package ru.courses.mantis.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.courses.mantis.appmanager.HttpSession;
import ru.courses.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase{

    @BeforeMethod
    public void ensurePrecondition() throws MessagingException {
        if (app.db().users().size() == 0) {
            long now = System.currentTimeMillis();
            String user = String.format("user%s", now);
            String email = String.format("user%s@localhost", now);
            String password = "password";
            app.registration().start(user, email);
            app.james().createUser(user, password);
            List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
            String confirmationLink = app.james().findConfirmationLink(mailMessages, email);
            app.registration().finish(confirmationLink, password);
            app.admin().logout();
        }
    }

    @Test
    public void testChangePasswd() throws IOException, MessagingException {
        String user = app.db().users().iterator().next().getUsername();
        String password = "password";
        String newPassword = "pass";
        app.admin().login();
        app.admin().resetPasswd(user, password);
        app.user().resetPasswd(user,password, newPassword);
        assertTrue(app.newSession().login(user,newPassword));
    }
}
