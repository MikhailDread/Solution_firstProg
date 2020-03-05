package ru.solutionfirstprog.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.solutionfirstprog.addressbook.module.GroupInf;

import java.util.Comparator;
import java.util.List;

public class NewGroup extends TestBase {

  @Test
  public void testUntitledTestCase() throws Exception {
    applicationManager.getNavigationClass().gotoGroup();
    List<GroupInf> before = applicationManager.getGroupHelper().groupList();
    applicationManager.getGroupHelper().initNewGroup();
    GroupInf group = new GroupInf("test1", "test2", "test3");
    applicationManager.getGroupHelper().creationGroup(group);
    applicationManager.getGroupHelper().submitGroup();
    applicationManager.getGroupHelper().returnToGroupPage();
    List<GroupInf> after = applicationManager.getGroupHelper().groupList();
    Assert.assertEquals(after.size(), before.size() + 1);

    int max1 = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();

    group.setId(max1);
    before.add(group);

    Comparator<? super GroupInf> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);
  }

}
