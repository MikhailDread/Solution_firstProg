package ru.solutionfirstprog.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.solutionfirstprog.addressbook.module.GroupInf;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


public class GroupModification extends TestBase {

    @Test
    public void testGroupModification(){
        applicationManager.getNavigationClass().gotoGroup();
        if(!applicationManager.getGroupHelper().isThereAGroup()){
            applicationManager.getGroupHelper().createGroup(new GroupInf("test1", null, null));
        }
        List<GroupInf> before = applicationManager.getGroupHelper().groupList();
        applicationManager.getGroupHelper().selectGroup(before.size() - 1);
        applicationManager.getGroupHelper().initGroupModification();
        GroupInf group = new GroupInf((before.get(before.size()-1)).getId(), "test1", "test2", "test3");
        applicationManager.getGroupHelper().creationGroup(group);
        applicationManager.getGroupHelper().submitGroupModification();
        applicationManager.getGroupHelper().returnToGroupPage();
        List<GroupInf> after = applicationManager.getGroupHelper().groupList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(group);

        Comparator<? super GroupInf> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());

        after.sort(byId);
        before.sort(byId);
        Assert.assertEquals(before, after);

    }
}
