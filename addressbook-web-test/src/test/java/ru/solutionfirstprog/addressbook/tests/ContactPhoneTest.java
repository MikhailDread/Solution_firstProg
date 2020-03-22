package ru.solutionfirstprog.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.solutionfirstprog.addressbook.module.ContactIng;
import ru.solutionfirstprog.addressbook.module.GroupInf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


public class ContactPhoneTest extends TestBase {


    private Properties properties;

    @BeforeMethod
    public void ensurePrecondotions() throws IOException {
        properties = new Properties();
        properties.load(new FileReader(new File(String.format("src/test/resources/local.properties"))));
        applicationManager.getGoTo().groupPage();

        if(applicationManager.contact().all().size() == 0){
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
    public void testContactPhone(){
        applicationManager.returned().returnHome();
        ContactIng cont = applicationManager.contact().all().iterator().next();
        ContactIng contactInfFromEditForm = applicationManager.contact().infoFromEditForm(cont);
        assertThat(cont.getAllPhone(), equalTo(mergePhones(contactInfFromEditForm)));
    }

    private String mergePhones(ContactIng contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> !s.equals("")).map(ContactPhoneTest::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone){
       return phone.replaceAll("\\s", "").replaceAll("[-()]","");
    }
}
