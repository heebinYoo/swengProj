package mock;

import bean.UserVo;

import java.sql.SQLException;

public class RegisterMain {

    public static final String reqnotempty = "fields should not empty";
    public static final String isDuplicated = "isDuplicated id should reselect";
    public static final String isNotVaildEmail = "email is not vaild";
    public static final String ok = "ok";


    /**
     *
     * @param userVo : entered by user
     * @return response String : {reqnotempty, isDuplicated, throwables.getMessage() , isNotVaildEmail, ok}
     */
    public String enterInfo(UserVo userVo){

        if(!isNotEmpty(userVo))
            return reqnotempty;
        try {
            if(!isNotDuplicated(userVo.id))
                return isDuplicated;
        } catch (SQLException throwables) {
            return throwables.getMessage();
        }
        if(!isValidEmail(userVo.email)){
            return isNotVaildEmail;
        }


        try {
            Utilities.register(userVo);
        } catch (SQLException throwables) {

            return throwables.getMessage();
        }


        return ok;

    }

    private boolean isNotDuplicated(String ID) throws SQLException {
        return Utilities.isNotDuplicated(ID);
    }

    private boolean isNotEmpty(UserVo userVo){
        if(userVo.id.equals(""))
            return false;
        if(userVo.pw.equals(""))
            return false;
        if(userVo.name.equals(""))
            return false;
        if(userVo.phone.equals(""))
            return false;
        if(userVo.email.equals(""))
            return false;

        return true;
    }

    private boolean isValidEmail(String email){
        final String rex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

        return email.matches(rex);
    }


}
