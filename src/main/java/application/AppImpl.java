package application;

import application.functionality.*;
import presenter.App;

public class AppImpl implements App {
    @Override
    public LoginFunctionality getLoginFunctionality() {
        return new LoginFunctionality();
    }

    @Override
    public BookManagerFunctionality getBookManagerFunctionality() {
        return new BookManagerFunctionality();
    }

    @Override
    public BookBuyFunctionality getBookBuyFunctionality() {
        return new BookBuyFunctionality();
    }


    @Override
    public RegisterFunctionality getRegisterFunctionality() {
        return new RegisterFunctionality();
    }

    @Override
    public BookRegisterFunctionality getBookRegisterFunctionality() {
        return new BookRegisterFunctionality();
    }

    @Override
    public MyBookFunctionality getMyBookFunctionalityFunctionality() {
        return new MyBookFunctionality();
    }

    @Override
    public UserManagementFunctionality getUserManagementFunctionality() {
        return new UserManagementFunctionality();
    }
}
