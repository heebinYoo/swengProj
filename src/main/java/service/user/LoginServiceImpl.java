package service.user;

import application.serviceInterface.user.LoginService;
import service.UserDAO;
import service.response.Token;

import database.vo.UserVo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LoginServiceImpl implements LoginService {

    private UserDAO userDAO;
    public LoginServiceImpl(UserDAO userDAO){
        this.userDAO=userDAO;
    }



    public Token login(String id, String pw, Token token){
        String dbms_addr = "192.168.0.13/sweng";
        String dbms_url = "jdbc:mysql://"+dbms_addr+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        UserVo userVo;
        try {
            userVo = userDAO.login(id, pw, token.getConn());
        } catch (SQLException throwables) {
            return new Token(null, null, throwables.toString());
        }

        if(userVo==null){
            return new Token(null, null, Token.notFound);
        }
        else if(userVo.isManage()){

            Connection conn = null;
            try {
                conn = DriverManager.getConnection(dbms_url, "admin", "nayana");
            } catch (SQLException throwables) {
                return new Token(null, null, throwables.toString());
            }
            return new Token(userVo, conn, Token.manager);
        }
        else if(!userVo.isActive()){
            return new Token(null, null, Token.deactivated);
        }
        else{
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(dbms_url, "normal", "normal");
            } catch (SQLException throwables) {
                return new Token(null, null, throwables.toString());
            }
            return new Token(userVo, conn, Token.ok);
        }


    }
}

