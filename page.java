import org.apache.commons.lang3.StringEscapeUtils;

import java.net.URL;
import java.sql.Connection;

/**
 * Created by Rahul Sehrawat on 13-07-2017.
 */
public class page {

   private String title;
   private String url ;
   private String meta_keywords;
   private String meta_description;
   private int num_of_links;
   private int num_of_images;
   private double currentPageRank;
   private double newpageRank;
   private String text;



    //getting mysql database connection


    @Override
    public String toString() {
        return "page{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", meta_keywords='" + meta_keywords + '\'' +
                ", meta_description='" + meta_description + '\'' +
                ", num_of_links=" + num_of_links +
                ", num_of_images=" + num_of_images +
                ", currentPageRank=" + currentPageRank +
                ", newpageRank=" + newpageRank +
                ", text='" + text + '\'' +
                '}';
    }

    public String makeQuery()
    {
        if (title.length()>255)
            title = title.substring(255);

        return
                "\"" + StringEscapeUtils.escapeEcmaScript(title) + '\"' +
                ", \"" + url + '\"' +
                ", \"" + StringEscapeUtils.escapeEcmaScript(meta_keywords) + '\"' +
                ", \"" + StringEscapeUtils.escapeEcmaScript(meta_description) + '\"' +
                ", " + num_of_links +
                ", " + num_of_images +
                ", \"" + StringEscapeUtils.escapeEcmaScript(text)+'\"' +
                        ", " + currentPageRank ;

    }

    public page(String url) {
        this.title = "";
        this.url = url;
        this.meta_keywords = "";
        this.num_of_links = 0;
        this.num_of_images = 0;
        this.currentPageRank = 1.0;
        this.text = "";
    }

    public String getMeta_description() {
        return meta_description;
    }

    public void setMeta_description(String meta_description) {
        this.meta_description = meta_description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setMeta_keywords(String meta_keywords) {
        this.meta_keywords = meta_keywords;
    }

    public void setNum_of_links(int num_of_links) {
        this.num_of_links = num_of_links;
    }

    public void setNum_of_images(int num_of_images) {
        this.num_of_images = num_of_images;
    }

    public void setCurrentPageRank(double currentPageRank) {
        this.currentPageRank = currentPageRank;
    }

    public void setNewpageRank(double newpageRank) {
        this.newpageRank = newpageRank;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getMeta_keywords() {
        return meta_keywords;
    }

    public int getNum_of_links() {
        return num_of_links;
    }

    public int getNum_of_images() {
        return num_of_images;
    }

    public double getCurrentPageRank() {
        return currentPageRank;
    }

    public double getNewpageRank() {
        return newpageRank;
    }
}
