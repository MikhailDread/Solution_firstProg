package ru.solutionfirstprog.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletedGroupTest extends TestBase{

    @Test
    public void testDeletedGroup(){
        applicationManager.returned().returnHome();
        applicationManager.contact().deletedGroup();
    }
}
