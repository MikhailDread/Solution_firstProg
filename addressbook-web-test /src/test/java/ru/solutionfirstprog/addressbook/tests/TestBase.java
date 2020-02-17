package ru.solutionfirstprog.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.solutionfirstprog.addressbook.appmanager.ApplicationManager;

public class TestBase {

    protected final ApplicationManager applicationManager = new ApplicationManager();

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        applicationManager.init();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        applicationManager.stop();
    }

}
