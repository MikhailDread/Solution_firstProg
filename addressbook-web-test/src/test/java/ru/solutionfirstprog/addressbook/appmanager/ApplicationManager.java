package ru.solutionfirstprog.addressbook.appmanager;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class ApplicationManager{
    private final Properties properties;
    WebDriver driver;
    private ReturnHelper returnHelper;
    private ContactHelper contactHelper;
    private NavigationClass goTo;
    private GroupHelper groupHelper;
    private SessionHelper sessionHelper;
    private String browser;
    private DbHelper dbhelper;

    public ApplicationManager(String browser){
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        dbhelper = new DbHelper();
        if(browser.equals(BrowserType.CHROME)){
            driver = new ChromeDriver();
        } else if (browser.equals(BrowserType.FIREFOX)){
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        driver.get(properties.getProperty("web.baseUrl"));
        groupHelper = new GroupHelper(driver);
        goTo = new NavigationClass(driver);
        sessionHelper  = new SessionHelper(driver);
        contactHelper  = new ContactHelper(driver,this);
        returnHelper = new ReturnHelper(driver);
        sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));

    }


    public void stop() {
        driver.quit();
    }


    public GroupHelper group() {
        return groupHelper;
    }

    public NavigationClass getGoTo() {
        return goTo;
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }

    public ContactHelper contact() {
        return contactHelper;
    }

    public ReturnHelper returned() {
        return returnHelper;
    }

    public DbHelper db(){
        return dbhelper;
    }
}
