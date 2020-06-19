package application.serviceInterface.trade;

import database.vo.BookVo;
import database.vo.UserVo;
import service.response.Token;
import service.response.Trade;

public interface BuildTradeService {
    String ok = "ok";
    String accessDenied = "you should be a nomal user";

    Trade buildTrade(BookVo target, UserVo buyer, Token token);

    }
