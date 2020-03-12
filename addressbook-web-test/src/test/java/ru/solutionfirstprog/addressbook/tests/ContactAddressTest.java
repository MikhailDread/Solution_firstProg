package ru.solutionfirstprog.addressbook.tests;

import org.testng.annotations.Test;
import ru.solutionfirstprog.addressbook.module.ContactIng;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactAddressTest extends TestBase{


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
