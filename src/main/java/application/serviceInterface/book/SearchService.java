package application.serviceInterface.book;

import database.vo.BookVo;
import service.response.StatusedArrayList;
import service.response.Token;

public interface SearchService {

    String ok = "ok";
    String accessDenied = "you should be a active or manager";

    StatusedArrayList<BookVo> search(String key, String method, Token token);


}
