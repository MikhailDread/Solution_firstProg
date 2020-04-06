package ru.solutionfirstprog.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.solutionfirstprog.mantis.model.Issue;
import ru.solutionfirstprog.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class SoapTests extends TestBase{

    @Test
    public void testGetProject() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = applicationManager.soap().getProject();
        System.out.println(projects.size());
        for(Project pj : projects){
            System.out.println(pj.getName());
        }
    }

    @Test
    public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
        Set<Project> projects = applicationManager.soap().getProject();
        Issue issue = new Issue().withSummary("Test issue").withDescription("Test issue description").withProject(projects.iterator().next());
        Issue created = applicationManager.soap().addIssue(issue);
        Assert.assertEquals(issue.getSummary(), created.getSummary());
    }
}
