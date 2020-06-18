package service.response;

import service.response.GetStatusable;
import database.vo.UserVo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Token implements GetStatusable {

    public static final String ok = "ok";
    public static final String manager = "it's manager";
    public static final String deactivated = "it's de activated";
    public static final String notFound = "you may mistype your id/pw";


    final private UserVo data;
    final private Connection conn;
    final private String status;

    public Token() throws SQLException {
        String dbms_addr = "192.168.0.13/sweng";
        String dbms_url = "jdbc:mysql://"+dbms_addr+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String dbms_id = "default";
        String dbms_pw = "1234";

        this.data = null;
        this.conn = DriverManager.getConnection(dbms_url, dbms_id, dbms_pw);;
        this.status = null;
    }
    public Token(UserVo data, Connection conn, String status) {
        this.data = data;
        this.conn = conn;
        this.status = status;
    }

    public UserVo getData() {
        return data;
    }

    public Connection getConn() {
        return conn;
    }

    public String getStatus() {
        return status;
    }
}
