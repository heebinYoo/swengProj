package database.dao;

import database.vo.BookVo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookDAO{
    public static final String title = "title";
    public static final String ISBN = "ISBN";
    public static final String writer = "writer";
    public static final String pYear = "pYear";
    public static final String uid = "uid";


    public static ArrayList<BookVo> search(String key, String method, Connection conn) throws SQLException {
        ArrayList<BookVo> bookVos = new ArrayList<>();
        String query;
        switch (method){
            case title:
                query = "select * from book where title=?";
                break;
            case ISBN:
                query = "select * from book where ISBN=?";
                break;
            case writer:
                query = "select * from book where writter=?";
                break;
            case pYear:
                query = "select * from book where uid=?";
                break;
            case uid:
                query = "select * from book where pyear=?";
                break;
            default:
                throw new SQLException("not proper method");
        }


        PreparedStatement prepStmt = conn.prepareStatement(query);
        prepStmt.setString(1, key);
        ResultSet rset = prepStmt.executeQuery();

        BookVo bookVo;
        while (rset.next()) {
            int idx = rset.getInt(1);
            String uid = rset.getString(2);
            String title = rset.getString(3);
            String publisher = rset.getString(4);
            String writer = rset.getString(5);
            int pyear = rset.getInt(6);
            int price = rset.getInt(7);
            int condition = rset.getInt(8);
            String ISBN = rset.getString(9);

            bookVo = new BookVo(idx,uid,title, publisher, writer, pyear, price, condition, ISBN);
            bookVos.add(bookVo);
        }
        prepStmt.close();

        return bookVos;
    }

    public static boolean insert(BookVo bookVo, Connection conn) throws SQLException {

        final String query = "insert into `book`(`uid`,`title`,`publisher`,`writter`,`pyear`,`price`,`condition`,`ISBN`) values(?,?,?,?,?,?,?,?);";


        PreparedStatement prepStmt = conn.prepareStatement(query);
        prepStmt.setString(1, bookVo.getUid());
        prepStmt.setString(2, bookVo.getTitle());
        prepStmt.setString(3, bookVo.getPublisher());
        prepStmt.setString(4, bookVo.getWriter());
        prepStmt.setInt(5, bookVo.getPyear());
        prepStmt.setInt(6, bookVo.getPrice());
        prepStmt.setInt(7, bookVo.getCondition());
        prepStmt.setString(8, bookVo.getISBN());


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
    public static boolean delete(BookVo bookVo, Connection conn) throws SQLException {
        final String query = "delete from book where idx=?";

        PreparedStatement prepStmt = conn.prepareStatement(query);
        prepStmt.setInt(1, bookVo.getIdx());


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
    public static boolean update(BookVo bookVo, Connection conn) throws SQLException {
        final String query = "update book set title=?, publisher=?, writter=?, pyear=?, price=?, `condition`=?, ISBN=? where idx=?";

        PreparedStatement prepStmt = conn.prepareStatement(query);

        prepStmt.setString(1, bookVo.getTitle());
        prepStmt.setString(2, bookVo.getPublisher());
        prepStmt.setString(3, bookVo.getWriter());
        prepStmt.setInt(4, bookVo.getPyear());
        prepStmt.setInt(5, bookVo.getPrice());
        prepStmt.setInt(6, bookVo.getCondition());
        prepStmt.setString(7, bookVo.getISBN());

        prepStmt.setInt(8, bookVo.getIdx());


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
