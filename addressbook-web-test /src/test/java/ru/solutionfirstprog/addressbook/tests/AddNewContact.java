package ru.solutionfirstprog.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.solutionfirstprog.addressbook.module.ContactIng;

import java.util.List;


public class AddNewContact extends TestBase {

  @Test
  public void testAddNewContact() throws Exception {
    List<ContactIng> before = applicationManager.getContactHelper().contactList();
    applicationManager.getNavigationClass().gotoNewContact();
    applicationManager.getContactHelper().createContactInf(new ContactIng("RZD", "Moscow, street Tambovskaya.", "jonjolli@yandex.fu", "Ivan", "Ivanovich", "Ivanov", "test1"), true);
    applicationManager.getContactHelper().submitCreation();
    applicationManager.getReturnHelper().gotoHomePage();
    List<ContactIng> after = applicationManager.getContactHelper().contactList();

    Assert.assertEquals(after, before);


  }

}
