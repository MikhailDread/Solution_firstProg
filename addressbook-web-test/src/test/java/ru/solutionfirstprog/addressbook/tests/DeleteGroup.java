package ru.solutionfirstprog.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.solutionfirstprog.addressbook.module.GroupInf;
import ru.solutionfirstprog.addressbook.module.Groups;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.testng.Assert.assertEquals;


public class DeleteGroup extends TestBase{

  private Properties properties;

  @BeforeMethod
  public void ensurePrecondotions() throws IOException {
    properties = new Properties();
    properties.load(new FileReader(new File(String.format("src/test/resources/local.properties"))));
    applicationManager.getGoTo().groupPage();
    if(applicationManager.group().all().size() == 0){
      applicationManager.group().create(new GroupInf().withName(properties.getProperty("web.nameGroup"))
              .withHeader(properties.getProperty("web.header")).withFeeder(properties.getProperty("web.footer")));
    }
  }

  @Test
  public void testDeleteGroup(){
    Groups before = applicationManager.group().all();
    GroupInf deletedGroup = before.iterator().next();
    applicationManager.group().delete(deletedGroup);
    Groups after = applicationManager.group().all();
    //assertThat(applicationManager.group().count(), equalTo(before.without(deletedGroup)));
    assertEquals(after.size(), before.size() - 1);

  }

}
