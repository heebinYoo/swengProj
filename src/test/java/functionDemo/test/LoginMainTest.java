package functionDemo.test;

import functionDemo.bean.Token;
import functionDemo.bean.UserVo;
import functionDemo.mock.LoginMain;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.*;

import static functionDemo.mock.DBUtilities.*;
import static org.junit.Assert.*;

public class LoginMainTest {

    static UserVo userVo;

    @BeforeClass
    public static void setup(){

        userVo = new UserVo();
        userVo.id = "1999bin";
        userVo.pw = "0225heebin";
        userVo.name = "heebin Y.";
        userVo.phone = "01089392751";
        userVo.email = "1999bin@naver.com";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbms_url, dbms_id, dbms_pw);
            PreparedStatement prepstmt =  conn.prepareStatement("insert into user(id,pw,name,phone,email) values(?,?,?,?,?)");
            prepstmt.setString(1, userVo.id);
            prepstmt.setString(2, userVo.pw);
            prepstmt.setString(3, userVo.name);
            prepstmt.setString(4, userVo.phone);
            prepstmt.setString(5, userVo.email);
            prepstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @AfterClass
    public static void tearDown(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbms_url, dbms_id, dbms_pw);
            Statement statement =  conn.createStatement();
            statement.execute("delete from user where id = \"1999bin\" ");
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void ok() {
        LoginMain loginMain = new LoginMain();

        Token result = loginMain.login(userVo.id, userVo.pw);
        assertEquals(result.getStatus(), Token.ok);
    }
    @Test
    public void manager() {
        LoginMain loginMain = new LoginMain();

        Token result = loginMain.login("admin", "nayana");
        assertEquals(result.getStatus(), Token.manager);
    }

    @Test
    public void notFound() {
        LoginMain loginMain = new LoginMain();
        Token result = loginMain.login("xzczsadfsda@@!!csaszc", "dfscvxcxzxz");
        assertEquals(result.getStatus(), Token.notFound);
    }

    @Test
    public void deactivated(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbms_url, dbms_id, dbms_pw);
            PreparedStatement prepstmt =  conn.prepareStatement("update user set active=? where id=?");
            prepstmt.setInt(1, 0);
            prepstmt.setString(2, userVo.id);
            prepstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        LoginMain loginMain = new LoginMain();

        Token result = loginMain.login(userVo.id, userVo.pw);
        assertEquals(result.getStatus(), Token.deactivated);

        try {
            conn = DriverManager.getConnection(dbms_url, dbms_id, dbms_pw);
            PreparedStatement prepstmt =  conn.prepareStatement("update user set active=? where id=?");
            prepstmt.setInt(1, 1);
            prepstmt.setString(2, userVo.id);
            prepstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}