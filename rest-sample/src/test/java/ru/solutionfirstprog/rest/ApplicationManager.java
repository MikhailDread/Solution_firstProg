package ru.solutionfirstprog.rest;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.IOException;
import java.util.Properties;


public class ApplicationManager {
    private String browser;
    WebDriver driver;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() throws IOException {
        if(browser.equals(BrowserType.CHROME)){
            driver = new ChromeDriver();
        } else if (browser.equals(BrowserType.FIREFOX)){
            driver = new FirefoxDriver();
        }
    }

    public void stop() {
        if (driver != null) {
            driver.quit();
        }
    }

    
}
