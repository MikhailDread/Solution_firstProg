package ru.solutionfirstprog.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.*;
import ru.solutionfirstprog.addressbook.module.ContactIng;
import ru.solutionfirstprog.addressbook.module.GroupInf;

import java.util.Comparator;
import java.util.List;
import java.util.Set;


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
    Set<ContactIng> before = applicationManager.contact().all();
    ContactIng deleteContact = before.iterator().next();
    applicationManager.group().selectContactById(deleteContact.getId());
    applicationManager.contact().delete();
    applicationManager.returned().returnHome();
    Set<ContactIng> after = applicationManager.contact().all();
    Assert.assertEquals(after.size(), before.size() -1);

    before.remove(deleteContact );
    Assert.assertEquals(after.size(), before.size());

    Assert.assertEquals(after, before);

  }


}
