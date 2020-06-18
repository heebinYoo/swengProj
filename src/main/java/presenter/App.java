package presenter;

import application.functionality.*;

public interface App {
    LoginFunctionality getLoginFunctionality();
    BookManagerFunctionality getBookManagerFunctionality();
    BookBuyFunctionality getBookBuyFunctionality();
    RegisterFunctionality getRegisterFunctionality();
    BookRegisterFunctionality getBookRegisterFunctionality();
    MyBookFunctionality getMyBookFunctionalityFunctionality();
    UserManagementFunctionality getUserManagementFunctionality();


}
