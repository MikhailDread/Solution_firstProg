package ru.solutionfirstprog.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.solutionfirstprog.addressbook.module.ContactIng;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


public class AddNewContact extends TestBase {

  @Test
  public void testAddNewContact() throws Exception {
    applicationManager.getReturnHelper().returnHome();
    List<ContactIng> before = applicationManager.getContactHelper().contactList();
    applicationManager.getGoTo().gotoNewContact();
    ContactIng contact = new ContactIng("RZD", "Moscow, street Tambovskaya.", "jonjolli@yandex.fu", "Ivan", "Ivanovich", "Ivanov", "test1");
    applicationManager.getContactHelper().createContactInf(contact, true);
    applicationManager.getContactHelper().submitCreation();
    applicationManager.getReturnHelper().gotoHomePage();
    List<ContactIng> after = applicationManager.getContactHelper().contactList();
    Assert.assertEquals(after.size(), before.size()+1);

    int max1 = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();
    contact.setId(max1);
    before.add(contact);

    Comparator<? super ContactIng> maxId = (Comparator<ContactIng>) (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    after.sort(maxId);
    before.sort(maxId);
    Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));

  }

}
