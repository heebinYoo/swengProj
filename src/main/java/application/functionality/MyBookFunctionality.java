package application.functionality;

import application.functionality.core.Functionality;
import application.model.ListModel;
import application.serviceInterface.BookSearchMethod;
import application.serviceInterface.book.DeleteBookService;
import application.serviceInterface.book.SearchService;
import application.serviceInterface.book.UpdateService;
import database.vo.BookVo;
import service.response.Status;
import service.response.StatusedArrayList;

public class MyBookFunctionality extends Functionality {

    public static final String ok = "ok";
    public static final String retry = "please retry";

    private ListModel<BookVo> model;

    private SearchService searchService;
    private DeleteBookService deleteBookService;
    private UpdateService updateService;

    public MyBookFunctionality(SearchService searchService, DeleteBookService deleteBookService, UpdateService updateService){
        this.searchService = searchService;
        this.deleteBookService = deleteBookService;
        this.updateService = updateService;

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

        StatusedArrayList<BookVo> result = searchService.search(token.getData().getId(), BookSearchMethod.uid, token);

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

        Status status = updateService.update(bookVo, token);
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

        Status status = deleteBookService.delete(bookVo, token);
        switch (status.getStatus()){
            case DeleteBookService.accessDenied:
                throw new IllegalAccessError("MyBookFunctionality : access denied doing DeleteService.delete");
            case DeleteBookService.ok:
                return new Status(this.ok);
            case DeleteBookService.unknown:
            default:
                return new Status(this.retry);
        }
    }



}
