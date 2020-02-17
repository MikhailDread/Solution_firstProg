package ru.solutionfirstprog.addressbook.tests;

import org.testng.annotations.*;
import ru.solutionfirstprog.addressbook.module.ContactIng;
import ru.solutionfirstprog.addressbook.module.UserInf;


public class AddNewContact extends TestBase {

  @Test
  public void testAddNewContact() throws Exception {
    applicationManager.getNavigationClass().gotoNewContact();
    applicationManager.getContactHelper().createUserInf(new UserInf("Ivan", "Ivanovich", "Ivanov"));
    applicationManager.getContactHelper().createContactInf(new ContactIng("RZD", "Moscow, street Tambovskaya.", "jonjolli@yandex.fu"));
    applicationManager.getContactHelper().submitCreation();
    applicationManager.getReturnHelper().gotoHomePage();
  }

}
