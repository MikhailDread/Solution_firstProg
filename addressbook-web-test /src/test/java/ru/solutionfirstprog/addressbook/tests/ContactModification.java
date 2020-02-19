package ru.solutionfirstprog.addressbook.tests;

import org.testng.annotations.Test;
import ru.solutionfirstprog.addressbook.module.ContactIng;

public class ContactModification extends TestBase {

    @Test
    public void testContactModification(){

        applicationManager.getReturnHelper().returnHome();
        applicationManager.getContactHelper().tookContactModification();
        applicationManager.getContactHelper().submitContactModification();
        applicationManager.getReturnHelper().gotoHomePage();
    }
}
