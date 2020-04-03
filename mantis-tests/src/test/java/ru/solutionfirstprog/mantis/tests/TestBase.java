package ru.solutionfirstprog.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.solutionfirstprog.mantis.appmanager.ApplicationManager;

import java.io.File;

public class TestBase {

    protected static final ApplicationManager applicationManager =
            new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        applicationManager.init();
        //applicationManager.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc", "config_inc.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
       // applicationManager.ftp().restore("config_inc.php.bak", "config_inc.php");
        applicationManager.stop();
    }
}
