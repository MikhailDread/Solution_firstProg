package ru.solutionfirstprog.addressbook.tests;


import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.solutionfirstprog.addressbook.module.ContactIng;
import ru.solutionfirstprog.addressbook.module.Contacts;
import ru.solutionfirstprog.addressbook.module.GroupInf;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class DeleteContact extends TestBase{

  @BeforeMethod
  public void ensurePrecondotions(){
    applicationManager.getGoTo().groupPage();

    if(applicationManager.group().all().size() == 0){
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
    applicationManager.group().selectContactById(deleteContact.getId());
    applicationManager.contact().delete();
    applicationManager.returned().returnHome();
    Contacts after = applicationManager.contact().all();
    assertThat(after.size(), equalTo(before.size() -1));

    assertThat(after, equalTo(before.without(deleteContact)));
  }


}
