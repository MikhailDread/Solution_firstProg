package ru.solutionfirstprog.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.solutionfirstprog.addressbook.module.GroupInf;
import ru.solutionfirstprog.addressbook.module.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NewGroup extends TestBase {
    private Properties properties;

    @DataProvider
    public Iterator<Object[]> validGroupsJSON() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/java/resourse/groups.xml")))){
        String json = "";
        String line = reader.readLine();
        while(line != null){
            json += line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<GroupInf> group = gson.fromJson(json, new TypeToken<List<GroupInf>>() {}.getType());
        return group.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();}
    }


  @DataProvider
  public Iterator<Object[]> validGroups() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))){
    String xml = "";
    String line = reader.readLine();
    while(line != null){
        xml += line;
        line = reader.readLine();
    }
      XStream xstream = new XStream();
    xstream.processAnnotations(GroupInf.class);
      List<GroupInf> group = (List<GroupInf>)xstream.fromXML(xml);
     return group.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();}
  }
  @Test(dataProvider = "validGroups")
  public void testUntitledTestCase(GroupInf group) throws Exception {
      applicationManager.getGoTo().groupPage();
      Groups before = applicationManager.db().groups();
      applicationManager.group().create(group);
      assertThat(applicationManager.group().count(), equalTo(before.size() + 1));
      Groups after = applicationManager.db().groups();


      assertThat(after, equalTo(
              before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test
  public void testBadUntitledTestCase() throws Exception {
        properties = new Properties();
      properties.load(new FileReader(new File(String.format("src/test/resources/local.properties"))));
    applicationManager.getGoTo().groupPage();
    Groups before = applicationManager.db().groups();
    GroupInf group = new GroupInf().withName(properties.getProperty("web.nameGroup"))
            .withFeeder(properties.getProperty("web.footer")).withHeader(properties.getProperty("web.header"));
    applicationManager.group().create(group);
    assertThat(applicationManager.group().count(), equalTo(before.size()));
    Groups after = applicationManager.db().groups();


    assertThat(after, equalTo(before));
  }

}
