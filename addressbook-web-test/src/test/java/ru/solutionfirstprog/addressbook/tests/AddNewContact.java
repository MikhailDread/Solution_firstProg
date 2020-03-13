package ru.solutionfirstprog.addressbook.tests;

import org.testng.annotations.Test;
import ru.solutionfirstprog.addressbook.module.ContactIng;
import ru.solutionfirstprog.addressbook.module.Contacts;

import java.io.File;
import java.io.FileReader;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class AddNewContact extends TestBase {

  @Test
  public void testAddNewContact() throws Exception {
    applicationManager.returned().returnHome();
    Contacts before = applicationManager.contact().all();
    applicationManager.getGoTo().newContact();
    File photo = new File("src/test/java/resourse/1600px-This_wolf_still_has_teeth_(2).png");
    ContactIng contact = new ContactIng().withCompany("RZD").withStreet("Moscow, street Tambovskaya.").withEmail1("jonjolli@yandex.fu").withName("Ivan").withMiddlename("Ivanovich").withLastname("Ivanov").withGroup("test1").withPhoto(photo);
    applicationManager.contact().create(contact, true);
    Contacts after = applicationManager.contact().all();
    assertThat(after.size(), equalTo(before.size()+1));

    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

}
