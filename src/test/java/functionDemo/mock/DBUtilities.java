package functionDemo.mock;

import functionDemo.bean.BookVo;
import functionDemo.bean.Token;
import functionDemo.bean.UserVo;

import java.sql.*;
import java.util.ArrayList;

public class DBUtilities {
    final static public String dbms_addr = "192.168.0.13/sweng";
    final static public String dbms_url = "jdbc:mysql://"+dbms_addr+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    final static public String dbms_id = "default";
    final static public String dbms_pw = "1234";


    /**
     * it access with root of mysql
     * @param id
     * @return
     * @throws SQLException
     */
    public static boolean isNotDuplicated(String id) throws SQLException {
        final String duplicateCheckQuery = "select 1 from user where id=?";

        Connection conn = DriverManager.getConnection(dbms_url, dbms_id, dbms_pw);
        PreparedStatement prepStmt = conn.prepareStatement(duplicateCheckQuery);
        prepStmt.setString(1, id);

        ResultSet rset = prepStmt.executeQuery();

        while (rset.next()) {
            prepStmt.close();
            conn.close();
            return false;
        }

        prepStmt.close();
        conn.close();
        return true;
    }

    /**
     * it access with root of mysql
     * @param userVo
     * @return
     * @throws SQLException
     */
    public static boolean register(UserVo userVo) throws SQLException {
        final String query = "insert into user(id,pw,name,phone,email) values(?,?,?,?,?)";

        Connection conn = DriverManager.getConnection(dbms_url, dbms_id, dbms_pw);
        PreparedStatement prepStmt = conn.prepareStatement(query);
        prepStmt.setString(1, userVo.id);
        prepStmt.setString(2, userVo.pw);
        prepStmt.setString(3, userVo.name);
        prepStmt.setString(4, userVo.phone);
        prepStmt.setString(5, userVo.email);

        int result = prepStmt.executeUpdate();
        prepStmt.close();
        conn.close();

        switch (result){
            case 1:
                return true;
            default:
                return false;
        }
    }

    /**
     * it access with root of mysql
     * @param id
     * @param pw
     * @return
     * @throws SQLException
     */
    public static Token login(String id, String pw) throws SQLException {
        UserVo userVo = new UserVo();

        final String query = "select * from user where id=? and pw=?";
        Connection conn = DriverManager.getConnection(dbms_url, dbms_id, dbms_pw);
        PreparedStatement prepStmt = conn.prepareStatement(query);
        prepStmt.setString(1, id);
        prepStmt.setString(2, pw);

        ResultSet rset = prepStmt.executeQuery();


        while (rset.next()) {
            userVo.id=id;
            userVo.pw=pw;
            userVo.name = rset.getString(3);
            userVo.phone = rset.getString(4);
            userVo.email = rset.getString(5);
            userVo.active = rset.getInt(6) == 1 ? true : false;
            userVo.manage = rset.getInt(7) ==1 ? true : false;
        }
        prepStmt.close();
        conn.close();

        if(userVo.manage){
            conn = DriverManager.getConnection(dbms_url, "admin", "nayana");
            return new Token(userVo, conn, Token.manager);
        }
        else if(userVo.active==false){
            return new Token(null, null, Token.deactivated);
        }
        else if(userVo.id==null){
            return new Token(null, null, Token.notFound);
        }
        else{
            conn = DriverManager.getConnection(dbms_url, "normal", "normal");
            return new Token(userVo, conn, Token.ok);
        }

    }

    /**
     *
     * @param conn : should pass admin connection obj
     * @return : ArrayList of users
     * @throws SQLException
     */
    public static ArrayList<UserVo> getUsers(Connection conn) throws SQLException {

        ArrayList<UserVo> userVos = new ArrayList<>();
        final String query = "select * from user where manage=0";

        Statement statement = conn.createStatement();
        ResultSet rset = statement.executeQuery(query);

        UserVo userVo;
        while (rset.next()) {
            userVo = new UserVo();
            userVo.id=rset.getString(1);
            userVo.pw=rset.getString(2);
            userVo.name = rset.getString(3);
            userVo.phone = rset.getString(4);
            userVo.email = rset.getString(5);
            userVo.active = rset.getInt(6) == 1 ? true : false;
            userVo.manage = rset.getInt(7) ==1 ? true : false;
            userVos.add(userVo);
        }

        statement.close();
        conn.close();

        return userVos;
    }

