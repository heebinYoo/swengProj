package application.functionality;

import application.functionality.core.Functionality;
import application.serviceInterface.book.RegisterBookService;
import database.vo.BookVo;
import service.response.Status;

public class BookRegisterFunctionality extends Functionality {

    public static final String ok = "ok";
    public static final String retry = "please retry";

    private RegisterBookService registerBookService;

    public BookRegisterFunctionality(RegisterBookService registerBookService){
        this.registerBookService = registerBookService;
    }

    @Override
    protected void setUpStateGraph() {
        super.stateGraphBuilder(1);
    }

    public Status registerBook(BookVo bookVo){
        final int stage = 0;

super.stateChange(stage);
        Status status = registerBookService.register(bookVo, token);

        switch (status.getStatus()){
            case RegisterBookService.accessDenied:
                throw new IllegalAccessError();
            case RegisterBookService.ok:
                return new Status(ok);
            case RegisterBookService.unknown:
            default:
                return new Status(retry);
        }


    }



}
