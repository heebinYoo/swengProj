package application.functionality;

import application.functionality.core.Functionality;
import application.model.ListModel;
import application.serviceInterface.book.SearchService;
import application.serviceInterface.trade.BuildTradeService;
import application.serviceInterface.trade.MailService;
import database.vo.BookVo;
import service.book.SearchServiceImpl;
import service.response.Status;
import service.response.StatusedArrayList;
import service.response.Trade;
import service.trade.BuildTradeServiceImpl;
import service.trade.MailServiceImpl;

public class BookBuyFunctionality extends Functionality {
    ListModel<BookVo> model;

    public static final String ok = "ok";
    public static final String retry = "please retry";

    private BuildTradeService buildTradeService;
    private MailService mailService;
    private SearchService searchService;

    public BookBuyFunctionality(BuildTradeService buildTradeService, MailService mailService, SearchService searchService){

        this.buildTradeService = buildTradeService;
        this.mailService = mailService;
        this.searchService = searchService;

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

        StatusedArrayList<BookVo> result = searchService.search(key, method, token);

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


        Trade result = buildTradeService.buildTrade(bookVo, token.getData(), token);
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
        Status status = mailService.send(trade, token);

        //TODO : maybe after this project

        return new Status(this.ok);

    }






}
