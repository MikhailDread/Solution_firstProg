package ru.solutionfirstprog.addressbook.tests;


import org.testng.annotations.*;
import ru.solutionfirstprog.addressbook.module.ContactIng;


public class DeleteContact extends TestBase{


  @Test
  public void testDeleteContact() throws Exception {
    applicationManager.getReturnHelper().returnHome();
    if(! applicationManager.getContactHelper().thereAContact()){
      applicationManager.getNavigationClass().gotoNewContact();
      applicationManager.getContactHelper().createContact(new ContactIng("RZD", "Moscow, street Tambovskaya.", "jonjolli@yandex.fu", "Ivan", "Ivanovich", "Ivanov", "test1"));
      applicationManager.getReturnHelper().gotoHomePage();
    }
    applicationManager.getGroupHelper().selectGroup();
    applicationManager.getContactHelper().deleteContact();
    applicationManager.getReturnHelper().returnHome();
  }


}
