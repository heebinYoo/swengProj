package service.book;

import application.serviceInterface.book.SearchService;

import database.vo.BookVo;
import service.BookDAO;
import service.response.StatusedArrayList;
import service.response.Token;

import java.sql.SQLException;
import java.util.ArrayList;

public class SearchServiceImpl implements SearchService {

    private BookDAO bookDAO;
    public SearchServiceImpl(BookDAO bookDAO){
        this.bookDAO=bookDAO;
    }



    public StatusedArrayList<BookVo> search(String key, String method, Token token) {

        if(token.getStatus().equals(Token.ok) || token.getStatus().equals(Token.manager)){
            return new StatusedArrayList<>(null, accessDenied);
        }

        try {
            ArrayList<BookVo> bookVos = bookDAO.search(key, method, token.getConn());
            return new StatusedArrayList(bookVos, ok);
        } catch (SQLException throwables) {
            return new StatusedArrayList(null, throwables.toString());
        }


    }
}
