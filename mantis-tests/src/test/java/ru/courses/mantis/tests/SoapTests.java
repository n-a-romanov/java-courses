package ru.courses.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.courses.mantis.model.Issue;
import ru.courses.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class SoapTests extends TestBase{
    
//    @BeforeMethod
//    public void precondition () throws MalformedURLException, ServiceException, RemoteException {
//        skipIfNotFixed(1);
//    }

    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        skipIfNotFixed(1);
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for (Project project : projects) {
            System.out.println(project.getName());
        }
    }

    @Test
    public void testCreateIssue() throws MalformedURLException, ServiceException, RemoteException {
        skipIfNotFixed(2);
        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue().setSummary("Test issue").setDescription("Test issue description").setProject(projects.iterator().next());
        Issue created = app.soap().addIssue(issue);
        Assert.assertEquals(issue.getSummary(), created.getSummary());

    }
}
