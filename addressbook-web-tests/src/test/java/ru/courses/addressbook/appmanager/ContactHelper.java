package ru.courses.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.courses.addressbook.model.ContactData;
import ru.courses.addressbook.model.Contacts;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

    public void editContactById(int id) {
        wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();
    }

    public void saveEditContact() {
        click(By.xpath("//div[@id='content']/form/input[22]"));
    }

    public void deleteEditContact() throws InterruptedException {
        click(By.xpath("//div[@id='content']/form[2]/input[2]"));
        TimeUnit.SECONDS.sleep(1);
    }

    public void checkContact(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void delete(ContactData contact) throws InterruptedException {
        editContactById(contact.getId());
        deleteEditContact();
    }

    public void deleteCheckedContact(ContactData contact) {
        checkContact(contact.getId());
        deleteContact();
        confirmDeleteContact();
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
        fillContact(new ContactData()
            .withName("testname")
            .withLastname("testlname")
            .withAddress("testaddress")
            .withPhoneHome("123454321")
            .withEmail("1@1.ru")
            .withGroup("test1"), true);
        saveContact();
    }

    public void modify(ContactData contact) {
        editContactById(contact.getId());

        fillContact(contact,false);
        saveEditContact();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public boolean isGroupFind(ContactData contactData) {
        String xpath = "//select[@name='new_group']/option[contains(text(),'" + contactData.getGroup() + "')]";
        return isElementPresent(By.xpath(xpath));
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> rows = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement column : rows) {
            String name = column.findElements(By.tagName("td")).get(2).getText();
            String lastname = column.findElements(By.tagName("td")).get(1).getText();
            String address = column.findElements(By.tagName("td")).get(3).getText();
            String allEmails = column.findElements(By.tagName("td")).get(4).getText();
            String allPhones = column.findElements(By.tagName("td")).get(5).getText();
            int id = Integer.parseInt(column.findElement(By.xpath("td/input")).getAttribute("id"));
            contacts.add(new ContactData()
                .withId(id)
                .withName(name)
                .withLastname(lastname)
                .withAllPhones(allPhones)
                .withAddress(address)
                .withAllEmails(allEmails));

        }
        return contacts;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        editContactById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String phone2 = wd.findElement(By.name("phone2")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String address2 = wd.findElement(By.name("address2")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData()
            .withId(contact.getId())
            .withName(firstname)
            .withLastname(lastname)
            .withPhoneHome(home)
            .withPhoneMobile(mobile)
            .withPhoneWork(work)
            .withPhone2(phone2)
            .withAddress(address)
            .withAddress2(address2)
            .withEmail(email)
            .withEmail2(email2)
            .withEmail3(email3);
    }
}
