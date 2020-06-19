package service.user;


import application.serviceInterface.user.DeleteUserService;
import database.vo.UserVo;
import service.UserDAO;
import service.response.Status;
import service.response.Token;

import java.sql.SQLException;

public class DeleteUserServiceImpl implements DeleteUserService {

    private UserDAO userDAO;
    public DeleteUserServiceImpl(UserDAO userDAO){
        this.userDAO=userDAO;
    }



    public Status delete(UserVo userVo, Token token){
        if(token.getStatus().equals(Token.manager)) {
            try {
                if (userDAO.delete(userVo, token.getConn())) {
                    return new Status(ok);
                } else {
                    return new Status(notFound);
                }
            } catch (SQLException throwables) {
                return new Status(throwables.toString());
            }
        } else {
            return new Status(accessDenied);
        }
    }
}
