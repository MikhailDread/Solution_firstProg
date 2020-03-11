package ru.solutionfirstprog.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.solutionfirstprog.addressbook.module.ContactIng;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


public class testContactPhone extends TestBase {

    @Test
    public void testContactPhone(){
        applicationManager.returned().returnHome();
        ContactIng cont = applicationManager.contact().all().iterator().next();
        ContactIng contactInfFromEditForm = applicationManager.contact().infoFromEditForm(cont);
        assertThat(cont.getAllPhone(), equalTo(mergePhones(contactInfFromEditForm)));
    }

    private String mergePhones(ContactIng contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> !s.equals("")).map(testContactPhone::cleaned).collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone){
       return phone.replaceAll("\\s", "").replaceAll("[-()]","");
    }
}
