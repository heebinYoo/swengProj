package application.functionality;

import application.Functionality;
import application.StateGraph;
import application.model.ListModel;
import database.dao.BookDAO;
import database.vo.BookVo;
import service.book.DeleteService;
import service.book.SearchService;
import service.response.Status;
import service.response.StatusedArrayList;
import service.response.Token;

public class BookManagerFunctionality extends Functionality {
    ListModel<BookVo> model;

    public static final String retry = "please retry";

    public static final String title = SearchService.title;
    public static final String ISBN = SearchService.ISBN;
    public static final String writer = SearchService.writer;
    public static final String pYear = SearchService.pYear;
    public static final String uid = SearchService.uid;


    public BookManagerFunctionality() {
        model = new ListModel<>();
        model.replaceData(null);
    }

    @Override
    protected void setUpStateGraph() {
        super.stateGraphBuilder(2);
        super.putEdge(0, 1);
        super.putEdge(1, 1);
    }

    public ListModel<BookVo> search(String key, String method){
        final int stage = 0;

super.stateChange(stage);

        StatusedArrayList<BookVo> result = SearchService.search(key, method, token);

        switch (result.getStatus()){
            case SearchService.accessDenied:
                throw new IllegalAccessError();

            case SearchService.ok:
                model.replaceData(result.getData());
        }

        return this.model;
    }


    public Status deleteBook(BookVo bookVo){
        final int stage = 1;

super.stateChange(stage);


        Status result = DeleteService.delete(bookVo, token);

        switch (result.getStatus()){
            case DeleteService.accessDenied:
                throw new IllegalAccessError();
            case DeleteService.ok:
                model.deleteElement(bookVo);
                return new Status(result.getStatus());
            case DeleteService.unknown:
            default:
                return new Status(retry);
        }


    }




}
