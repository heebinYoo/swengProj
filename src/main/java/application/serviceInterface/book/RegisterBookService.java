package application.serviceInterface.book;

import database.vo.BookVo;
import service.response.Status;
import service.response.Token;

public interface RegisterBookService {
    String ok = "ok";
    String unknown = "please retry";
    String accessDenied = "you should register your own book or manager";


    Status register(BookVo bookVo, Token token);
}
