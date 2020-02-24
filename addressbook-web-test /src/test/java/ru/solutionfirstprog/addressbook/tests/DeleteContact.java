package ru.solutionfirstprog.addressbook.tests;


import org.testng.annotations.*;
import ru.solutionfirstprog.addressbook.module.ContactIng;


public class DeleteContact extends TestBase{


  @Test
  public void testDeleteContact() throws Exception {
    applicationManager.getReturnHelper().returnHome();
    if(!applicationManager.getContactHelper().thereAContact()){ //если нет возможности нажать селектед
      applicationManager.getNavigationClass().gotoNewContact();  //создание контакта
      applicationManager.getContactHelper().createContact();   //создание контакта
      applicationManager.getReturnHelper().gotoHomePage();  //создание контакта
    }
    applicationManager.getGroupHelper().selectGroup();
    applicationManager.getContactHelper().deleteContact();
    applicationManager.getReturnHelper().returnHome();
  }


}
