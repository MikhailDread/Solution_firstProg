package ru.solutionfirstprog.addressbook.tests;


import org.testng.annotations.Test;

public class ContactAddedGroupTest extends TestBase {

    @Test
    public void testContactAdded(){
        applicationManager.returned().returnHome();
        applicationManager.contact().addInGroup();
    }
}
