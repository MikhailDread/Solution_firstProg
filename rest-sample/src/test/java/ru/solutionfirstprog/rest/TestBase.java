package ru.solutionfirstprog.rest;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {

    protected static final ApplicationManager applicationManager =
            new ApplicationManager(BrowserType.CHROME);

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        applicationManager.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        applicationManager.stop();
    }

}
