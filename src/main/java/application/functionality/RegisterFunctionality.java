package application.functionality;

import application.Functionality;
import application.StateGraph;
import database.vo.UserVo;
import service.response.Status;
import service.response.Token;
import service.user.RegisterService;

public class RegisterFunctionality extends Functionality {

    public static final String reqnotempty =RegisterService.reqnotempty;
    public static final String isDuplicated = RegisterService.isDuplicated;
    public static final String isNotVaildEmail = RegisterService.isNotVaildEmail;
    public static final String ok = "ok";
    public static final String retry = "please retry";


    @Override
    protected void setUpStateGraph() {
        super.super.stateGraphBuilder(1);
    }


    public Status register(UserVo userVo){
        final int stage = 0;

super.stateChange(stage);

        switch (RegisterService.register(userVo, token).getStatus()){
            case RegisterService.reqnotempty:
                return new Status(reqnotempty);
            case RegisterService.isDuplicated:
                return new Status(isDuplicated);
            case RegisterService.isNotVaildEmail:
                return new Status(isNotVaildEmail);
            case RegisterService.ok:
                return new Status(ok);
            default:
                return new Status(retry);
        }



    }
}
