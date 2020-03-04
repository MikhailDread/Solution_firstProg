package ru.solutionfirstprog.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.solutionfirstprog.addressbook.module.GroupInf;

import java.util.Comparator;
import java.util.List;


public class DeleteGroup extends TestBase{

  @Test
  public void testDeleteGroup() throws Exception {
    applicationManager.getNavigationClass().gotoGroup();
    if(!applicationManager.getGroupHelper().isThereAGroup()){
      applicationManager.getGroupHelper().createGroup(new GroupInf("test1", "test2", "test3"));
    }
    List<GroupInf> before = applicationManager.getGroupHelper().groupList();
    applicationManager.getGroupHelper().selectGroup(before.size() - 1);
    applicationManager.getGroupHelper().deleteGroup();
    applicationManager.getGroupHelper().returnToGroupPage();
    List<GroupInf> after = applicationManager.getGroupHelper().groupList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() -1);
    Assert.assertEquals(after.size(), before.size());

    Comparator<? super GroupInf> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
