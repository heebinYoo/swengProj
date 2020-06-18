package functionDemo.test;

import functionDemo.bean.Token;
import functionDemo.mock.GetUsersMain;
import functionDemo.mock.LoginMain;
import org.junit.Test;

import static org.junit.Assert.*;

public class GetUsersMainTest {

    @Test
    public void getUsers() {
        Token token = new LoginMain().login("admin", "nayana");
        GetUsersMain getUsersMain = new GetUsersMain();
        assertEquals(GetUsersMain.ok, getUsersMain.getUsers(token));


    }
}