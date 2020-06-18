package service.user;

import database.dao.UserDAO;
import database.vo.UserVo;
import service.response.Status;
import service.response.Token;

import java.sql.SQLException;

public class ActivationService {
    public static final String ok = "ok";
    public static final String notFound = "can't found user";
    public static final String accessDenied = "be manager";
    public static Status deactivate(UserVo userVo, Token token){
        if(token.getStatus().equals(Token.manager)) {
            try {
                if (UserDAO.deactivate(userVo, token.getConn())) {
                    return new Status(ok);
                } else {
                    return new Status(notFound);
                }
            } catch (SQLException throwables) {
                return new Status(throwables.toString());
            }
        }
        else{
            return new Status(accessDenied);
        }
    }

    public static Status activate(UserVo userVo, Token token){
        if(token.getStatus().equals(Token.manager)) {
            try {
                if(UserDAO.activate(userVo, token.getConn())){
                    return new Status(ok);
                }
                else{
                    return new Status(notFound);
                }
            } catch (SQLException throwables) {
                return new Status(throwables.toString());
            }
        } else{
            return new Status(accessDenied);
        }
    }
}
