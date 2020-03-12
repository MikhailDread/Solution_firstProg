package ru.solutionfirstprog.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.solutionfirstprog.addressbook.module.ContactIng;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


public class ContactEmailTest extends TestBase{

    @Test
    public void testContactEmail(){
        applicationManager.returned().returnHome();
        ContactIng cont = applicationManager.contact().all().iterator().next();
        ContactIng contactInfFromEditForm = applicationManager.contact().infoFromEditForm(cont);
        assertThat(cont.getAllEmail(), equalTo(merge(contactInfFromEditForm)));
    }

    public static String cleaned(String email){
        return email.replaceAll("\\s", "").replaceAll("[-()]", "");
    }

    public String merge(ContactIng contact){
        return Arrays.asList(contact.getEmail1(), contact.getEmail2(), contact.getEmail3()).stream().filter((s) -> !equals(""))
                .map(ContactEmailTest::cleaned).collect(Collectors.joining("\n"));
    }
}
