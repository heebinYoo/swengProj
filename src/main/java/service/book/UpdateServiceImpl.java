package service.book;

import application.serviceInterface.book.UpdateService;

import database.vo.BookVo;
import service.BookDAO;
import service.response.Status;
import service.response.Token;

import java.sql.SQLException;

public class UpdateServiceImpl implements UpdateService {


    private BookDAO bookDAO;
    public UpdateServiceImpl(BookDAO bookDAO){
        this.bookDAO=bookDAO;
    }


    public Status update(BookVo bookVo, Token token){
        if(token.getData().getId() == bookVo.getUid() || token.getStatus().equals(Token.manager)) {
            boolean resultIsGood = false;
            try {
                resultIsGood = bookDAO.update(bookVo, token.getConn());

            } catch (SQLException throwables) {
                return new Status(throwables.toString());
            }
            if(resultIsGood)
                return new Status(ok);
            else
                return new Status(unknown);
        }
        else {
            return new Status(accessDenied);
        }



    }
}