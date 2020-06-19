package service;

import application.serviceInterface.book.DeleteBookService;
import application.serviceInterface.book.RegisterBookService;
import application.serviceInterface.book.SearchService;
import application.serviceInterface.book.UpdateService;
import application.serviceInterface.trade.BuildTradeService;
import application.serviceInterface.trade.MailService;
import application.serviceInterface.user.*;
import database.DAOManager;
import service.book.DeleteBookServiceImpl;
import service.book.RegisterBookServiceImpl;
import service.book.SearchServiceImpl;
import service.book.UpdateServiceImpl;
import service.trade.BuildTradeServiceImpl;
import service.trade.MailServiceImpl;
import service.user.*;

public class ServiceManager {
    private static class LazyHolder {
        public static final DeleteBookService deleteBookService = new DeleteBookServiceImpl(DAOManager.getBookDAOInstance());
        public static final RegisterBookService registerBookService = new RegisterBookServiceImpl(DAOManager.getBookDAOInstance());
        public static final SearchService searchService = new SearchServiceImpl(DAOManager.getBookDAOInstance());
        public static final UpdateService updateService = new UpdateServiceImpl(DAOManager.getBookDAOInstance());

        public static final BuildTradeService buildTradeService = new BuildTradeServiceImpl(DAOManager.getUserDAOInstance());
        public static final MailService mailService = new MailServiceImpl();

        public static final ActivationService activationService = new ActivationServiceImpl(DAOManager.getUserDAOInstance());
        public static final DeleteUserService deleteUserService = new DeleteUserServiceImpl(DAOManager.getUserDAOInstance());
        public static final GetListService getListService = new GetListServiceImpl(DAOManager.getUserDAOInstance());
        public static final LoginService loginService = new LoginServiceImpl(DAOManager.getUserDAOInstance());
        public static final RegisterUserService registerUserService = new RegisterUserServiceImpl(DAOManager.getUserDAOInstance());

    }

    public static DeleteBookService getDeleteBookServiceInstance(){
        return LazyHolder.deleteBookService;
    }
    public static RegisterBookService getRegisterBookServiceInstance(){
        return LazyHolder.registerBookService;
    }
    public static SearchService getSearchServiceInstance(){
        return LazyHolder.searchService;
    }
    public static UpdateService getUpdateServiceInstance(){
        return LazyHolder.updateService;
    }

    public static BuildTradeService getBuildTradeServiceInstance(){
        return LazyHolder.buildTradeService;
    }
    public static MailService getMailServiceInstance(){
        return LazyHolder.mailService;
    }

    public static ActivationService getActivationServiceInstance(){
        return LazyHolder.activationService;
    }
    public static DeleteUserService getDeleteUserServiceInstance(){
        return LazyHolder.deleteUserService;
    }
    public static GetListService getGetListServiceInstance(){
        return LazyHolder.getListService;
    }
    public static LoginService getLoginServiceInstance(){
        return LazyHolder.loginService;
    }
    public static RegisterUserService getRegisterUserServiceInstance(){
        return LazyHolder.registerUserService;
    }



}
