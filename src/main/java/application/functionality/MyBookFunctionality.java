package application.functionality;

import application.Functionality;
import application.StateGraph;
import application.model.ListModel;
import database.vo.BookVo;
import service.book.DeleteService;
import service.book.SearchService;
import service.book.UpdateService;
import service.response.Status;
import service.response.StatusedArrayList;
import service.response.Token;

public class MyBookFunctionality extends Functionality {

    public static final String ok = "ok";
    public static final String retry = "please retry";

    ListModel<BookVo> model;
    MyBookFunctionality(){
        model = new ListModel<>();
        model.replaceData(null);
    }

    @Override
    protected void setUpStateGraph() {
        super.stateGraphBuilder(3);
        super.putEdge(0, 1);
        super.putEdge(0, 2);

        super.putEdge(1, 1);
        super.putEdge(1, 2);
        super.putEdge(2, 1);
        super.putEdge(2, 2);
    }


    public ListModel<BookVo> getMyBook(){
        final int stage = 0;

super.stateChange(stage);

        StatusedArrayList<BookVo> result = SearchService.search(token.getData().getId(), SearchService.uid, token);

        switch (result.getStatus()){
            case SearchService.accessDenied:
                throw new IllegalAccessError("MyBookFunctionality : access denied doing search");
            case SearchService.ok:
                model.replaceData(result.getData());
        }

        return this.model;
    }


    public Status update(BookVo bookVo){
        final int stage = 1;

super.stateChange(stage);

        Status status = UpdateService.update(bookVo, token);
        switch (status.getStatus()){
            case UpdateService.accessDenied:
                throw new IllegalAccessError("MyBookFunctionality : access denied doing UpdateService.update");
            case UpdateService.ok:
                return new Status(this.ok);
            case UpdateService.unknown:
            default:
                return new Status(this.retry);
        }

    }

    public Status delete(BookVo bookVo){
        final int stage = 2;

super.stateChange(stage);

        Status status = DeleteService.delete(bookVo, token);
        switch (status.getStatus()){
            case DeleteService.accessDenied:
                throw new IllegalAccessError("MyBookFunctionality : access denied doing DeleteService.delete");
            case DeleteService.ok:
                return new Status(this.ok);
            case DeleteService.unknown:
            default:
                return new Status(this.retry);
        }
    }



}
