package ru.solutionfirstprog.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.solutionfirstprog.addressbook.module.ContactIng;
import ru.solutionfirstprog.addressbook.module.GroupInf;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactAddressTest extends TestBase{


    @BeforeMethod
    public void ensurePrecondotions(){
        applicationManager.getGoTo().groupPage();

        if(applicationManager.contact().all().size() == 0){
            applicationManager.group().create(new GroupInf().withName("test1").withFeeder("test2").withHeader("test3"));
        }
        applicationManager.returned().returnHome();
        if(!applicationManager.contact().thereAContact()){
            applicationManager.getGoTo().newContact();
            applicationManager.contact().create();
            applicationManager.returned().homePage();
        }
    }

        @Test
        public void testContactAddress(){
            applicationManager.returned().returnHome();
            ContactIng cont = applicationManager.contact().all().iterator().next();
            ContactIng contactInfFromEditForm = applicationManager.contact().infoFromEditForm(cont);
            assertThat(cont.getStreet(), equalTo(mergeAddress(contactInfFromEditForm)));
        }

        public String mergeAddress(ContactIng contact){
            return Arrays.asList(contact.getStreet()).stream().filter((s) -> !equals("")).collect(Collectors.joining());

        }

    }
