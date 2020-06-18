package functionDemo.test;

import functionDemo.bean.UserVo;
import functionDemo.mock.RegisterMain;
import org.junit.After;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static functionDemo.mock.DBUtilities.*;
import static org.junit.Assert.*;



public class RegisterMainTest {

    @After
    public void doAfter(){
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

        RegisterMain registerMain = new RegisterMain();

        UserVo userVo = new UserVo();
        userVo.id = "1999bin";
        userVo.pw = "0225heebin";
        userVo.name = "heebin Y.";
        userVo.phone = "01089392751";
        userVo.email = "1999bin@naver.com";

        String result = registerMain.enterInfo(userVo);

        assertEquals(result, RegisterMain.ok);
    }
    @Test
    public void isDuplicated() {
        RegisterMain registerMain = new RegisterMain();
        String result;

        UserVo userVo = new UserVo();
        userVo.id = "1999bin";
        userVo.pw = "12355";
        userVo.name = "heebin K.";
        userVo.phone = "01089392751";
        userVo.email = "1999bin@naver.com";


        result = registerMain.enterInfo(userVo);
        assertEquals(result, RegisterMain.ok);

        result = registerMain.enterInfo(userVo);
        assertEquals(result, RegisterMain.isDuplicated);


    }

    @Test
    public void reqnotnull() {

        RegisterMain registerMain = new RegisterMain();

        UserVo userVo = new UserVo();
        userVo.id = "";
        userVo.pw = "0225heebin";
        userVo.name = "heebin Y.";
        userVo.phone = "01089392751";
        userVo.email = "1999bin@naver.com";

        String result = registerMain.enterInfo(userVo);

        assertEquals(result, RegisterMain.reqnotempty);
    }



    @Test
    public void isNotVaildEmail() {
        RegisterMain registerMain = new RegisterMain();

        UserVo userVo = new UserVo();
        userVo.id = "1999bin";
        userVo.pw = "0225heebin";
        userVo.name = "heebin Y.";
        userVo.phone = "01089392751";
        userVo.email = "naver.com";

        String result = registerMain.enterInfo(userVo);

        assertEquals(result, RegisterMain.isNotVaildEmail);
    }

}