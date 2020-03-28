package ru.solutionfirstprog.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.solutionfirstprog.addressbook.module.ContactIng;
import ru.solutionfirstprog.addressbook.module.GroupInf;
import ru.solutionfirstprog.addressbook.module.Groups;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class ContactDeletedGroupTest extends TestBase {

    private Properties properties;

    @BeforeMethod
    public void ensurePrecondotions() throws IOException {
        properties = new Properties();
        properties.load(new FileReader(new File(String.format("src/test/resources/local.properties"))));
        applicationManager.getGoTo().groupPage();
        if (applicationManager.db().groups().size() == 0) {
            applicationManager.group().create(new GroupInf().withName(properties.getProperty("web.nameGroup"))
                    .withFeeder(properties.getProperty("web.footer")).withHeader(properties.getProperty("web.header")));
        }
        applicationManager.returned().returnHome();
        if (!applicationManager.contact().thereAContact()) {
            applicationManager.getGoTo().newContact();
            applicationManager.contact().create();
            applicationManager.returned().homePage();
        }
        applicationManager.returned().returnHome();
    }

    @Test
    public void testDeletedGroup() {
        Groups groups = applicationManager.db().groups();
        GroupInf groupInf = groups.iterator().next();
        applicationManager.returned().returnHome();
        ContactIng before = null;
        List<ContactIng> beforeCnts = applicationManager.contact().contactListHb();
        ContactIng added = beforeCnts.iterator().next();
        for (ContactIng b : beforeCnts) {
            if (b.getId() == added.getId()) {
                before = b;
            }
        }
            applicationManager.contact().deletedGroup(groupInf);
            ContactIng after = null;
            List<ContactIng> afterCnts = applicationManager.contact().contactListHb();
            for (ContactIng a : afterCnts) {
                if (a.getId() == before.getId()) {
                    after = a;
                }
            }

            if (after.getGroups().size() != (before.getGroups().size())) {
                Assert.assertEquals(after.getGroups().size(), before.getGroups().size() - 1);
             }
            System.out.println("Before " + before.getGroups().size());
            System.out.println("After " + after.getGroups().size());

            System.out.println("Before1 " + before.getGroups());
            System.out.println("After1 " + after.getGroups());

        if (!after.getGroups().equals(before.getGroups())) {
            Assert.assertEquals(after.getGroups(), before.getGroups().without(groupInf));
        } else {
            Assert.assertEquals(after.getGroups(), before.getGroups());
        }

        }
    }

