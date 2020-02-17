package ru.solutionfirstprog.addressbook.tests;


import org.testng.annotations.*;


public class DeleteContact extends TestBase{


  @Test
  public void testDeleteContact() throws Exception {
    applicationManager.getReturnHelper().returnHome();
    applicationManager.getGroupHelper().selectGroup();
    applicationManager.getContactHelper().deleteContact();
    applicationManager.getReturnHelper().returnHome();
  }


}
