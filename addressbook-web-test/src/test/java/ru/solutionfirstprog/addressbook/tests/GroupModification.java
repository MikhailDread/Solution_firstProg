package ru.solutionfirstprog.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.solutionfirstprog.addressbook.module.GroupInf;
import ru.solutionfirstprog.addressbook.module.Groups;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class GroupModification extends TestBase {


    private Properties properties;

    @BeforeMethod
    public void ensurePrecondotions() throws IOException {
        properties = new Properties();
        properties.load(new FileReader(new File(String.format("src/test/resources/local.properties"))));
        applicationManager.getGoTo().groupPage();
        if(applicationManager.group().all().size() == 0){
            applicationManager.group().create(new GroupInf().withName(properties.getProperty("web.nameGroup")));
        }
    }

    @Test
    public void testGroupModification() throws IOException {
        properties = new Properties();
        properties.load(new FileReader(new File(String.format("src/test/resources/local.properties"))));
        Groups before = applicationManager.group().all();
        GroupInf modyfiyGroup = before.iterator().next();
        GroupInf group = new GroupInf().withId(modyfiyGroup.getId())
                .withName(properties.getProperty("web.nameGroup")).withFeeder(properties.getProperty("web.footer"))
                .withHeader(properties.getProperty("web.header"));
        applicationManager.group().modify(group);
        Groups after = applicationManager.group().all();
        assertEquals(after.size(), before.size());
        assertThat(applicationManager.group().count(), equalTo(before.without(modyfiyGroup).withAdded(group)));

    }

}
