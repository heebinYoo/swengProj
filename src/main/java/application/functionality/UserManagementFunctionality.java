package application.functionality;

import application.functionality.core.Functionality;
import application.model.ListModel;
import application.serviceInterface.user.ActivationService;
import application.serviceInterface.user.DeleteUserService;
import application.serviceInterface.user.GetListService;
import database.vo.UserVo;
import service.response.Status;
import service.response.StatusedArrayList;
import service.user.ActivationServiceImpl;
import service.user.DeleteUserServiceImpl;
import service.user.GetListServiceImpl;

public class UserManagementFunctionality extends Functionality {
    public static final String ok = "ok";
    public static final String notFound = "can't found user";
    public static final String retry = "please retry";
    public static final String accessDenied = "be manager";
    private ListModel<UserVo> model;

    private GetListService getListService;
    private DeleteUserService deleteUserService;
    private ActivationService activationService;


    public UserManagementFunctionality(GetListService getListService, DeleteUserService deleteUserService, ActivationService activationService) {
        this.getListService = getListService;
        this.deleteUserService = deleteUserService;
        this.activationService = activationService;

        model = new ListModel<>();
        model.replaceData(null);

    }

    @Override
    protected void setUpStateGraph() {
        super.stateGraphBuilder(4);
        super.putEdge(0, 1);
        super.putEdge(0, 2);
        super.putEdge(0, 3);

        super.putEdge(1, 1);
        super.putEdge(1, 2);
        super.putEdge(1, 3);

        super.putEdge(2, 1);
        super.putEdge(2, 2);
        super.putEdge(2, 3);

        super.putEdge(3, 1);
        super.putEdge(3, 2);
        super.putEdge(3, 3);
    }


    public ListModel<UserVo> getUserList(){
        final int stage = 0;

        super.stateChange(stage);

        StatusedArrayList<UserVo> result = getListService.getList(token);

        switch (result.getStatus()){
            case GetListServiceImpl.accessDenied:
                throw new IllegalAccessError("UserManagementFunctionality : access denied doing GetListService.getList");
            case GetListServiceImpl.ok:
                model.replaceData(result.getData());
        }

        return this.model;
    }

    public Status delete(UserVo userVo){
        final int stage = 1;

        super.stateChange(stage);
        Status status = deleteUserService.delete(userVo, token);

        switch (status.getStatus()){
            case DeleteUserServiceImpl.accessDenied:
                throw new IllegalAccessError("UserManagementFunctionality : access denied doing DeleteService.delete");
            case DeleteUserServiceImpl.notFound:
                return new Status(this.notFound);
            case DeleteUserServiceImpl.ok:
                return new Status(this.ok);
            default:
                return new Status(retry);

        }

    }


    public Status activation(UserVo userVo){
        final int stage = 2;

        super.stateChange(stage);

        Status status = activationService.activate(userVo, token);

        switch (status.getStatus()){
            case ActivationServiceImpl.accessDenied:
                throw new IllegalAccessError("UserManagementFunctionality : access denied doing ActivationService.activate");
            case ActivationServiceImpl.notFound:
                return new Status(this.notFound);
            case ActivationServiceImpl.ok:
                return new Status(this.ok);
            default:
                return new Status(retry);

        }


    }
    public Status deactivation(UserVo userVo){
        final int stage = 3;

        super.stateChange(stage);

        Status status = activationService.deactivate(userVo, token);

        switch (status.getStatus()){
            case ActivationServiceImpl.accessDenied:
                throw new IllegalAccessError("UserManagementFunctionality : access denied doing ActivationService.deactivate");
            case ActivationServiceImpl.notFound:
                return new Status(this.notFound);
            case ActivationServiceImpl.ok:
                return new Status(this.ok);
            default:
                return new Status(retry);

        }

    }

}
