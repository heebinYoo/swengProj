package mock;

import bean.StatusedArrayList;
import bean.Token;
import bean.UserVo;

import java.sql.SQLException;

public class DeleteUserMain {
    public static final String ok = "ok";
    public static final String not = "can't delete it";
    public static final String manager = "it's manager";
    public static final String accessDenied = "you should be a manager";


    public String deleteUser(UserVo userVo, Token token){
        if(!token.getStatus().equals(Token.manager)){
            return accessDenied;
        }
        else if(userVo.manage==true){
            return manager;
        }
        try {
            return DBUtilities.deleteUser(userVo, token.getConn()) ? ok : not;
        } catch (SQLException throwables) {
            return throwables.toString();
        }

    }



}
