package ru.solutionfirstprog.addressbook.tests;

import org.testng.annotations.Test;
import ru.solutionfirstprog.addressbook.module.ContactIng;


public class ContactModification extends TestBase {

    @Test
    public void testContactModification(){

        applicationManager.getReturnHelper().returnHome();
        if(!applicationManager.getContactHelper().thereAContact()){
            applicationManager.getNavigationClass().gotoNewContact();
            applicationManager.getContactHelper().createContact();
            applicationManager.getReturnHelper().gotoHomePage();
        }
        applicationManager.getContactHelper().tookContactModification();
        applicationManager.getContactHelper().createContactInf(new ContactIng("RZD", "Moscow, street Tambovskaya.", "jonjolli@yandex.fu", "Ivan", "Ivanovich", "Ivanov", null), false);
        applicationManager.getContactHelper().submitContactModification();
        applicationManager.getReturnHelper().gotoHomePage();
    }
}
