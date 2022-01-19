package ru.courses.mantis.appmanager;

import org.openqa.selenium.By;

import javax.mail.MessagingException;

public class AdministrationHelper extends HelperBase {

    public AdministrationHelper(ApplicationManager app){
        super(app);
    }

    public void login (){
        type(By.name("username"), "administrator");
        type(By.name("password"), "root");
        click(By.cssSelector("input.button"));
    }

    public void resetPasswd (String username, String password) throws MessagingException {
        if (!app.james().doesUserExist(username)) {
            app.james().createUser(username,password);
        }
        app.james().drainEmail(username,password);
        click(By.cssSelector("a.manage-menu-link"));
        click(By.xpath("//a[contains(@href, '/mantisbt/manage_user_page.php')]"));
        click(By.linkText(username));
        click(By.xpath("//form[@id='manage-user-reset-form']/fieldset/span/input"));
    }

}
