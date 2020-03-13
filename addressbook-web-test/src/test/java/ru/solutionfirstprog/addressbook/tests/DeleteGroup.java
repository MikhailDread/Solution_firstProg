package ru.solutionfirstprog.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.solutionfirstprog.addressbook.module.GroupInf;
import ru.solutionfirstprog.addressbook.module.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class DeleteGroup extends TestBase{

  @BeforeMethod
  public void ensurePrecondotions(){
    applicationManager.getGoTo().groupPage();
    if(applicationManager.group().all().size() == 0){
      applicationManager.group().create(new GroupInf().withName("test2").withHeader("test2").withFeeder("test3"));
    }
  }

  @Test
  public void testDeleteGroup() throws Exception {
    Groups before = applicationManager.group().all();
    GroupInf deletedGroup = before.iterator().next();
    applicationManager.group().delete(deletedGroup);
    //assertThat(applicationManager.group().count(), equalTo(before.without(deletedGroup)));
    Groups after = applicationManager.group().all();
    assertEquals(after.size(), before.size() - 1);

  }

}
