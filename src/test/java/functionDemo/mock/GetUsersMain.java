package functionDemo.mock;

import functionDemo.bean.StatusedArrayList;
import functionDemo.bean.Token;
import functionDemo.bean.UserVo;

import java.sql.SQLException;



public class GetUsersMain {
    public static final String ok = "ok";
    public static final String accessDenied = "you should be a manager";


    public StatusedArrayList<UserVo> getUsers(Token token) {
        if(!token.getStatus().equals(Token.manager)){
            return new StatusedArrayList<>(null, accessDenied);
        }


        StatusedArrayList<UserVo> statusedArrayList = null;
        try {
            statusedArrayList =new StatusedArrayList(DBUtilities.getUsers(token.getConn()), ok);

        } catch (SQLException throwables) {
            statusedArrayList = new StatusedArrayList(null, throwables.toString());
        }

        return statusedArrayList;
    }


}
