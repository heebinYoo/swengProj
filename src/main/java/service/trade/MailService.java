package service.trade;

import service.response.Status;
import service.response.Token;
import service.response.Trade;

public class MailService {

    public static final String ok = "ok";
    public static Status send(Trade trade, Token token){
        return new Status(ok);

    }

}
