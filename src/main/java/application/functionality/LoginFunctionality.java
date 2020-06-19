package application.functionality;

import application.TokenHolder;
import application.functionality.core.Functionality;
import application.serviceInterface.user.LoginService;
import service.response.Status;
import service.response.Token;

public class LoginFunctionality extends Functionality {
    public static final String ok = "ok";
    public static final String retry = "please retry";
    public static final String deactivated = "it's de activated";

    private LoginService loginService;

    public LoginFunctionality(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    protected void setUpStateGraph() {
        stateGraphBuilder(1);
        super.putEdge(0,0);
    }

    public Status login(String id, String pw){
        final int stage = 0;
        super.stateChange(stage);

        Token result = loginService.login(id, pw, token);
        switch (result.getStatus()) {
            case Token.ok:
            case Token.manager:
                TokenHolder.setToken(result);
                return new Status(ok);
            case Token.deactivated:
                return new Status(deactivated);
            case Token.notFound:
            default:
                return new Status(retry);


        }

    }


}
