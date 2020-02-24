package ru.solutionfirstprog.addressbook.tests;

import org.testng.annotations.*;
import ru.solutionfirstprog.addressbook.module.ContactIng;



public class AddNewContact extends TestBase {

  @Test
  public void testAddNewContact() throws Exception {
    applicationManager.getNavigationClass().gotoNewContact();
    applicationManager.getContactHelper().createContactInf(new ContactIng("RZD", "Moscow, street Tambovskaya.", "jonjolli@yandex.fu", "Ivan", "Ivanovich", "Ivanov", "test1"), true);
    applicationManager.getContactHelper().submitCreation();
    applicationManager.getReturnHelper().gotoHomePage();
  }

}
