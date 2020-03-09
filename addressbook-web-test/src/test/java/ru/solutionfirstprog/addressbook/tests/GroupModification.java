package ru.solutionfirstprog.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.solutionfirstprog.addressbook.module.GroupInf;

import java.util.Comparator;
import java.util.List;


public class GroupModification extends TestBase {

    @BeforeMethod
    public void ensurePrecondotions(){
        applicationManager.getGoTo().groupPage();
        if(applicationManager.group().list().size() == 0){
            applicationManager.group().create(new GroupInf("test1", null, null));
        }
    }

    @Test
    public void testGroupModification(){
        List<GroupInf> before = applicationManager.group().list();
        int index = before.size() - 1;
        GroupInf group = new GroupInf(before.get(index).getId(), "test1", "test2", "test3");
        applicationManager.group().modify(index, group);
        List<GroupInf> after = applicationManager.group().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(group);

        Comparator<? super GroupInf> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());

        after.sort(byId);
        before.sort(byId);
        Assert.assertEquals(before, after);

    }

}
