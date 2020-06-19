package service.trade;

import application.serviceInterface.trade.MailService;
import service.response.Status;
import service.response.Token;
import service.response.Trade;

public class MailServiceImpl implements MailService {
    public Status send(Trade trade, Token token){
        return new Status(ok);
    }
}
