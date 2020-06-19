package application.serviceInterface.user;

import database.vo.UserVo;
import service.response.Status;
import service.response.Token;

public interface DeleteUserService {
    String ok = "ok";
    String notFound = "can't found user";
    String accessDenied = "be manager";

    Status delete(UserVo userVo, Token token);

}

