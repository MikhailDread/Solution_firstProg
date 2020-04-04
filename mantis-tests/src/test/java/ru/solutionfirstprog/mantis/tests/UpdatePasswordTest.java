package ru.solutionfirstprog.mantis.tests;

import org.testng.annotations.Test;
import java.io.IOException;



public class UpdatePasswordTest extends TestBase{

    @Test
    public void testUpdatePassword() throws IOException {
        applicationManager.updatePass().login();
        applicationManager.updatePass().searchUserList();
    }
}
