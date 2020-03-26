package ru.solutionfirstprog.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.solutionfirstprog.addressbook.module.ContactIng;
import ru.solutionfirstprog.addressbook.module.Contacts;
import ru.solutionfirstprog.addressbook.module.GroupInf;
import ru.solutionfirstprog.addressbook.module.Groups;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.acl.Group;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class ContactAddedGroupTest extends TestBase {

    private Properties properties;

    @BeforeMethod
    public void ensurePrecondotions() throws IOException {
        properties = new Properties();
        properties.load(new FileReader(new File(String.format("src/test/resources/local.properties"))));
        applicationManager.getGoTo().groupPage();
        if(applicationManager.db().groups().size() == 0){
            applicationManager.group().create(new GroupInf().withName(properties.getProperty("web.nameGroup"))
                    .withFeeder(properties.getProperty("web.footer")).withHeader(properties.getProperty("web.header")));
        }
        applicationManager.returned().returnHome();
        if(!applicationManager.contact().thereAContact()){
            applicationManager.getGoTo().newContact();
            applicationManager.contact().create();
            applicationManager.returned().homePage();
        }
    }

    @Test
    public void testContactAdded(){

        ContactIng beforeCont = null;
        applicationManager.returned().returnHome();
        List<ContactIng> before = applicationManager.contact().contactListHb();
        for(ContactIng c : before){
            beforeCont = c;
        }

        applicationManager.contact().addInGroup();
        ContactIng afterCont = null;
        List<ContactIng> after = applicationManager.contact().contactListHb();
        for (ContactIng c : after){
            afterCont = c;
        }

        Assert.assertEquals(afterCont.getGroups().size(), beforeCont.getGroups().size()+1);
    }
}
