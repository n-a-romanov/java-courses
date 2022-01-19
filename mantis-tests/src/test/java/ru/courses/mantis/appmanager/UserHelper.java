package ru.courses.mantis.appmanager;

import ru.courses.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.util.List;

public class UserHelper extends HelperBase {

    public UserHelper(ApplicationManager app){
        super(app);
    }

    public void resetPasswd (String username, String password, String newPassword) throws MessagingException {
        List<MailMessage> mailMessages = app.james().waitForMail(username, password, 60000);
        String confirmationLink = app.james().findConfirmationLink(mailMessages, username + "@localhost");
        app.registration().finish(confirmationLink, newPassword);
    }
}
