package ru.solutionfirstprog.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.*;
import ru.solutionfirstprog.addressbook.module.ContactIng;
import ru.solutionfirstprog.addressbook.module.GroupInf;

import java.util.List;


public class DeleteContact extends TestBase{


  @Test
  public void testDeleteContact() throws Exception {
    applicationManager.getNavigationClass().gotoGroup();

    if(!applicationManager.getGroupHelper().isThereAGroup()){
      applicationManager.getGroupHelper().createGroup(new GroupInf("test1", "test2", "test3"));
    }
    applicationManager.getReturnHelper().returnHome();

    if(!applicationManager.getContactHelper().thereAContact()){
      applicationManager.getNavigationClass().gotoNewContact();
      applicationManager.getContactHelper().createContact();
      applicationManager.getReturnHelper().gotoHomePage();
    }
    List<ContactIng> before = applicationManager.getContactHelper().contactList();
    applicationManager.getGroupHelper().selectGroup(before.size() - 1);
    applicationManager.getContactHelper().deleteContact();
    applicationManager.getReturnHelper().returnHome();
    List<ContactIng> after = applicationManager.getContactHelper().contactList();
    Assert.assertEquals(after.size(), before.size() -1);

  }


}
