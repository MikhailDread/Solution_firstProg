package ru.solutionfirstprog.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.solutionfirstprog.addressbook.module.ContactIng;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ContactHelper extends Helperbase {

    boolean acceptNextAlert = true;

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void submitCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void createContactInf(ContactIng contactIng, boolean creation) {
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

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
        assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
    }

    public void tookContactModification() {
        click(By.name("selected[]"));
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }


    public boolean thereAContact() {
        return isElementPresent(By.name("selected[]"));
    }


    public void createContact() {

        createContactInf(new ContactIng("RZD", "Moscow, street Tambovskaya.", "jonjolli@yandex.fu", "Ivan", "Ivanovich", "Ivanov", "test1"), true);
        submitCreation();
    }

    public List<ContactIng> contactList() {
        List<ContactIng> list = new ArrayList<>();
        List <WebElement> contact = driver.findElements(By.name("entry"));
        for(int i = 0 ; i < contact.size(); i++){
            String name = driver.findElement(By.tagName("input")).getAttribute("alt");
            String lastname = driver.findElement(By.tagName("input")).getAttribute("title");
            int id = Integer.parseInt(driver.findElement(By.tagName("input")).getAttribute("value"));
            ContactIng cont = new ContactIng(id, name, lastname, null, null, null, null, null);
            list.add(cont);
        }
        return list;
    }
}
