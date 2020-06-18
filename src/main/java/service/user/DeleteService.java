package service.user;

import database.dao.UserDAO;
import database.vo.UserVo;
import service.response.Status;
import service.response.Token;

import java.sql.SQLException;

public class DeleteService {
    public static final String ok = "ok";
    public static final String notFound = "can't found user";
    public static final String accessDenied = "be manager";
    public static Status delete(UserVo userVo, Token token){
        if(token.getStatus().equals(Token.manager)) {
            try {
                if (UserDAO.delete(userVo, token.getConn())) {
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
