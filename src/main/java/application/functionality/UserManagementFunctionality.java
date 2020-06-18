package application.functionality;

import application.Functionality;
import application.StateGraph;
import application.model.ListModel;
import database.vo.BookVo;
import database.vo.UserVo;
import service.book.SearchService;
import service.response.Status;
import service.response.StatusedArrayList;
import service.response.Token;
import service.user.ActivationService;
import service.user.DeleteService;
import service.user.GetListService;

public class UserManagementFunctionality extends Functionality {
    public static final String ok = "ok";
    public static final String notFound = "can't found user";
    public static final String retry = "please retry";
    public static final String accessDenied = "be manager";
    ListModel<UserVo> model;

    public UserManagementFunctionality() {
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

        StatusedArrayList<UserVo> result = GetListService.getList(token);

        switch (result.getStatus()){
            case GetListService.accessDenied:
                throw new IllegalAccessError("UserManagementFunctionality : access denied doing GetListService.getList");
            case GetListService.ok:
                model.replaceData(result.getData());
        }

        return this.model;
    }

    public Status delete(UserVo userVo){
        final int stage = 1;

super.stateChange(stage);
        Status status = DeleteService.delete(userVo, token);

        switch (status.getStatus()){
            case DeleteService.accessDenied:
                throw new IllegalAccessError("UserManagementFunctionality : access denied doing DeleteService.delete");
            case DeleteService.notFound:
                return new Status(this.notFound);
            case DeleteService.ok:
                return new Status(this.ok);
            default:
                return new Status(retry);

        }

    }


    public Status activation(UserVo userVo){
        final int stage = 2;

super.stateChange(stage);

        Status status = ActivationService.activate(userVo, token);

        switch (status.getStatus()){
            case ActivationService.accessDenied:
                throw new IllegalAccessError("UserManagementFunctionality : access denied doing ActivationService.activate");
            case ActivationService.notFound:
                return new Status(this.notFound);
            case ActivationService.ok:
                return new Status(this.ok);
            default:
                return new Status(retry);

        }


    }
    public Status deactivation(UserVo userVo){
        final int stage = 3;

super.stateChange(stage);

        Status status = ActivationService.deactivate(userVo, token);

        switch (status.getStatus()){
            case ActivationService.accessDenied:
                throw new IllegalAccessError("UserManagementFunctionality : access denied doing ActivationService.deactivate");
            case ActivationService.notFound:
                return new Status(this.notFound);
            case ActivationService.ok:
                return new Status(this.ok);
            default:
                return new Status(retry);

        }

    }

}
