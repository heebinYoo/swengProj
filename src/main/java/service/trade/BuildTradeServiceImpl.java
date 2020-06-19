package service.trade;


import application.serviceInterface.trade.BuildTradeService;
import database.vo.BookVo;
import database.vo.UserVo;
import service.UserDAO;
import service.response.Token;
import service.response.Trade;

import java.sql.SQLException;

public class BuildTradeServiceImpl implements BuildTradeService {

    private UserDAO userDAO;
    public BuildTradeServiceImpl(UserDAO userDAO){
        this.userDAO=userDAO;
    }

    public Trade buildTrade(BookVo target, UserVo buyer, Token token){

        if(!token.getStatus().equals(Token.ok)){
            return new Trade(accessDenied, null, null, -1);
        }

        try {
            return new Trade(ok, userDAO.getEmail(target.getUid(), token.getConn()), buyer.getEmail(), target.getIdx());

        } catch (SQLException throwables) {
            return new Trade(throwables.toString(), null, null, -1);
        }
    }

}
