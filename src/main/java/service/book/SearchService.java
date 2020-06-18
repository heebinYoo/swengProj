package service.book;

import database.dao.BookDAO;
import database.vo.BookVo;
import database.vo.UserVo;
import service.response.StatusedArrayList;
import service.response.Token;

import java.sql.SQLException;
import java.util.ArrayList;

public class SearchService {
    public static final String ok = "ok";
    public static final String accessDenied = "you should be a active or manager";

    public static final String title = BookDAO.title;
    public static final String ISBN = BookDAO.ISBN;
    public static final String writer = BookDAO.writer;
    public static final String pYear = BookDAO.pYear;
    public static final String uid = BookDAO.uid;



    public static StatusedArrayList<BookVo> search(String key, String method, Token token) {

        if(token.getStatus().equals(Token.ok) || token.getStatus().equals(Token.manager)){
            return new StatusedArrayList<>(null, accessDenied);
        }

        try {
            ArrayList<BookVo> bookVos = BookDAO.search(key, method, token.getConn());
            return new StatusedArrayList(bookVos, ok);
        } catch (SQLException throwables) {
            return new StatusedArrayList(null, throwables.toString());
        }


    }
}
