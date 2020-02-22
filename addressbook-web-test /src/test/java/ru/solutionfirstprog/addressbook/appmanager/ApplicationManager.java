package ru.solutionfirstprog.addressbook.appmanager;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;


public class ApplicationManager{
    WebDriver driver;
    private ReturnHelper returnHelper;
    private ContactHelper contactHelper;
    private NavigationClass navigationClass;
    private GroupHelper groupHelper;
    private SessionHelper sessionHelper;
    private String browser;

    public ApplicationManager(String browser){
        this.browser = browser;
    }


    public void init() {
        if(browser.equals(BrowserType.CHROME)){
            driver = new ChromeDriver();
        } else if (browser.equals(BrowserType.FIREFOX)){
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        groupHelper = new GroupHelper(driver);
        navigationClass = new NavigationClass(driver);
        sessionHelper  = new SessionHelper(driver);
        contactHelper  = new ContactHelper(driver);
        returnHelper = new ReturnHelper(driver);
        sessionHelper.login("admin", "secret");
    }


    public void stop() {
        driver.quit();
    }


    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationClass getNavigationClass() {
        return navigationClass;
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }

    public ReturnHelper getReturnHelper() {
        return returnHelper;
    }
}
