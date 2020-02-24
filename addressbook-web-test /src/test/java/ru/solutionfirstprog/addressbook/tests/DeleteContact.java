package ru.solutionfirstprog.addressbook.tests;


import org.testng.annotations.*;



public class DeleteContact extends TestBase{


  @Test
  public void testDeleteContact() throws Exception {
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
