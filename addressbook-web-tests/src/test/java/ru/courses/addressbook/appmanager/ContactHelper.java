package ru.courses.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.courses.addressbook.model.ContactData;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void saveContact() {
      click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContact(ContactData contactData) {
        type(By.name("firstname"),contactData.getName());
        type(By.name("lastname"),contactData.getLastname());
        type(By.name("address"),contactData.getAddress());
        type(By.name("home"),contactData.getPhoneHome());
        type(By.name("email"),contactData.getEmail());
    }

    public void addNew() {
      click(By.linkText("add new"));
    }

    public void editContact() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void saveEditContact() {
        click(By.xpath("//div[@id='content']/form/input[22]"));
    }

    public void deleteEditContact() {
        click(By.xpath("//div[@id='content']/form[2]/input[2]"));
    }

    public void checkContact() {
        click(By.name("selected[]"));
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void confirmDeleteContact() {
        alertAccept();
    }

    public void checkAllContact() {
        click(By.id("MassCB"));
    }
}
