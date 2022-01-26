package ru.courses.mantis.appmanager;

import org.openqa.selenium.By;

import javax.mail.MessagingException;

public class AdministrationHelper extends HelperBase {

    public AdministrationHelper(ApplicationManager app){
        super(app);
    }

    public void login (){
        wd.get(app.getProperty("web.baseUrl") + "login_page.php");
        type(By.name("username"), app.getProperty("web.adminLogin"));
        type(By.name("password"), app.getProperty("web.adminPassword"));
        click(By.cssSelector("input.button"));
    }

    public void logout (){
        click(By.id("logout-link"));
    }

    public void initResetPassword(String username, String mailPassword) throws MessagingException {
        if (!app.james().doesUserExist(username)) {
            app.james().createUser(username,mailPassword);
        }
        app.james().drainEmail(username,mailPassword);
        click(By.cssSelector("a.manage-menu-link"));
        click(By.xpath("//a[contains(@href, '/manage_user_page.php')]"));
        click(By.linkText(username));
        click(By.xpath("//form[@id='manage-user-reset-form']/fieldset/span/input"));
    }

}
