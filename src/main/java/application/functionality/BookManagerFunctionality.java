package application.functionality;

import application.functionality.core.Functionality;
import application.model.ListModel;
import application.serviceInterface.book.DeleteBookService;
import application.serviceInterface.book.SearchService;
import database.vo.BookVo;
import service.response.Status;
import service.response.StatusedArrayList;

public class BookManagerFunctionality extends Functionality {
    private ListModel<BookVo> model;

    private SearchService searchService;
    private DeleteBookService deleteBookService;


    public static final String ok = "ok";
    public static final String retry = "please retry";


    public BookManagerFunctionality(SearchService searchService, DeleteBookService deleteBookService) {
        this.searchService = searchService;
        this.deleteBookService = deleteBookService;

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

        StatusedArrayList<BookVo> result = searchService.search(key, method, token);

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


        Status result = deleteBookService.delete(bookVo, token);

        switch (result.getStatus()){
            case DeleteBookService.accessDenied:
                throw new IllegalAccessError();
            case DeleteBookService.ok:
                model.deleteElement(bookVo);
                return new Status(ok);
            case DeleteBookService.unknown:
            default:
                return new Status(retry);
        }


    }




}
