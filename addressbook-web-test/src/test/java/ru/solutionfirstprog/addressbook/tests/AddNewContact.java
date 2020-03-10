package ru.solutionfirstprog.addressbook.tests;

import org.testng.annotations.Test;
import ru.solutionfirstprog.addressbook.module.ContactIng;
import ru.solutionfirstprog.addressbook.module.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


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
