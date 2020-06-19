package service;

import database.vo.UserVo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDAO {

    boolean isNotDuplicated(String id, Connection conn) throws SQLException;
    boolean register(UserVo userVo, Connection conn) throws SQLException;
    UserVo login(String inputId, String inputPw, Connection conn) throws SQLException;
    ArrayList<UserVo> getList(Connection conn) throws SQLException;
    boolean delete(UserVo userVo, Connection conn) throws SQLException;
    String getEmail(String id, Connection conn) throws SQLException;
    boolean deactivate(UserVo userVo, Connection conn) throws SQLException;
    boolean activate(UserVo userVo, Connection conn) throws SQLException;



}
