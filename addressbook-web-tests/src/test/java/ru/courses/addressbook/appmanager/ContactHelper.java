package ru.courses.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.courses.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void saveContact() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContact(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getPhoneHome());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
            if (isGroupFind(contactData)) {
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
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

    public void createContact() {
        addNew();
        fillContact(new ContactData("testname", "testlname", "testAddress", "1234567890", "test@test.ru", "test1"), true);
        saveContact();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public boolean isGroupFind(ContactData contactData) {
        String xpath = "//select[@name='new_group']/option[contains(text(),'" + contactData.getGroup() + "')]";
        return isElementPresent(By.xpath(xpath));
    }
}
