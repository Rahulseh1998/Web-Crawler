/**
 * Created by Rahul Sehrawat on 13-07-2017.
 */


import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;


public class sql {
    private static final String USER_AGENT ="Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36";
    private static int MAX_LEVEL = 2;
   private static String startingURL = "http://www.google.com/";

    public static void main(String[] args) {

            crawl();
//        String txt = "Stuart Frankel's very small web sit";
//        System.out.println(StringEscapeUtils.escapeEcmaScript(txt));

//        String results = StringEscapeUtils.escapeJava(str);
//        System.out.println(results);

    }


    public static void crawl()
    {

        db database = new db();
        page p ;
        int i=0;
        HashMap<String,Boolean> vistited = new HashMap<>();
        Queue<String> pendingLinks = new LinkedList<>();
        pendingLinks.add(startingURL);
        vistited.put(startingURL,true);
        String currentURL;
        Document htmldocument ;
        Elements anchor;
        Elements images;
        int numOFLinksCrawled = 0;

        int linksCollision = 0;

        Element keywords;Elements description;
        int level = 1;
        int numOfLinksOnFirstPage=0;
        while (!pendingLinks.isEmpty())
        {
            currentURL  = pendingLinks.poll();

            //for the level count
            if (currentURL == null)
            {
                level++;
                System.out.println(MAX_LEVEL+numOfLinksOnFirstPage);
                if (level==MAX_LEVEL+numOfLinksOnFirstPage)
                {
                    System.out.println("Level finished");
                    break;
                }
                // if level equals MAX_LEVEL then it will come out of the loop

            }

            //check if the url is not empty
            while (currentURL==""||currentURL==null)
            {
                currentURL = pendingLinks.poll();
            }

            p = new page(currentURL);
            numOFLinksCrawled++;
            System.out.println(numOFLinksCrawled+") crawling : "+currentURL );

            try {
                //load the page
                htmldocument = Jsoup.connect(currentURL).userAgent(USER_AGENT).get();

                if (htmldocument==null) {
                    System.out.println("page not found");return;
                }
                p.setTitle(htmldocument.title());

                //getting the meta keywords
                keywords = htmldocument.select("meta[name=keywords]").first();
                if (keywords!=null)
                    p.setMeta_keywords(keywords.attr("content"));

                //getting the meat description
                description = htmldocument.select("meta[name=description]");
                if (description!=null)
                    p.setMeta_description(description.attr("content"));

                //getting all the links
                anchor = htmldocument.select("a[href]");
                images = htmldocument.select("img[src]");

                for (Element a:anchor)
                {
                    i++;
                    if (!vistited.containsKey(a.attr("abs:href")))
                    {
                        pendingLinks.add(a.attr("abs:href"));
                        vistited.put(a.attr("abs:href"),true);
                    }
                    else {
                        linksCollision++;
                    }
                }

                //Null is added after each website links to keep the track of the levels
                pendingLinks.add(null);

                p.setNum_of_links(i);
                if (numOFLinksCrawled==1)
                    numOfLinksOnFirstPage = i;
                i=0;
                for (Element img:images)
                {
                    i++;
                }

                p.setNum_of_images(i);

                p.setText(htmldocument.text());
//                System.out.println(p.makeQuery());
                database.insertPage(p.makeQuery());
                database.insertURL(currentURL);

            } catch (IOException e) {
                e.printStackTrace();
            }





        }



    }

}


//       try {
//            Class.forName("com.mysql.jdbc.Driver");
//            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "");
//        }catch (Exception e){
//
//        }
////here sonoo is database name, root is username and password
//            Statement stmt=con.createStatement();
//            ResultSet rs=stmt.executeQuery("select * from users");
//            boolean result = stmt.execute("INSERT INTO `users`( `name`, `email`) VALUES ('Vishal','vishal@gmail.com')");
////            while(rs.next())
////                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
//            System.out.println(result);
//            con.close();
//        }catch(Exception e){ System.out.println(e);}