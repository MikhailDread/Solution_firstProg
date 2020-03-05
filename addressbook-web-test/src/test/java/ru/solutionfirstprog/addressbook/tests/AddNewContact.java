package ru.solutionfirstprog.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.solutionfirstprog.addressbook.module.ContactIng;

import java.util.HashSet;
import java.util.List;


public class AddNewContact extends TestBase {

  @Test
  public void testAddNewContact() throws Exception {
    List<ContactIng> before = applicationManager.getContactHelper().contactList();
    applicationManager.getNavigationClass().gotoNewContact();
    ContactIng contact = new ContactIng("RZD", "Moscow, street Tambovskaya.", "jonjolli@yandex.fu", "Ivan", "Ivanovich", "Ivanov", "test1");
    applicationManager.getContactHelper().createContactInf(contact, true);
    applicationManager.getContactHelper().submitCreation();
    applicationManager.getReturnHelper().gotoHomePage();
    List<ContactIng> after = applicationManager.getContactHelper().contactList();
    //Assert.assertEquals(after.size(), before.size() + 1);

    int max = 0;
    for(ContactIng e : after){
      if(e.getId() > max){
        max = e.getId();
      }
    }
    contact.setId(max);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));

  }

}
