package database;


import database.dao.BookDAOsqlImpl;
import database.dao.UserDAOsqlImpl;
import service.BookDAO;
import service.UserDAO;

public class DAOManager {
    private static class LazyHolder {
        public static final UserDAO userDAO = new UserDAOsqlImpl();
        public static final BookDAO bookDAO = new BookDAOsqlImpl();
    }

    public static UserDAO getUserDAOInstance(){
        return DAOManager.LazyHolder.userDAO;
    }
    public static BookDAO getBookDAOInstance(){
        return DAOManager.LazyHolder.bookDAO;
    }
}
