package ru.solutionfirstprog.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.solutionfirstprog.mantis.appmanager.ApplicationManager;
import ru.solutionfirstprog.mantis.appmanager.HelperBase;

public class RegistrationHelper extends HelperBase {
    private ApplicationManager applicationManager;
    private WebDriver driver;

    public RegistrationHelper(ApplicationManager applicationManager) {
        super(applicationManager);
    }

    public void start(String username, String email) {
        driver.get(applicationManager.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[value='Signup'"));
    }

    public void finish(String confirmationLink, String password) {
        driver.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("input[value='Update User'"));
    }
}
