package application.functionality;

import application.Functionality;
import application.StateGraph;
import application.model.ListModel;
import database.dao.BookDAO;
import database.vo.BookVo;
import service.book.SearchService;
import service.response.Status;
import service.response.StatusedArrayList;
import service.response.Token;
import service.response.Trade;
import service.trade.BuildTradeService;
import service.trade.MailService;

public class BookBuyFunctionality extends Functionality {
    ListModel<BookVo> model;

    public static final String ok = "ok";
    public static final String retry = "please retry";

    public static final String title = SearchService.title;
    public static final String ISBN = SearchService.ISBN;
    public static final String writer = SearchService.writer;
    public static final String pYear = SearchService.pYear;
    public static final String uid = SearchService.uid;



    BookBuyFunctionality(){
        model = new ListModel<>();
        model.replaceData(null);
    }

    @Override
    protected void setUpStateGraph() {
        super.stateGraphBuilder(3);
        super.putEdge(0, 1);
        super.putEdge(1, 2);
        super.putEdge(2, 1);
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
    public Trade offer(BookVo bookVo){
        final int stage = 1;

        super.stateChange(stage);


        Trade result = BuildTradeService.buildTrade(bookVo, token.getData(), token);
        switch (result.getStatus()){
            case SearchService.accessDenied:
                throw new IllegalAccessError();
            case SearchService.ok:
                return result;
            default:
                return new Trade(retry,null, null, -1);

        }

    }
    public Status doTransaction(Trade trade){
        final int stage = 2;

        super.stateChange(stage);
        Status status = MailService.send(trade, token);

        //TODO : maybe after this project

        return new Status(this.ok);

    }






}
