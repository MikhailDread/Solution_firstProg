package ru.solutionfirstprog.addressbook.tests;

import org.testng.annotations.*;
import ru.solutionfirstprog.addressbook.module.GroupInf;


public class DeleteGroup extends TestBase{

  @Test
  public void testDeleteGroup() throws Exception {
    applicationManager.getNavigationClass().gotoGroup();

    if(!applicationManager.getGroupHelper().isThereAGroup()){
      applicationManager.getGroupHelper().createGroup(new GroupInf("test1", "test2", "test3"));
    }

    applicationManager.getGroupHelper().selectGroup();
    applicationManager.getGroupHelper().deleteGroup();
    applicationManager.getGroupHelper().returnToGroupPage();
  }


}
