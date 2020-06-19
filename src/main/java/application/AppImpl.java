package application;

import application.functionality.*;
import presenter.App;
import service.ServiceManager;

public class AppImpl implements App {
    @Override
    public LoginFunctionality getLoginFunctionality() {
        return new LoginFunctionality(ServiceManager.getLoginServiceInstance());
    }

    @Override
    public BookManagerFunctionality getBookManagerFunctionality() {
        return new BookManagerFunctionality(ServiceManager.getSearchServiceInstance(), ServiceManager.getDeleteBookServiceInstance());
    }

    @Override
    public BookBuyFunctionality getBookBuyFunctionality() {
        return new BookBuyFunctionality(ServiceManager.getBuildTradeServiceInstance(), ServiceManager.getMailServiceInstance(), ServiceManager.getSearchServiceInstance());
    }


    @Override
    public RegisterFunctionality getRegisterFunctionality() {
        return new RegisterFunctionality(ServiceManager.getRegisterUserServiceInstance());
    }

    @Override
    public BookRegisterFunctionality getBookRegisterFunctionality() {
        return new BookRegisterFunctionality(ServiceManager.getRegisterBookServiceInstance());
    }

    @Override
    public MyBookFunctionality getMyBookFunctionalityFunctionality() {
        return new MyBookFunctionality(ServiceManager.getSearchServiceInstance(), ServiceManager.getDeleteBookServiceInstance(), ServiceManager.getUpdateServiceInstance());
    }

    @Override
    public UserManagementFunctionality getUserManagementFunctionality() {
        return new UserManagementFunctionality(ServiceManager.getGetListServiceInstance(), ServiceManager.getDeleteUserServiceInstance(), ServiceManager.getActivationServiceInstance());
    }
}
