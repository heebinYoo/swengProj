package service;

import database.vo.BookVo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface BookDAO {

    ArrayList<BookVo> search(String key, String method, Connection conn) throws SQLException;
    boolean insert(BookVo bookVo, Connection conn) throws SQLException;
    boolean delete(BookVo bookVo, Connection conn) throws SQLException;
    boolean update(BookVo bookVo, Connection conn) throws SQLException;
}
