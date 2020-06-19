package application.serviceInterface.user;

import database.vo.UserVo;
import service.response.Status;
import service.response.Token;

public interface ActivationService {

    String ok = "ok";
    String notFound = "can't found user";
    String accessDenied = "be manager";

    Status deactivate(UserVo userVo, Token token);
    Status activate(UserVo userVo, Token token);
}
