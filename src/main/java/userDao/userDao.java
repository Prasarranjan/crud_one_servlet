package userDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import user.user;
public class userDao {
    public static Connection getConnection() {

        Connection Con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "prasar123");
            if (Con != null) {
                System.out.println("database successfully connected");
            } else {
                System.out.println("database connection failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Con;
    }
    public static int saveDetails(user u){
        int result=0;
        Connection con =getConnection();


        try {
            String createUser="insert into book(name,author,price,publisher,isbn) values(?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(createUser);
            ps.setString(1, u.getName());
            ps.setString(2, u.getAuthor());
            ps.setInt(3, u.getPrice());
            ps.setString(4, u.getPublisher());
            ps.setString(5, u.getIsbn());
            result=ps.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public static List<user> selectUser(){
        ArrayList<user> listitem= new ArrayList<>();
        Connection con = getConnection();
        try {
            String selectUser="select * from book";
            PreparedStatement ps = con.prepareStatement(selectUser);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                user User = new user();
                User.setId(rs.getInt("id"));
                User.setName(rs.getString("name"));
                User.setAuthor(rs.getString("author"));
                User.setPrice(rs.getInt("price"));
                User.setPublisher(rs.getString("publisher"));
                User.setIsbn(rs.getString("isbn"));
                listitem.add(User);
            }
        }
        catch (Exception e ){
            e.printStackTrace();
        }
        return listitem;
    }
    public static int deleteUser(int id){
        int result = 0;
        Connection con =getConnection();
        try{
            String deleteUser="delete from book where id=?";
            PreparedStatement ps = con.prepareStatement(deleteUser);
            ps.setInt(1,id);
            result =ps.executeUpdate();

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public static  int updateUser(user e){
        int result =0;
        Connection con = getConnection();
        try{
            String updateUser="update book set name=?,author=?,price=?,publisher=? ,isbn=? where id=?";
            PreparedStatement ps =con.prepareStatement(updateUser);
            ps.setString(1,e.getName());
            ps.setString(2,e.getAuthor());
            ps.setInt(3,e.getPrice());
            ps.setString(4,e.getPublisher());
            ps.setString(5,e.getIsbn());
            ps.setInt(6,e.getId());
            result=ps.executeUpdate();

        }
        catch (Exception f ){
            f.printStackTrace();
        }
        return result;
    }
    //select User by id
    public static user getUserbyid(int id) {
        user user = new user();

        Connection con = getConnection();
        try {
            String selectUserbyID="select id,name,author,price,publisher,isbn from book where id=?";
            PreparedStatement ps = con.prepareStatement(selectUserbyID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setAuthor(rs.getString("author"));
                user.setPrice(rs.getInt("price"));
                user.setPublisher(rs.getString("publisher"));
                user.setIsbn(rs.getString("isbn"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}


