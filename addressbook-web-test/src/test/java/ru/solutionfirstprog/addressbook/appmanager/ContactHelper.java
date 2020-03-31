package ru.solutionfirstprog.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.solutionfirstprog.addressbook.module.ContactIng;
import ru.solutionfirstprog.addressbook.module.Contacts;
import ru.solutionfirstprog.addressbook.module.GroupInf;
import ru.solutionfirstprog.addressbook.module.Groups;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static org.testng.Assert.assertTrue;

public class ContactHelper extends Helperbase {

    private final Properties properties;
    boolean acceptNextAlert = true;

    ApplicationManager applicationManager;
    private SessionBuilder<SessionBuilder> sessionFactory;

    public ContactHelper(WebDriver driver, ApplicationManager applicationManager) throws IOException {
        super(driver);
        this.applicationManager = applicationManager;
        properties = new Properties();
        properties.load(new FileReader(new File(String.format("src/test/resources/local.properties"))));
    }

    public void submitCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void creationContact(ContactIng contactIng, boolean creation) {
        type(By.name("company"), contactIng.getCompany());
        type(By.name("address"), contactIng.getStreet());
        type(By.name("email"), contactIng.getEmail1());
        type(By.name("firstname"), contactIng.getName());
        type(By.name("middlename"), contactIng.getMiddlename());
        type(By.name("lastname"), contactIng.getLastname());
        attach(By.name("photo"), contactIng.getPhoto());

        if (creation) {
            if(contactIng.getGroups().size() > 0){
                Assert.assertTrue(contactIng.getGroups().size() == 1);
            new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactIng.getGroups().iterator().next().getName());}
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }

    public void create(ContactIng contact, boolean usl) {
        if(usl == true){
        creationContact(contact, true);
            submitCreation();
            applicationManager.returned().homePage();
        }
        else if (usl == false){
            creationContact(contact, false);
            submit();
            applicationManager.returned().homePage();
        }
    }


    public String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }

    public void delete() {
        click(By.xpath("//input[@value='Delete']"));
        assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
    }

    public void delete(ContactIng deleteContact) {
        applicationManager.group().selectContactById(deleteContact.getId());
        applicationManager.contact().delete();
        applicationManager.returned().returnHome();
    }

    public void contactModification() {
        click(By.name("selected[]"));
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submit() {
        click(By.name("update"));
    }


    public boolean thereAContact() {
        return isElementPresent(By.name("selected[]"));
    }


    public void create(){
        Groups groups = applicationManager.db().groups();
        creationContact(new ContactIng().withName(properties.getProperty("web.name"))
                .withMiddlename(properties.getProperty("web.middleName")).withLastname(properties.getProperty("web.lastName")).withCompany(properties.getProperty("web.company")).withStreet(properties.getProperty("web.street"))
                .withEmail1(properties.getProperty("web.email")).inGroup(groups.iterator().next()), true);
        submitCreation();
    }


    public Contacts all() {
        Contacts list = new Contacts();
        List <WebElement> contact = driver.findElements(By.name("entry"));
        for(WebElement e : contact){
            List<WebElement> cells = e.findElements(By.tagName("td"));
            String name = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            String allPhones = cells.get(5).getText();
            String address = cells.get(3).getText();
            String allEmail = cells.get(4).getText();
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            ContactIng cont = new ContactIng().withId(id).withCompany(null).withStreet(null).withEmail1(null).withName(name).
                    withMiddlename(null).withLastname(lastname).inGroup(null).withAllPhones(allPhones)
                    .withStreet(address).withAllEmail(allEmail);
            list.add(cont);
        }
        return list;
    }

    public ContactIng infoFromEditForm(ContactIng cont) {
        initContactModificationById(cont.getId());
        String name = driver.findElement(By.name("firstname")).getAttribute("value");
        String lastname = driver.findElement(By.name("lastname")).getAttribute("value");
        String home = driver.findElement(By.name("home")).getAttribute("value");
        String mobile = driver.findElement(By.name("mobile")).getAttribute("value");
        String work = driver.findElement(By.name("work")).getAttribute("value");
        String email1 = driver.findElement(By.name("email")).getAttribute("value");
        String email2 = driver.findElement(By.name("email2")).getAttribute("value");
        String email3 = driver.findElement(By.name("email3")).getAttribute("value");
        String address = driver.findElement(By.cssSelector("textarea[name = 'address']")).getText();
        driver.navigate().back();
        return new ContactIng().withId(cont.getId()).withName(name).withLastname(lastname).withHomePhone(home)
.withMobilePhone(mobile).withWorkPhone(work).withEmail1(email1).withEmail2(email2).withEmail3(email3).withStreet(address);    }

    public void initContactModificationById(int id) {
        driver.findElement((By.cssSelector("a[href*='edit.php?id=" + id + "']"))).click();
    }


    public void addInGroup(int id, String nameGroup) {
        driver.findElement(By.cssSelector("input[value = '" + id + "']")).click(); // выбираем контакт по переданному айди
        driver.findElement(By.name("to_group")).click(); // нажимаем на список групп
        //driver.findElement(By.xpath("//table[@id='maintable']/tbody/tr[3]/td/input")).click();
        //driver.findElement(By.cssSelector("option[value = '" + idGroup + "']")).click();
        new Select(driver.findElement(By.name("to_group"))).selectByVisibleText(nameGroup); // выбираем группу по переданному имени группы
        driver.findElement(By.name("add")).click(); // добавляем
        applicationManager.returned().returnHome();
    }

    public void deletedGroup(int id, GroupInf group, boolean have) {
        driver.findElement(By.linkText("home")).click();
        driver.findElement(By.name("group")).click(); // нажимаем на группу
        new Select(driver.findElement(By.name("group"))).selectByVisibleText(group.getName()); // выбираем по имени переданному
        if (have) {
        //if (group.getContacts().contains(id)) {
            driver.findElement(By.cssSelector("input[value = '" + id + "']")).click(); // ставим галочку на контакт
            //driver.findElement(By.name("selected[]")).click(); // ставим галочку на контакт
            driver.findElement(By.name("remove")).click(); // удаляем
            driver.findElement(By.linkText("home")).click(); // домой
        } else {
            new Select(driver.findElement(By.name("group"))).selectByVisibleText("[all]");
            addInGroup(id, group.getName());
            driver.findElement(By.name("group")).click();
            new Select(driver.findElement(By.name("group"))).selectByVisibleText(group.getName());
            driver.findElement(By.cssSelector("input[value = '" + id + "']")).click(); // ставим галочку на контакт
            driver.findElement(By.name("remove")).click(); // удаляем
            driver.findElement(By.linkText("home")).click(); // домой
        }
    }

    public List<ContactIng> contactListHb() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactIng> result = session.createQuery("from ContactIng where deprecated = '0000-00-00'").list();
        for (ContactIng cont : result) {
            System.out.println(cont);
            System.out.println(cont.getGroups());
        }
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
