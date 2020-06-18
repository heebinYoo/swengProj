package application.functionality;

import application.Functionality;
import application.StateGraph;
import database.vo.BookVo;
import service.book.RegisterService;
import service.response.Status;
import service.response.Token;

public class BookRegisterFunctionality extends Functionality {

    public static final String ok = "ok";
    public static final String retry = "please retry";

    @Override
    protected void setUpStateGraph() {
        super.stateGraphBuilder(1);
    }

    public Status registerBook(BookVo bookVo){
        final int stage = 0;

super.stateChange(stage);
        Status status = RegisterService.register(bookVo, token);

        switch (status.getStatus()){
            case RegisterService.accessDenied:
                throw new IllegalAccessError();
            case RegisterService.ok:
                return new Status(ok);
            case RegisterService.unknown:
            default:
                return new Status(retry);
        }


    }



}
