package application.serviceInterface.book;

import database.vo.BookVo;
import service.response.Status;
import service.response.Token;

public interface DeleteBookService {

    String ok = "ok";
    String unknown = "please retry";
    String accessDenied = "you should delete your book or manager";


    Status delete(BookVo bookVo, Token token);
}
