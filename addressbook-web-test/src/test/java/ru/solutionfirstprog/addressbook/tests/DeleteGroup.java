package ru.solutionfirstprog.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.solutionfirstprog.addressbook.module.GroupInf;

import java.util.Comparator;
import java.util.List;


public class DeleteGroup extends TestBase{


  @BeforeMethod
  public void ensurePrecondotions(){
    applicationManager.getGoTo().groupPage();
    if(applicationManager.group().list().size() == 0){
      applicationManager.group().create(new GroupInf("test1", null, null));
    }
  }

  @Test
  public void testDeleteGroup() throws Exception {
    List<GroupInf> before = applicationManager.group().list();
    int index = before.size() - 1;
    applicationManager.group().delete(index);
    List<GroupInf> after = applicationManager.group().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Assert.assertEquals(after.size(), before.size());

    Comparator<? super GroupInf> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
