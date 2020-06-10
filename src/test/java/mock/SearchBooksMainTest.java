package mock;

import bean.StatusedArrayList;
import bean.Token;
import org.junit.BeforeClass;
import org.junit.Test;

import static mock.SearchBooksMain.ok;
import static mock.SearchBooksMain.unAvailalbeMethod;
import static org.junit.Assert.*;

public class SearchBooksMainTest {
    static SearchBooksMain searchBooksMain;
    static Token token;
    @BeforeClass
    public static void setup(){
        searchBooksMain = new SearchBooksMain();
        token = new LoginMain().login("admin", "nayana");
    }


    @Test
    public void search() {
       StatusedArrayList statusedArrayList = searchBooksMain.search(SearchBooksMain.title, "k2938dkkdck", token);
       assertEquals(ok, statusedArrayList.status);
       assertTrue(statusedArrayList.data.isEmpty());
    }

    @Test
    public void title(){
        StatusedArrayList statusedArrayList = searchBooksMain.search(SearchBooksMain.title, "k2938dkkdck", token);
        assertEquals(ok, statusedArrayList.status);
        assertTrue(statusedArrayList.data.isEmpty());
    }
    @Test
    public void ISBN(){
        StatusedArrayList statusedArrayList = searchBooksMain.search(SearchBooksMain.ISBN, "k2938dkkdck", token);
        assertEquals(ok, statusedArrayList.status);
        assertTrue(statusedArrayList.data.isEmpty());
    }

    @Test
    public void writer(){
        StatusedArrayList statusedArrayList = searchBooksMain.search(SearchBooksMain.writer, "k2938dkkdck", token);
        assertEquals(ok, statusedArrayList.status);
        assertTrue(statusedArrayList.data.isEmpty());
    }

    @Test
    public void uid(){
        StatusedArrayList statusedArrayList = searchBooksMain.search(SearchBooksMain.uid, "k2938dkkdck", token);
        assertEquals(ok, statusedArrayList.status);
        assertTrue(statusedArrayList.data.isEmpty());
    }



    @Test
    public void unAvail(){
        assertEquals(searchBooksMain.search("fail method", "key", token).status, unAvailalbeMethod);

    }
}