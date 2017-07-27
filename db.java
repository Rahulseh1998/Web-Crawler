import java.sql.*;

/**
 * Created by Rahul Sehrawat on 14-07-2017.
 */
public class db {

    private Connection connection;
//    makeSQLConn database;
    public db() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crawler", "root", "");
        } catch (Exception e) {
            System.out.println("Database connecttion error " + e.fillInStackTrace());
        }

    }

    public void coonectionClose()
    {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean insertPage(String query)
    {
        String queryi = "INSERT INTO `pages`(`title`, `url`, `meta_keyword`, `meta_description`, `noOfLinks`, `noOfImages`, `text`, `pageRank`) " +
                "VALUES (" +query+")";
//        System.out.println(queryi);
     return  execute(queryi);
//        System.out.println(query);
//        return false;
    }

    public boolean insertURL(String url)
    {
        String query = "INSERT INTO `visited`(`url`) VALUES (\'"+url+"\')";
         return execute(query);
    }




    public ResultSet executeQuery(String query)
    {
        ResultSet rs = null;
        try {
            Statement stmt=connection.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    public boolean execute(String query)
    {
        Boolean b = false;
        try {
            Statement stmt=connection.createStatement();
            b = stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error cannot execute query");
            System.out.println(query);
//            e.printStackTrace();
        }

        return b;
    }





}
