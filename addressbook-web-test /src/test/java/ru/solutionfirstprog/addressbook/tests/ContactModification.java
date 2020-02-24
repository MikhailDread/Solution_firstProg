package ru.solutionfirstprog.addressbook.tests;

import org.testng.annotations.Test;
import ru.solutionfirstprog.addressbook.module.ContactIng;
import ru.solutionfirstprog.addressbook.module.GroupInf;

public class ContactModification extends TestBase {

    @Test
    public void testContactModification(){

        applicationManager.getReturnHelper().returnHome();
        if(! applicationManager.getContactHelper().thereAContact()){
            applicationManager.getNavigationClass().gotoNewContact();
            applicationManager.getContactHelper().createContact(new ContactIng("RZD", "Moscow, street Tambovskaya.", "jonjolli@yandex.fu", "Ivan", "Ivanovich", "Ivanov", "test1"));
            if(!applicationManager.getGroupHelper().isThereAGroup()){
                applicationManager.getGroupHelper().createGroup(new GroupInf("test1", "test2", "test3"));
            }
            applicationManager.getReturnHelper().gotoHomePage();
        }
        applicationManager.getContactHelper().tookContactModification();
        applicationManager.getContactHelper().createContactInf(new ContactIng("RZD", "Moscow, street Tambovskaya.", "jonjolli@yandex.fu", "Ivan", "Ivanovich", "Ivanov", null), false);
        applicationManager.getContactHelper().submitContactModification();
        applicationManager.getReturnHelper().gotoHomePage();
    }
}
