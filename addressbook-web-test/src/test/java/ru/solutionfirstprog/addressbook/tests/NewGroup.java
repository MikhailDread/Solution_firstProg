package ru.solutionfirstprog.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.solutionfirstprog.addressbook.module.GroupInf;
import ru.solutionfirstprog.addressbook.module.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class NewGroup extends TestBase {

  @Test
  public void testUntitledTestCase() throws Exception {
    applicationManager.getGoTo().groupPage();
    Groups before = applicationManager.group().all();
    GroupInf group = new GroupInf().withName("test2").withFeeder("test2").withHeader("test2");
    applicationManager.group().create(group);
    Groups after = applicationManager.group().all();
    assertThat(after.size(), equalTo(before.size() + 1));


    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

}
