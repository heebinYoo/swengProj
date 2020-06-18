package service.book;

import database.dao.BookDAO;
import database.vo.BookVo;
import service.response.Status;
import service.response.Token;

import java.sql.SQLException;

public class RegisterService {
    public static final String ok = "ok";
    public static final String unknown = "please retry";
    public static final String accessDenied = "you should register your own book or manager";

    public static Status register(BookVo bookVo, Token token){
        if(token.getData().getId() == bookVo.getUid() || token.getStatus().equals(Token.manager)) {
            boolean resultIsGood = false;
            try {
                resultIsGood = BookDAO.insert(bookVo, token.getConn());
            } catch (SQLException throwables) {
                return new Status(throwables.toString());
            }
            if (resultIsGood) {
                return new Status(ok);
            } else {
                return new Status(unknown);
            }
        }
        else{
            return new Status(accessDenied);
        }
    }

}
