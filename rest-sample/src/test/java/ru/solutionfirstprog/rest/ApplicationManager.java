package ru.solutionfirstprog.rest;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class ApplicationManager {
    private final Properties properties;
    private String browser;
    WebDriver driver;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }

    public void stop() {
        if (driver != null) {
            driver.quit();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public WebDriver getDriver() {
        if (driver == null) {

            if (browser.equals(BrowserType.CHROME)) {
                driver = new ChromeDriver();
            } else if (browser.equals(BrowserType.FIREFOX)) {
                driver = new FirefoxDriver();
            }
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            driver.get(properties.getProperty("web.baseUrl"));

        }
        return driver;
    }

    
}
