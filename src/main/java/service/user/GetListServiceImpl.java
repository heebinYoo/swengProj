package service.user;

import application.serviceInterface.user.GetListService;
import service.UserDAO;
import service.response.StatusedArrayList;
import service.response.Token;

import database.vo.UserVo;

import java.sql.SQLException;

public class GetListServiceImpl implements GetListService {

    private UserDAO userDAO;
    public GetListServiceImpl(UserDAO userDAO){
        this.userDAO=userDAO;
    }





    public StatusedArrayList<UserVo> getList(Token token) {
        if(!token.getStatus().equals(Token.manager)){
            return new StatusedArrayList<>(null, accessDenied);
        }

        StatusedArrayList<UserVo> statusedArrayList = null;
        try {
            statusedArrayList =new StatusedArrayList(userDAO.getList(token.getConn()), ok);

        } catch (SQLException throwables) {
            statusedArrayList = new StatusedArrayList(null, throwables.toString());
        }

        return statusedArrayList;
    }
}
