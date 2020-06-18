package application;

import service.response.Token;

import java.sql.SQLException;

public class TokenHolder {
    private static Token token;

    static{
        reset();
    }
    public static void logout(){
        reset();
    }

    public static Token getToken() {
        return token;
    }
    public static void setToken(Token token) {
        TokenHolder.token = token;
    }

    private static void reset(){
        try {
            token = new Token();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.err.println("TokenHolder : making default token but database is not available");
            System.exit(-1);
        }
    }
}
