package ru.solutionfirstprog.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.solutionfirstprog.addressbook.module.ContactIng;
import ru.solutionfirstprog.addressbook.module.Contacts;
import ru.solutionfirstprog.addressbook.module.Groups;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;


public class AddNewContact extends TestBase {

  @Test
  public void testAddNewContact() throws Exception {
    applicationManager.returned().returnHome();
    Contacts before = applicationManager.contact().all();
    applicationManager.getGoTo().newContact();
    ContactIng contact = new ContactIng().withCompany("RZD").withStreet("Moscow, street Tambovskaya.").withEmail("jonjolli@yandex.fu").withName("Ivan").withMiddlename("Ivanovich").withLastname("Ivanov").withGroup("test1");
    applicationManager.contact().creationContact(contact, true);
    applicationManager.contact().submitCreation();
    applicationManager.returned().homePage();
    Contacts after = applicationManager.contact().all();
    assertThat(after.size(), equalTo(before.size()+1));

    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

}
