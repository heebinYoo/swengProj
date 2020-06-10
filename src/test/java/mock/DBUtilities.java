package mock;

import bean.Token;
import bean.UserVo;

import java.sql.*;

public class DBUtilities {
    final static public String dbms_addr = "192.168.0.13/sweng";
    final static public String dbms_url = "jdbc:mysql://"+dbms_addr+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    final static public String dbms_id = "root";
    final static public String dbms_pw = "rootroot";



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
            return new Token(userVo, conn, Token.manager);
        }
        else if(userVo.active==false){
            return new Token(null, null, Token.deactivated);
        }
        else{
            return new Token(userVo, conn, Token.ok);
        }






    }


}
