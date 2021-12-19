package ru.courses.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.courses.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

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

    public void editContact(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }

    public void saveEditContact() {
        click(By.xpath("//div[@id='content']/form/input[22]"));
    }

    public void deleteEditContact() {
        click(By.xpath("//div[@id='content']/form[2]/input[2]"));
    }

    public void checkContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
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

    public void createContact(ContactData contactData) {
        addNew();
        fillContact(contactData, true);
        saveContact();
    }

    public void createContact() {
        addNew();
        fillContact(new ContactData("testname","testlname","testaddress","123454321","1@1.ru","test1"), true);
        saveContact();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public boolean isGroupFind(ContactData contactData) {
        String xpath = "//select[@name='new_group']/option[contains(text(),'" + contactData.getGroup() + "')]";
        return isElementPresent(By.xpath(xpath));
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> rows = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement column : rows) {
            String name = column.findElements(By.tagName("td")).get(2).getText();
            String lastname = column.findElements(By.tagName("td")).get(1).getText();
            int id = Integer.parseInt(column.findElement(By.xpath("td/input")).getAttribute("id"));
            ContactData contact = new ContactData(id, name, lastname);
            contacts.add(contact);

        }
        return contacts;
    }
}
