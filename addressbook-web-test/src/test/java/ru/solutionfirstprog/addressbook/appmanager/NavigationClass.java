package ru.solutionfirstprog.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationClass extends Helperbase{


    public NavigationClass(WebDriver driver) {
        super(driver);
    }

    public void groupPage() {

        if(isElementPresent(By.tagName("h1"))
                && driver.findElement(By.tagName("h1")).getText().equals("Group")
                && isElementPresent(By.name("new"))){
            return;
        }

        click(By.linkText("groups"));
    }

    public void newContact() {

        click(By.linkText("add new"));
    }
}
