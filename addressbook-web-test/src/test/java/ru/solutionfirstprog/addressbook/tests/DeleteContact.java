package ru.solutionfirstprog.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.solutionfirstprog.addressbook.module.ContactIng;
import ru.solutionfirstprog.addressbook.module.Contacts;
import ru.solutionfirstprog.addressbook.module.GroupInf;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class DeleteContact extends TestBase{

  @BeforeMethod
  public void ensurePrecondotions(){
    applicationManager.getGoTo().groupPage();

    if(applicationManager.group().all().size() == 0){
     // properties.load(new FileReader(new File(String.format("src/test/java/resourse/local.properties"))));
    applicationManager.group().create(new GroupInf().withName("test1"));
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
