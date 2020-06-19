package service.user;


import application.serviceInterface.user.ActivationService;
import database.vo.UserVo;
import service.UserDAO;
import service.response.Status;
import service.response.Token;

import java.sql.SQLException;

public class ActivationServiceImpl implements ActivationService {



    private UserDAO userDAO;
    public ActivationServiceImpl(UserDAO userDAO){
        this.userDAO=userDAO;
    }



    public Status deactivate(UserVo userVo, Token token){
        if(token.getStatus().equals(Token.manager)) {
            try {
                if (userDAO.deactivate(userVo, token.getConn())) {
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

    public Status activate(UserVo userVo, Token token){
        if(token.getStatus().equals(Token.manager)) {
            try {
                if(userDAO.activate(userVo, token.getConn())){
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
