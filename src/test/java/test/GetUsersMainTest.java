package test;

import bean.Token;
import mock.GetUsersMain;
import mock.LoginMain;
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