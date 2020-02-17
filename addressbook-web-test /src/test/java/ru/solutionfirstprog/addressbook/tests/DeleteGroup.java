package ru.solutionfirstprog.addressbook.tests;

import org.testng.annotations.*;



public class DeleteGroup extends TestBase{

  @Test
  public void testDeleteGroup() throws Exception {
    applicationManager.getNavigationClass().gotoGroup();
    applicationManager.getGroupHelper().selectGroup();
    applicationManager.getGroupHelper().deleteGroup();
    applicationManager.getGroupHelper().returnToGroupPage();
  }


}
