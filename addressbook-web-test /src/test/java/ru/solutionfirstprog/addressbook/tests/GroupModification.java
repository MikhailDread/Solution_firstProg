package ru.solutionfirstprog.addressbook.tests;

import org.testng.annotations.Test;
import ru.solutionfirstprog.addressbook.module.GroupInf;

public class GroupModification extends TestBase {

    @Test
    public void testGroupModification(){
        applicationManager.getNavigationClass().gotoGroup();
        applicationManager.getGroupHelper().selectGroup();
        applicationManager.getGroupHelper().initGroupModification();
        applicationManager.getGroupHelper().creationGroup(new GroupInf("test1", "test2", "test3"));
        applicationManager.getGroupHelper().submitGroupModification();
        applicationManager.getGroupHelper().returnToGroupPage();
    }
}
