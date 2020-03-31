package ru.solutionfirstprog.mantis.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.solutionfirstprog.mantis.appmanager.ApplicationManager;

public class RegistrationHelper {
    private ApplicationManager applicationManager;
    private WebDriver driver;

    public RegistrationHelper(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
        driver = applicationManager.getDriver();
    }

    public void start(String username, String email) {
        driver.get(applicationManager.getProperty("web.baseUrl") + "/login_page.php");
    }
}
