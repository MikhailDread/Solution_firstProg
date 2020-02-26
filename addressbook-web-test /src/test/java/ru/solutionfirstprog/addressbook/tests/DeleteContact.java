package ru.solutionfirstprog.addressbook.tests;


import org.testng.annotations.*;
import ru.solutionfirstprog.addressbook.module.GroupInf;


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

    applicationManager.getGroupHelper().selectGroup();
    applicationManager.getContactHelper().deleteContact();
    applicationManager.getReturnHelper().returnHome();
  }


}
