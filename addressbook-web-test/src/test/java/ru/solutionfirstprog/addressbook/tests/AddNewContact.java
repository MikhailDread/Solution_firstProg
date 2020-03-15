package ru.solutionfirstprog.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.solutionfirstprog.addressbook.module.ContactIng;
import ru.solutionfirstprog.addressbook.module.Contacts;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class AddNewContact extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    List<Object[]> list = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/java/resourse/contacts.csv")));
    String line = reader.readLine();
    while (line != null) {
      String [] split = line.split(";");
      list.add(new Object[] {new ContactIng().withCompany("RZD").withStreet("Moscow, street Tambovskaya.").withEmail1("jonjolli@yandex.fu").withName(split[0]).withMiddlename(split[1]).withLastname(split[2]).withGroup("test1").withPhoto(new File("src/test/java/resourse/1600px-This_wolf_still_has_teeth_(2).png"))});
      line = reader.readLine();
    }
    return list.iterator();
  }

  @Test(dataProvider = "validContacts")
  public void testAddNewContact(ContactIng contact) throws Exception {
    applicationManager.returned().returnHome();
    Contacts before = applicationManager.contact().all();
    applicationManager.getGoTo().newContact();
    applicationManager.contact().create(contact, true);
    Contacts after = applicationManager.contact().all();
    assertThat(after.size(), equalTo(before.size()+1));

    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

}
