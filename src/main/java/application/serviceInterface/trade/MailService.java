package application.serviceInterface.trade;

import service.response.Status;
import service.response.Token;
import service.response.Trade;

public interface MailService {

    String ok = "ok";
    Status send(Trade trade, Token token);
}
