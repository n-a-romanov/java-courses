package ru.courses.mantis.tests;

import org.testng.annotations.Test;
import ru.courses.mantis.appmanager.HttpSession;

import javax.mail.MessagingException;
import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase{

    @Test
    public void testChangePasswd() throws IOException, MessagingException {
        String user = "user1642527295136";
        String password = "password";
        String newPassword = "pass";
        app.admin().login();
        app.admin().resetPasswd(user, password);
        app.user().resetPasswd(user,password, newPassword);
        assertTrue(app.newSession().login(user,newPassword));
    }
}
