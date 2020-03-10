package ru.solutionfirstprog.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.solutionfirstprog.addressbook.module.ContactIng;
import ru.solutionfirstprog.addressbook.module.Contacts;
import ru.solutionfirstprog.addressbook.module.GroupInf;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactModification extends TestBase {

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
    public void testContactModification(){

        Contacts before = applicationManager.contact().all();
        ContactIng modifyContact = before.iterator().next();
        applicationManager.contact().contactModification();
        ContactIng contact = new ContactIng().withId(modifyContact.getId()).withCompany("RZD").withStreet("Moscow, street Tambovskaya.").withEmail("jonjolli@yandex.fu").withName("Ivan").withMiddlename("Ivanovich").withLastname("Ivanov").withGroup("test1");
        applicationManager.contact().creationContact(contact, false);
        applicationManager.contact().submit();
        applicationManager.returned().homePage();
        Contacts after = applicationManager.contact().all();
        assertThat(after.size(), equalTo(before.size()));

        assertThat(after, equalTo(before.without(modifyContact).withAdded(modifyContact)));
    }

}
