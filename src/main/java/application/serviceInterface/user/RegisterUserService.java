package application.serviceInterface.user;

import database.vo.UserVo;
import service.response.Status;
import service.response.Token;

public interface RegisterUserService {

    String reqnotempty = "fields should not empty";
    String isDuplicated = "isDuplicated id should reselect";
    String isNotVaildEmail = "email is not vaild";
    String ok = "ok";


    Status register(UserVo userVo, Token token);


}
