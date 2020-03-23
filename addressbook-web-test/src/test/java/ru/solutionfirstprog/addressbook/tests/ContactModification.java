package ru.solutionfirstprog.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.solutionfirstprog.addressbook.module.ContactIng;
import ru.solutionfirstprog.addressbook.module.Contacts;
import ru.solutionfirstprog.addressbook.module.GroupInf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactModification extends TestBase {

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
    public void testContactModification() throws IOException {
        properties = new Properties();
        properties.load(new FileReader(new File(String.format("src/test/resources/local.properties"))));
        Contacts before = applicationManager.db().contacts();
        ContactIng modifyContact = before.iterator().next();
        applicationManager.contact().initContactModificationById(modifyContact.getId());
        ContactIng contact = new ContactIng().withId(modifyContact.getId()).withCompany(properties.getProperty("web.company"))
                .withStreet(properties.getProperty("web.street")).withEmail1(properties.getProperty("web.email"))
                .withName(properties.getProperty("web.name")).withMiddlename(properties.getProperty("web.middleName"))
                .withLastname(properties.getProperty("web.lastName")).withPhoto(new File("src/test/resources/1600px-This_wolf_still_has_teeth_(2).png"));
        applicationManager.contact().create(contact, false);
        Contacts after = applicationManager.db().contacts();
        assertThat(after.size(), equalTo(before.size()));

        //assertThat(after, equalTo(before.without(modifyContact).withAdded(modifyContact)));
    }

}
