package ru.solutionfirstprog.mantis.tests;

import org.testng.annotations.Test;

public class RegistrationTest extends TestBase{

    @Test
    public void testRegistration(){
        applicationManager.registration().start("user1", "user2");
    }
}
