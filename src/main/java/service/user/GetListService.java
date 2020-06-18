package service.user;

import service.response.StatusedArrayList;
import service.response.Token;
import database.dao.UserDAO;
import database.vo.UserVo;

import java.sql.SQLException;

public class GetListService {

    public static final String ok = "ok";
    public static final String accessDenied = "you should be a manager";


    public static StatusedArrayList<UserVo> getList(Token token) {
        if(!token.getStatus().equals(Token.manager)){
            return new StatusedArrayList<>(null, accessDenied);
        }

        StatusedArrayList<UserVo> statusedArrayList = null;
        try {
            statusedArrayList =new StatusedArrayList(UserDAO.getList(token.getConn()), ok);

        } catch (SQLException throwables) {
            statusedArrayList = new StatusedArrayList(null, throwables.toString());
        }

        return statusedArrayList;
    }
}
