package ru.solutionfirstprog.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.solutionfirstprog.addressbook.module.ContactIng;
import ru.solutionfirstprog.addressbook.module.Contacts;
import ru.solutionfirstprog.addressbook.module.Groups;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class AddNewContact extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))){
    String xml = "";
    String line = reader.readLine();
    while (line != null) {
      xml += line;
      line = reader.readLine();
    }
    XStream xStream = new XStream();
    xStream.processAnnotations(ContactIng.class);
    List <ContactIng> contacts = (List <ContactIng>) xStream.fromXML(xml);
    return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();}
  }

  @Test(dataProvider = "validContacts")
  public void testAddNewContact(ContactIng contact) throws Exception {
    applicationManager.returned().returnHome();
    Contacts before = applicationManager.db().contacts();
    applicationManager.getGoTo().newContact();
    applicationManager.contact().create(contact, true);
    Contacts after = applicationManager.db().contacts();
    assertThat(after.size(), equalTo(before.size()+1));

    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

}
