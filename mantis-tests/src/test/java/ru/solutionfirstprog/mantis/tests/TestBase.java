package ru.solutionfirstprog.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.solutionfirstprog.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

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

    boolean isIssueOpen(int issue) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = applicationManager.soap().getMantisConnect();
        IssueData issueId = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(issue));
        if(issueId.getResolution().getName().equals("Решена")){
            return false;
        }
        else {
            return true;
        }
    }

    public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
