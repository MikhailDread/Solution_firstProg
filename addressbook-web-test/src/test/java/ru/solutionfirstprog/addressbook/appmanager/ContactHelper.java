package ru.solutionfirstprog.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.solutionfirstprog.addressbook.module.ContactIng;
import ru.solutionfirstprog.addressbook.module.Contacts;
import ru.solutionfirstprog.addressbook.module.Groups;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertTrue;

public class ContactHelper extends Helperbase {

    boolean acceptNextAlert = true;

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void submitCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void creationContact(ContactIng contactIng, boolean creation) {
        type(By.name("company"), contactIng.getCompany());
        type(By.name("address"), contactIng.getStreet());
        type(By.name("email"), contactIng.getEmail());
        type(By.name("firstname"), contactIng.getName());
        type(By.name("middlename"), contactIng.getMiddlename());
        type(By.name("lastname"), contactIng.getLastname());

        if (creation) {
            new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactIng.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
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


    public void create() {

        creationContact(new ContactIng().withCompany("RZD").withStreet("Moscow, street Tambovskaya.").withEmail("jonjolli@yandex.fu").withName("Ivan").withMiddlename("Ivanovich").withLastname("Ivanov").withGroup("test1"), true);
        submitCreation();
    }


    public List<ContactIng> list() {
        List<ContactIng> list = new ArrayList<>();
        List <WebElement> contact = driver.findElements(By.name("entry"));
        for(WebElement e : contact){
            List<WebElement> cells = e.findElements(By.tagName("td"));
            String name = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            ContactIng cont = new ContactIng().withId(id).withCompany(null).withStreet(null).withEmail(null).withName(name).withMiddlename(null).withLastname(lastname).withGroup(null);
            list.add(cont);
        }
        return list;
    }

    public Contacts all() {
        Contacts list = new Contacts();
        List <WebElement> contact = driver.findElements(By.name("entry"));
        for(WebElement e : contact){
            List<WebElement> cells = e.findElements(By.tagName("td"));
            String name = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            ContactIng cont = new ContactIng().withId(id).withCompany(null).withStreet(null).withEmail(null).withName(name).withMiddlename(null).withLastname(lastname).withGroup(null);
            list.add(cont);
        }
        return list;
    }

}
