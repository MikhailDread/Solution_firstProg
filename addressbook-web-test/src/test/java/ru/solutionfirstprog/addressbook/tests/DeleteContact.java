package ru.solutionfirstprog.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.solutionfirstprog.addressbook.module.ContactIng;
import ru.solutionfirstprog.addressbook.module.Contacts;
import ru.solutionfirstprog.addressbook.module.GroupInf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class DeleteContact extends TestBase{

  private Properties properties;

  @BeforeMethod
  public void ensurePrecondotions() throws IOException {
    properties = new Properties();
    properties.load(new FileReader(new File(String.format("src/test/java/resourse/local.properties"))));
    applicationManager.getGoTo().groupPage();

    if(applicationManager.group().all().size() == 0){
    applicationManager.group().create(new GroupInf().withName(properties.getProperty("web.nameGroup")));
  }
    applicationManager.returned().returnHome();
    if(!applicationManager.contact().thereAContact()){
      applicationManager.getGoTo().newContact();
      applicationManager.contact().create();
      applicationManager.returned().homePage();
    }
  }



  @Test
  public void testDeleteContact() throws Exception {
    Contacts before = applicationManager.contact().all();
    ContactIng deleteContact = before.iterator().next();
    applicationManager.contact().delete(deleteContact);
    Contacts after = applicationManager.contact().all();
    assertThat(after.size(), equalTo(before.size() -1));

    assertThat(after, equalTo(before.without(deleteContact)));
  }

}
