package ru.solutionfirstprog.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.solutionfirstprog.addressbook.module.ContactIng;
import ru.solutionfirstprog.addressbook.module.UserInf;

import static org.testng.Assert.assertTrue;

public class ContactHelper extends Helperbase{
    boolean acceptNextAlert = true;

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void submitCreation() {
      click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void createContactInf(ContactIng contactIng) {
        type(By.name("company"), contactIng.getCompany());
      type(By.name("address"), contactIng.getStreet());
      type(By.name("email"), contactIng.getEmail());
    }

    public void createUserInf(UserInf userInf) {
        type(By.name("firstname"), userInf.getName());
        type(By.name("middlename"), userInf.getMiddlename());
        type(By.name("lastname"), userInf.getLastname());

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
}
