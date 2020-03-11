package ru.solutionfirstprog.addressbook.tests;

import org.testng.annotations.Test;
import ru.solutionfirstprog.addressbook.module.GroupInf;
import ru.solutionfirstprog.addressbook.module.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NewGroup extends TestBase {

  @Test
  public void testUntitledTestCase() throws Exception {
    applicationManager.getGoTo().groupPage();
    Groups before = applicationManager.group().all();
    GroupInf group = new GroupInf().withName("test2").withFeeder("test2").withHeader("test2");
    applicationManager.group().create(group);
    assertThat(applicationManager.group().count(), equalTo(before.size() + 1));
    Groups after = applicationManager.group().all();


    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test
  public void testBadUntitledTestCase() throws Exception {
    applicationManager.getGoTo().groupPage();
    Groups before = applicationManager.group().all();
    GroupInf group = new GroupInf().withName("test2'").withFeeder("test2").withHeader("test2");
    applicationManager.group().create(group);
    assertThat(applicationManager.group().count(), equalTo(before.size()));
    Groups after = applicationManager.group().all();


    assertThat(after, equalTo(before));
  }

}
