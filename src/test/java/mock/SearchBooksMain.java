package mock;

import bean.BookVo;
import bean.StatusedArrayList;
import bean.Token;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static mock.DBUtilities.*;


public class SearchBooksMain {
    public static final String title = "title";
    public static final String ISBN = "ISBN";
    public static final String writer = "writer";
    public static final String uid = "uid";

    public static final String ok = "ok";
    public static final String unAvailalbeMethod = "it is unavailalbe method";



    public StatusedArrayList<BookVo> search(String method, String key, Token token){
        ArrayList<BookVo> reslut = null;
        try {
            switch (method){
                case title:
                    reslut = searchByTitle(key, token.getConn());
                    break;
                case ISBN:
                    reslut = searchByISBN(key, token.getConn());
                    break;
                case writer:
                    reslut = searchByWriter(key, token.getConn());
                    break;
                case uid:
                    reslut = searchByUid(key, token.getConn());
                    break;
                default:
                    return new StatusedArrayList<>(null, unAvailalbeMethod);
            }
        } catch (SQLException throwables) {
            return new StatusedArrayList<>(null, throwables.toString());
        }
        return new StatusedArrayList<>(reslut, ok);


    }




}
