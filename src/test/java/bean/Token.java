package bean;

import java.sql.Connection;

public class Token {
    public static final String ok = "ok";
    public static final String manager = "it's manager";
    public static final String deactivated = "it's de activated";
    public static final String exception = "exception occured, please retry";

    final private UserVo data;
    final private Connection conn;
    final private String status;

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
