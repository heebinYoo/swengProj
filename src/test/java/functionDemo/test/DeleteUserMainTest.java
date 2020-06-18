package functionDemo.test;

import functionDemo.bean.Token;
import functionDemo.bean.UserVo;
import functionDemo.mock.DeleteUserMain;
import functionDemo.mock.LoginMain;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

import static functionDemo.mock.DBUtilities.*;
import static org.junit.Assert.*;

public class DeleteUserMainTest {
    static UserVo userVo;

    @Before
    public void setup(){

        userVo = new UserVo();
        userVo.id = "1999bin";
        userVo.pw = "0225heebin";
        userVo.name = "heebin Y.";
        userVo.phone = "01089392751";
        userVo.email = "1999bin@naver.com";
        userVo.active = false;

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbms_url, dbms_id, dbms_pw);
            PreparedStatement prepstmt =  conn.prepareStatement("insert into user(id,pw,name,phone,email,active) values(?,?,?,?,?,?)");
            prepstmt.setString(1, userVo.id);
            prepstmt.setString(2, userVo.pw);
            prepstmt.setString(3, userVo.name);
            prepstmt.setString(4, userVo.phone);
            prepstmt.setString(5, userVo.email);
            prepstmt.setInt(6, userVo.active?1:0);

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
    public void deleteUser() {
        Token token = new LoginMain().login("admin", "nayana");


        DeleteUserMain deleteUserMain = new DeleteUserMain();

        assertEquals(DeleteUserMain.ok, deleteUserMain.deleteUser(userVo, token));

        final String query = "select * from user where id=?";
        int i=0;
        try {
            Connection conn = null;
            conn = DriverManager.getConnection(dbms_url, dbms_id, dbms_pw);
            PreparedStatement prepStmt = conn.prepareStatement(query);

            prepStmt.setString(1, userVo.id);

            ResultSet rset = prepStmt.executeQuery();

            while (rset.next()) {
                i++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        assertSame(0,i);


    }

    @Test
    public void accessDenied() {
        Token token = new LoginMain().login("normal", "normal");


        DeleteUserMain deleteUserMain = new DeleteUserMain();
        assertEquals(DeleteUserMain.accessDenied, deleteUserMain.deleteUser(userVo, token));

        final String query = "select * from user where id=?";
        int i=0;
        try {
            Connection conn = null;
            conn = DriverManager.getConnection(dbms_url, dbms_id, dbms_pw);
            PreparedStatement prepStmt = conn.prepareStatement(query);

            prepStmt.setString(1, userVo.id);

            ResultSet rset = prepStmt.executeQuery();

            while (rset.next()) {
                i++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        assertSame(1,i);




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
    public void not() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbms_url, dbms_id, dbms_pw);
            Statement statement =  conn.createStatement();
            statement.execute("update user set active = 1 where id = \"1999bin\" ");
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }




        Token token = new LoginMain().login("admin", "nayana");


        DeleteUserMain deleteUserMain = new DeleteUserMain();
        assertEquals(DeleteUserMain.not, deleteUserMain.deleteUser(userVo, token));

        final String query = "select * from user where id=?";
        int i=0;
        try {

            conn = DriverManager.getConnection(dbms_url, dbms_id, dbms_pw);
            PreparedStatement prepStmt = conn.prepareStatement(query);

            prepStmt.setString(1, userVo.id);

            ResultSet rset = prepStmt.executeQuery();

            while (rset.next()) {
                i++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        assertSame(1,i);





        try {
            conn = DriverManager.getConnection(dbms_url, dbms_id, dbms_pw);
            Statement statement =  conn.createStatement();
            statement.execute("delete from user where id = \"1999bin\" ");
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }



}