package database.dao;

import database.vo.UserVo;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
    /**
     * it access with root of mysql
     * @param id
     * @return
     * @throws SQLException
     */
    public static boolean isNotDuplicated(String id, Connection conn) throws SQLException {
        final String duplicateCheckQuery = "select 1 from user where id=?";

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
    public static boolean register(UserVo userVo, Connection conn) throws SQLException {
        final String query = "insert into user(id,pw,name,phone,email) values(?,?,?,?,?)";


        PreparedStatement prepStmt = conn.prepareStatement(query);
        prepStmt.setString(1, userVo.getId());
        prepStmt.setString(2, userVo.getPw());
        prepStmt.setString(3, userVo.getName());
        prepStmt.setString(4, userVo.getPhone());
        prepStmt.setString(5, userVo.getEmail());

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
     * @param inputId
     * @param inputPw
     * @return
     * @throws SQLException
     */
    public static UserVo login(String inputId, String inputPw, Connection conn) throws SQLException {
        UserVo userVo;


        final String query = "select * from user where id=? and pw=?";

        PreparedStatement prepStmt = conn.prepareStatement(query);
        prepStmt.setString(1, inputId);
        prepStmt.setString(2, inputPw);

        ResultSet rset = prepStmt.executeQuery();


        if(rset.next()) {
            userVo = getUserVo(rset);
        }
        else {
            return null;
        }
        prepStmt.close();

        return userVo;

    }

    private static UserVo getUserVo(ResultSet rset) throws SQLException {
        UserVo userVo;
        String id=rset.getString(1);
        String pw=rset.getString(2);
        String name = rset.getString(3);
        String phone = rset.getString(4);
        String email = rset.getString(5);
        boolean active = rset.getInt(6) == 1 ? true : false;
        boolean manage = rset.getInt(7) ==1 ? true : false;

        userVo = new UserVo(id, pw, name, phone, email, active, manage);
        return userVo;
    }

    /**
     *
     * @param conn : should pass admin connection obj
     * @return : ArrayList of users
     * @throws SQLException
     */
    public static ArrayList<UserVo> getList(Connection conn) throws SQLException {

        ArrayList<UserVo> userVos = new ArrayList<>();
        final String query = "select * from user where manage=0";

        Statement statement = conn.createStatement();
        ResultSet rset = statement.executeQuery(query);

        UserVo userVo;
        while (rset.next()) {
            userVo = getUserVo(rset);
            userVos.add(userVo);
        }

        statement.close();


        return userVos;
    }


    public static boolean delete(UserVo userVo, Connection conn) throws SQLException {

        final String deleteQuery = "delete from user where id=? and active=0";

        PreparedStatement prepStmt = conn.prepareStatement(deleteQuery);
        prepStmt.setString(1, userVo.getId());
        int result = prepStmt.executeUpdate();

        return result==1 ? true:false;
    }

    public static String getEmail(String id, Connection conn) throws SQLException{
        final String query = "select email from user where id=?";

        PreparedStatement prepStmt = conn.prepareStatement(query);
        prepStmt.setString(1, id);

        ResultSet rset = prepStmt.executeQuery();

        if(rset.next())
            return rset.getString(1);
        else
            return null;

    }

    public static boolean deactivate(UserVo userVo, Connection conn) throws SQLException{

        final String query = "update user set active=0 where id=? and active=1";

        PreparedStatement prepStmt = conn.prepareStatement(query);
        prepStmt.setString(1, userVo.getId());
        int result = prepStmt.executeUpdate();

        return result==1 ? true:false;
    }

    public static boolean activate(UserVo userVo, Connection conn) throws SQLException{

        final String query = "update user set active=1 where id=? and active=0";

        PreparedStatement prepStmt = conn.prepareStatement(query);
        prepStmt.setString(1, userVo.getId());
        int result = prepStmt.executeUpdate();

        return result==1 ? true:false;
    }


}
