package application.serviceInterface.user;

import service.response.Token;

public interface LoginService {

    Token login(String id, String pw, Token token);

}
