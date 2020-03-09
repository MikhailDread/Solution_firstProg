package ru.solutionfirstprog.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.*;
import ru.solutionfirstprog.addressbook.module.ContactIng;
import ru.solutionfirstprog.addressbook.module.GroupInf;

import java.util.Comparator;
import java.util.List;


public class DeleteContact extends TestBase{


  @Test
  public void testDeleteContact() throws Exception {
    applicationManager.getGoTo().groupPage();

    if(!applicationManager.group().isThereAGroup()){
      applicationManager.group().create(new GroupInf("test1", "test2", "test3"));
    }
    applicationManager.getReturnHelper().returnHome();

    if(!applicationManager.getContactHelper().thereAContact()){
      applicationManager.getGoTo().gotoNewContact();
      applicationManager.getContactHelper().createContact();
      applicationManager.getReturnHelper().gotoHomePage();
    }
    List<ContactIng> before = applicationManager.getContactHelper().contactList();
    applicationManager.group().selectGroup(before.size() - 1);
    applicationManager.getContactHelper().deleteContact();
    applicationManager.getReturnHelper().returnHome();
    List<ContactIng> after = applicationManager.getContactHelper().contactList();
    Assert.assertEquals(after.size(), before.size() -1);

    before.remove(before.size() - 1);
    Assert.assertEquals(after.size(), before.size());

    Comparator<ContactIng> maxId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
    after.sort(maxId);
    before.sort(maxId);
    Assert.assertEquals(after, before);

  }


}
