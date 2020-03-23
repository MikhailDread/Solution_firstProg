package ru.solutionfirstprog.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.solutionfirstprog.addressbook.appmanager.ApplicationManager;
import ru.solutionfirstprog.addressbook.module.GroupInf;
import ru.solutionfirstprog.addressbook.module.Groups;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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

    public void verifyGroupListInUI() {
        if(Boolean.getBoolean("verifyUI")){
            Groups dbgroups = applicationManager.db().groups();
            Groups uigroups = applicationManager.group().all();
            assertThat(uigroups, equalTo(dbgroups.stream()
                    .map((g) -> new GroupInf().withId(g.getId()).withName(g.getName()))
                    .collect(Collectors.toSet())));
        }
    }

}
