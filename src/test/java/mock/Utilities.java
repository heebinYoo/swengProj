package mock;

import bean.UserVo;

import java.sql.*;

public class Utilities {
    final static public String dbms_addr = "192.168.0.13/sweng";
    final static public String dbms_url = "jdbc:mysql://"+dbms_addr+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    final static public String dbms_id = "root";
    final static public String dbms_pw = "rootroot";



    public static boolean isNotDuplicated(String ID) throws SQLException {
        final String duplicateCheckQuery = "select 1 from user where ID=?";

        Connection conn = DriverManager.getConnection(dbms_url, dbms_id, dbms_pw);
        PreparedStatement prepStmt = conn.prepareStatement(duplicateCheckQuery);
        prepStmt.setString(1, ID);

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
}
