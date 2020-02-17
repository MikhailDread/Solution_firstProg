package ru.solutionfirstprog.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationClass extends Helperbase{


    public NavigationClass(WebDriver driver) {
        super(driver);
    }

    public void gotoGroup() {

        click(By.linkText("groups"));
    }

    public void gotoNewContact() {

        click(By.linkText("add new"));
    }
}
