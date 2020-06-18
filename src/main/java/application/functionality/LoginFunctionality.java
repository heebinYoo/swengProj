package application.functionality;

import application.Functionality;
import application.StateGraph;
import application.TokenHolder;
import service.response.Status;
import service.response.Token;
import service.user.LoginService;

public class LoginFunctionality extends Functionality {
    public static final String ok = "ok";
    public static final String retry = "please retry";
    public static final String deactivated = "it's de activated";

    @Override
    protected void setUpStateGraph() {
        stateGraphBuilder(1);
        super.putEdge(0,0);
    }

    public Status login(String id, String pw){
        final int stage = 0;
        super.stateChange(stage);

        Token result = LoginService.login(id, pw, token);
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