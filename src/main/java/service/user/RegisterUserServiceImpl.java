package service.user;

import application.serviceInterface.user.RegisterUserService;
import service.UserDAO;
import service.response.Status;
import service.response.Token;

import database.vo.UserVo;

import java.sql.SQLException;

public class RegisterUserServiceImpl implements RegisterUserService {


    private UserDAO userDAO;
    public RegisterUserServiceImpl(UserDAO userDAO){
        this.userDAO=userDAO;
    }

    /**
     *
     * @param userVo : entered by user
     * @return response String : {reqnotempty, isDuplicated, throwables.getMessage() , isNotVaildEmail, ok}
     */
    public Status register(UserVo userVo, Token token){

        if(!isNotEmpty(userVo))
            return new Status(reqnotempty);
        try {
            if(!isNotDuplicated(userVo.getId(), token))
                return new Status(isDuplicated);
        } catch (SQLException throwables) {
            return new Status(throwables.getMessage());
        }
        if(!isValidEmail(userVo.getEmail())){
            return new Status(isNotVaildEmail);
        }


        try {
            userDAO.register(userVo, token.getConn());
        } catch (SQLException throwables) {
            return new Status(throwables.getMessage());
        }


        return new Status(ok);

    }

    private boolean isNotDuplicated(String id, Token token) throws SQLException {
        return userDAO.isNotDuplicated(id, token.getConn());
    }

    private boolean isNotEmpty(UserVo userVo){
        if(userVo.getId().equals(""))
            return false;
        if(userVo.getPw().equals(""))
            return false;
        if(userVo.getName().equals(""))
            return false;
        if(userVo.getPhone().equals(""))
            return false;
        if(userVo.getEmail().equals(""))
            return false;

        return true;
    }

    private boolean isValidEmail(String email){
        final String rex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

        return email.matches(rex);
    }


}
