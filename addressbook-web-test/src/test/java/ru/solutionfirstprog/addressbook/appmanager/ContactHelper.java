package ru.solutionfirstprog.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.solutionfirstprog.addressbook.module.ContactIng;
import ru.solutionfirstprog.addressbook.module.Contacts;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class ContactHelper extends Helperbase {

    boolean acceptNextAlert = true;

    ApplicationManager applicationManager;

    public ContactHelper(WebDriver driver, ApplicationManager applicationManager) {
        super(driver);
        this.applicationManager = applicationManager;
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
            new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactIng.getGroup());
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


    public void create() {

        creationContact(new ContactIng().withCompany("RZD").withStreet("Moscow, street Tambovskaya.").withEmail1("jonjolli@yandex.fu").withName("Ivan").withMiddlename("Ivanovich").withLastname("Ivanov").withGroup("test1"), true);
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
                    withMiddlename(null).withLastname(lastname).withGroup(null).withAllPhones(allPhones)
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


}
