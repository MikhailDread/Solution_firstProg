package ru.solutionfirstprog.addressbook.tests;

import org.testng.annotations.Test;
import ru.solutionfirstprog.addressbook.module.ContactIng;
import ru.solutionfirstprog.addressbook.module.GroupInf;

public class ContactModification extends TestBase {

    @Test
    public void testContactModification(){

        applicationManager.getReturnHelper().returnHome();
        if(!applicationManager.getContactHelper().thereAContact()){  //если нет возможности нажать селектед
            applicationManager.getNavigationClass().gotoNewContact(); //создание контакта
            applicationManager.getContactHelper().createContact();//создание контакта 
            applicationManager.getReturnHelper().gotoHomePage();//создание контакта
        }
        applicationManager.getContactHelper().tookContactModification();
        applicationManager.getContactHelper().createContactInf(new ContactIng("RZD", "Moscow, street Tambovskaya.", "jonjolli@yandex.fu", "Ivan", "Ivanovich", "Ivanov", null), false);
        applicationManager.getContactHelper().submitContactModification();
        applicationManager.getReturnHelper().gotoHomePage();
    }
}
