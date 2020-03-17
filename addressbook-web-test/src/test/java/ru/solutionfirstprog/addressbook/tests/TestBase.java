package ru.solutionfirstprog.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.solutionfirstprog.addressbook.appmanager.ApplicationManager;

public class TestBase {

    protected static final ApplicationManager applicationManager =
            new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        applicationManager.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        applicationManager.stop();
    }

}
