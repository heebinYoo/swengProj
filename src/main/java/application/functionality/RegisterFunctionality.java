package application.functionality;

import application.functionality.core.Functionality;
import application.serviceInterface.user.RegisterUserService;
import database.vo.UserVo;
import service.response.Status;

public class RegisterFunctionality extends Functionality {

    public static final String reqnotempty = RegisterUserService.reqnotempty;
    public static final String isDuplicated = RegisterUserService.isDuplicated;
    public static final String isNotVaildEmail = RegisterUserService.isNotVaildEmail;
    public static final String ok = "ok";
    public static final String retry = "please retry";

    private RegisterUserService registerUserService;

    public RegisterFunctionality(RegisterUserService registerUserService) {
        this.registerUserService = registerUserService;
    }


    @Override
    protected void setUpStateGraph() {
        super.stateGraphBuilder(1);
    }


    public Status register(UserVo userVo){
        final int stage = 0;

        super.stateChange(stage);

        switch (registerUserService.register(userVo, token).getStatus()){
            case RegisterUserService.reqnotempty:
                return new Status(reqnotempty);
            case RegisterUserService.isDuplicated:
                return new Status(isDuplicated);
            case RegisterUserService.isNotVaildEmail:
                return new Status(isNotVaildEmail);
            case RegisterUserService.ok:
                return new Status(ok);
            default:
                return new Status(retry);
        }



    }
}
