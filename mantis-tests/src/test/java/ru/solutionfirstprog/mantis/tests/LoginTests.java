package ru.solutionfirstprog.mantis.tests;

import org.testng.annotations.Test;
import ru.solutionfirstprog.mantis.appmanager.HttpSession;

import java.io.IOException;

import static org.testng.Assert.assertTrue;


public class LoginTests extends TestBase {
    @Test
    public void testLogin() throws IOException {
        HttpSession httpSession = applicationManager.httpSession();
        assertTrue(httpSession.login("administrator", "root"));
        assertTrue(httpSession.isLoggedInAs("administrator"));
    }
}
