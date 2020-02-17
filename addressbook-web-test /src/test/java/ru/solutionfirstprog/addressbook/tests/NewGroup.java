package ru.solutionfirstprog.addressbook.tests;

import org.testng.annotations.*;
import ru.solutionfirstprog.addressbook.module.GroupInf;

public class NewGroup extends TestBase {

  @Test
  public void testUntitledTestCase() throws Exception {
    applicationManager.getNavigationClass().gotoGroup();
    applicationManager.getGroupHelper().initNewGroup();
    applicationManager.getGroupHelper().creationGroup(new GroupInf("test1", "test2", "test3"));
    applicationManager.getGroupHelper().submitGroup();
    applicationManager.getGroupHelper().returnToGroupPage();
  }

}
