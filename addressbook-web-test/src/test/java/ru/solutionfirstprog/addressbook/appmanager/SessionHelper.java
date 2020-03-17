package ru.solutionfirstprog.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class SessionHelper extends Helperbase{

    public SessionHelper(WebDriver driver){
        super(driver);

    }

    public void login(String log, String password) {
        type(By.name("user"), log);
        type(By.name("pass"), password);
        submit(By.id("LoginForm"));
    }

}
