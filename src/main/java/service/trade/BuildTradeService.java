package service.trade;

import database.dao.UserDAO;
import database.vo.BookVo;
import database.vo.UserVo;
import service.response.StatusedString;
import service.response.Token;
import service.response.Trade;

import java.sql.SQLException;

public class BuildTradeService {
    public static final String ok = "ok";
    public static final String accessDenied = "you should be a nomal user";

    public static Trade buildTrade(BookVo target, UserVo buyer, Token token){

        if(!token.getStatus().equals(Token.ok)){
            return new Trade(accessDenied, null, null, -1);
        }

        try {
            return new Trade(ok, UserDAO.getEmail(target.getUid(), token.getConn()), buyer.getEmail(), target.getIdx());

        } catch (SQLException throwables) {
            return new Trade(throwables.toString(), null, null, -1);
        }
    }

}
