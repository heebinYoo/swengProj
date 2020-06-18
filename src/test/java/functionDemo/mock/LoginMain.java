package functionDemo.mock;

import functionDemo.bean.Token;

import java.sql.SQLException;

public class LoginMain {

    public Token login(String id, String pw){
        Token token = null;
        try {
            token = DBUtilities.login(id, pw);
        } catch (SQLException throwables) {
            token = new Token(null, null, throwables.toString());
        }
        return token;
    }

}
