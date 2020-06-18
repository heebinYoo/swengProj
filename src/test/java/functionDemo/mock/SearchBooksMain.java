package functionDemo.mock;

import functionDemo.bean.BookVo;
import functionDemo.bean.StatusedArrayList;
import functionDemo.bean.Token;

import java.sql.SQLException;
import java.util.ArrayList;


public class SearchBooksMain {
    public static final String title = "title";
    public static final String ISBN = "ISBN";
    public static final String writer = "writer";
    public static final String pYear = "pYear";
    public static final String uid = "uid";

    public static final String ok = "ok";
    public static final String unAvailalbeMethod = "it is unavailalbe method";



    public StatusedArrayList<BookVo> search(String method, String key, Token token){
        ArrayList<BookVo> result = null;
        try {
            switch (method){
                case title:
                    result = DBUtilities.searchByTitle(key, token.getConn());
                    break;
                case ISBN:
                    result = DBUtilities.searchByISBN(key, token.getConn());
                    break;
                case writer:
                    result = DBUtilities.searchByWriter(key, token.getConn());
                    break;
                case uid:
                    result = DBUtilities.searchByUid(key, token.getConn());
                    break;
                case pYear:
                    result = DBUtilities.searchByPYear(key, token.getConn());
                default:
                    return new StatusedArrayList<>(null, unAvailalbeMethod);
            }
        } catch (SQLException throwables) {
            return new StatusedArrayList<>(null, throwables.toString());
        }
        return new StatusedArrayList<>(result, ok);


    }




}
