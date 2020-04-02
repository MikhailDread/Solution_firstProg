package ru.solutionfirstprog.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.solutionfirstprog.addressbook.module.ContactIng;
import ru.solutionfirstprog.addressbook.module.GroupInf;
import ru.solutionfirstprog.addressbook.module.Groups;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class ContactAddedGroupTest extends TestBase {

    private Properties properties;

    @BeforeMethod
    public void ensurePrecondotions() throws IOException {
        properties = new Properties();
        properties.load(new FileReader(new File(String.format("src/test/resources/local.properties"))));
        applicationManager.getGoTo().groupPage();
        if (applicationManager.db().groups().size() == 0) {
            applicationManager.group().create(new GroupInf().withName(properties.getProperty("web.nameGroup"))
                    .withFeeder(properties.getProperty("web.footer")).withHeader(properties.getProperty("web.header")));
        }
        applicationManager.returned().returnHome();
        if (!applicationManager.contact().thereAContact()) {
            applicationManager.getGoTo().newContact();
            applicationManager.contact().create();
            applicationManager.returned().homePage();
        }
    }

    @Test
    public void testContactAdded() {

        applicationManager.getGoTo().groupPage();
        //GroupInf groups = applicationManager.group().all().iterator().next(); // берем группу из списка
        applicationManager.returned().returnHome();

        ContactIng before = null;
        List<ContactIng> beforeCnts = applicationManager.contact().contactListHb(); // берем контакты не удаленные до
        ContactIng added = null;
        //for(ContactIng a : beforeCnts){ //перебираем элементы
        //    if(!(a.getGroups().contains(groups))){ // если а не вмещает в себя выбранную группу
         //       added = a; // то присваиваеа а
         //   } }
        Groups groups = applicationManager.db().groups();
        int allGroup = groups.size();
        for(ContactIng a : beforeCnts){
            if(a.getGroups().size() != allGroup){
                groups.removeAll(a.getGroups());
                added = a;
                break;
            }
        }

        GroupInf groupFinish = groups.iterator().next();
        for (ContactIng b : beforeCnts) { // перебираем контакты
            if(b.getId() == added.getId()){ // при совпадении айди присваиваем
            before = b;}
        }
        applicationManager.contact().addInGroup(added, groupFinish); // добавялем в контакт выбранную группу
        ContactIng after = null;
        List<ContactIng> afterCnts = applicationManager.contact().contactListHb(); //берем контакты не удаленные после
        for (ContactIng a : afterCnts) { //перебираем контакты
            if(a.getId() == before.getId()){ //при совпадении айди присваиваем
            after = a;}
        }

        if (after.getGroups().size() != before.getGroups().size()) { // сравниваем количество групп до и после добавления
            Assert.assertEquals(after.getGroups().size(), before.getGroups().size() + 1);
        }
          System.out.println("Before " + before.getGroups().size());
          System.out.println("After " + after.getGroups().size());

          System.out.println("Before1 " + before.getGroups());
          System.out.println("After1 " + after.getGroups());

        if (!after.getGroups().equals(before.getGroups())) {
            Assert.assertEquals(after.getGroups(), before.getGroups().withAdded(groupFinish));
        } else {
            Assert.assertEquals(after.getGroups(), before.getGroups());
        }
    }
}
