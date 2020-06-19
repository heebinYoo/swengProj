package application.serviceInterface.user;

import database.vo.UserVo;
import service.response.StatusedArrayList;
import service.response.Token;

public interface GetListService {
    String ok = "ok";
    String accessDenied = "you should be a manager";

    StatusedArrayList<UserVo> getList(Token token);
}
