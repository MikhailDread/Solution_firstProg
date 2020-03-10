package ru.solutionfirstprog.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.solutionfirstprog.addressbook.module.ContactIng;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class AddNewContact extends TestBase {

  @Test
  public void testAddNewContact() throws Exception {
    applicationManager.returned().returnHome();
    Set<ContactIng> before = applicationManager.contact().all();
    applicationManager.getGoTo().newContact();
    ContactIng contact = new ContactIng().withCompany("RZD").withStreet("Moscow, street Tambovskaya.").withEmail("jonjolli@yandex.fu").withName("Ivan").withMiddlename("Ivanovich").withLastname("Ivanov").withGroup("test1");
    applicationManager.contact().creationContact(contact, true);
    applicationManager.contact().submitCreation();
    applicationManager.returned().homePage();
    Set<ContactIng> after = applicationManager.contact().all();
    Assert.assertEquals(after.size(), before.size()+1);

    contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(contact);

    Assert.assertEquals(after, before);

  }

}
