package ru.solutionfirstprog.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ReturnHelper extends Helperbase{

    public ReturnHelper(WebDriver driver) {
        super(driver);
    }

    public void gotoHomePage() {
      click(By.linkText("home page"));
    }

    public void returnHome() {
      click(By.linkText("home"));
    }
}