    private static boolean isDeletableUser(UserVo userVo, Connection conn) throws SQLException {
        final String duplicateCheckQuery = "select active from user where id=?";

        PreparedStatement prepStmt = conn.prepareStatement(duplicateCheckQuery);
        prepStmt.setString(1, userVo.id);

        ResultSet rset = prepStmt.executeQuery();
        int result = -1;
        while (rset.next()) {
            result = rset.getInt(1);
        }
        prepStmt.close();

        if(result==0){
            return true;
        }
        else{
            return false;
        }

    }
    public static boolean deleteUser(UserVo userVo, Connection conn) throws SQLException {

        final String deleteQuery = "delete from user where id=?";

        try {
        conn.setAutoCommit(false);

        if(!isDeletableUser(userVo, conn))
            return false;

        PreparedStatement prepStmt = conn.prepareStatement(deleteQuery);
        prepStmt.setString(1, userVo.id);
        prepStmt.executeUpdate();

        } catch (SQLException throwables) {
            throw throwables;
        }
        finally {
            conn.setAutoCommit(true);
        }

        return true;
    }
    public static ArrayList<BookVo> searchByPYear(String key, Connection conn) throws SQLException {
        ArrayList<BookVo> bookVos = new ArrayList<>();
        final String query = "select * from book where pyear=?";

        PreparedStatement prepStmt = conn.prepareStatement(query);
        prepStmt.setString(1, key);
        ResultSet rset = prepStmt.executeQuery();

        BookVo bookVo;
        while (rset.next()) {
            bookVo = new BookVo();
            bookVo.idx = rset.getInt(1);
            bookVo.uid = rset.getString(2);
            bookVo.title = rset.getString(3);
            bookVo.publisher = rset.getString(4);
            bookVo.writer = rset.getString(5);
            bookVo.year = rset.getInt(6);
            bookVo.price = rset.getInt(7);
            bookVo.condition = rset.getInt(8);
            bookVo.ISBN = rset.getString(9);
            bookVos.add(bookVo);
        }

        prepStmt.close();


        return bookVos;
    }

    public static ArrayList<BookVo> searchByUid(String key, Connection conn) throws SQLException {

        ArrayList<BookVo> bookVos = new ArrayList<>();
        final String query = "select * from book where uid=?";

        PreparedStatement prepStmt = conn.prepareStatement(query);
        prepStmt.setString(1, key);
        ResultSet rset = prepStmt.executeQuery();

        BookVo bookVo;
        while (rset.next()) {
            bookVo = new BookVo();
            bookVo.idx = rset.getInt(1);
            bookVo.uid = rset.getString(2);
            bookVo.title = rset.getString(3);
            bookVo.publisher = rset.getString(4);
            bookVo.writer = rset.getString(5);
            bookVo.year = rset.getInt(6);
            bookVo.price = rset.getInt(7);
            bookVo.condition = rset.getInt(8);
            bookVo.ISBN = rset.getString(9);
            bookVos.add(bookVo);
        }

        prepStmt.close();


        return bookVos;

    }

    public static ArrayList<BookVo> searchByWriter(String key, Connection conn) throws SQLException {


        ArrayList<BookVo> bookVos = new ArrayList<>();
        final String query = "select * from book where writter=?";

        PreparedStatement prepStmt = conn.prepareStatement(query);
        prepStmt.setString(1, key);
        ResultSet rset = prepStmt.executeQuery();

        BookVo bookVo;
        while (rset.next()) {
            bookVo = new BookVo();
            bookVo.idx = rset.getInt(1);
            bookVo.uid = rset.getString(2);
            bookVo.title = rset.getString(3);
            bookVo.publisher = rset.getString(4);
            bookVo.writer = rset.getString(5);
            bookVo.year = rset.getInt(6);
            bookVo.price = rset.getInt(7);
            bookVo.condition = rset.getInt(8);
            bookVo.ISBN = rset.getString(9);
            bookVos.add(bookVo);
        }

        prepStmt.close();


        return bookVos;
    }

    public static ArrayList<BookVo> searchByISBN(String key, Connection conn) throws SQLException  {


        ArrayList<BookVo> bookVos = new ArrayList<>();
        final String query = "select * from book where ISBN=?";

        PreparedStatement prepStmt = conn.prepareStatement(query);
        prepStmt.setString(1, key);
        ResultSet rset = prepStmt.executeQuery();

        BookVo bookVo;
        while (rset.next()) {
            bookVo = new BookVo();
            bookVo.idx = rset.getInt(1);
            bookVo.uid = rset.getString(2);
            bookVo.title = rset.getString(3);
            bookVo.publisher = rset.getString(4);
            bookVo.writer = rset.getString(5);
            bookVo.year = rset.getInt(6);
            bookVo.price = rset.getInt(7);
            bookVo.condition = rset.getInt(8);
            bookVo.ISBN = rset.getString(9);
            bookVos.add(bookVo);
        }

        prepStmt.close();


        return bookVos;
    }

    public static ArrayList<BookVo> searchByTitle(String key, Connection conn) throws SQLException  {


        ArrayList<BookVo> bookVos = new ArrayList<>();
        final String query = "select * from book where title=?";

        PreparedStatement prepStmt = conn.prepareStatement(query);
        prepStmt.setString(1, key);
        ResultSet rset = prepStmt.executeQuery();

        BookVo bookVo;
        while (rset.next()) {
            bookVo = new BookVo();
            bookVo.idx = rset.getInt(1);
            bookVo.uid = rset.getString(2);
            bookVo.title = rset.getString(3);
            bookVo.publisher = rset.getString(4);
            bookVo.writer = rset.getString(5);
            bookVo.year = rset.getInt(6);
            bookVo.price = rset.getInt(7);
            bookVo.condition = rset.getInt(8);
            bookVo.ISBN = rset.getString(9);
            bookVos.add(bookVo);
        }

        prepStmt.close();


        return bookVos;
    }




}
