package ru.courses.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.courses.addressbook.model.ContactData;
import ru.courses.addressbook.model.GroupData;
import ru.courses.addressbook.model.Groups;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ru.courses.addressbook.tests.TestBase.app;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void deleteSelectedGroups() {
        click(By.name("delete"));
    }

    public void selectGroupById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        groupCache = null;
        returnToGroupPage();
    }

    public void modify(GroupData group) {
        selectGroupById(group.getId());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        groupCache = null;
        returnToGroupPage();
    }

    public void delete(GroupData group) {
        selectGroupById(group.getId());
        deleteSelectedGroups();
        groupCache = null;
        returnToGroupPage();
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));

    }

    private Groups groupCache = null;

    public Groups all() {
        if (groupCache != null) {
            return new Groups(groupCache);
        }

        groupCache = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groupCache.add(new GroupData().withId(id).withName(name));
        }
        return new Groups(groupCache);
    }

    public GroupData addGroupAndCheck(GroupData group) {
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("testsG1"));

        int max = 0;
        for (GroupData g : app.db().groups()) {
            if (g.getId() > max) {
                max = g.getId();
                group = g.withId(max);
            }
        }
        app.goTo().home();
        return group;
    }

    public void deleteContactFromGroup(ContactData contact, GroupData group) {
        selectGroupFromDropDownListById(group);
        deleteSelectedContactFromGroup(contact);
    }

    private void deleteSelectedContactFromGroup(ContactData contact) {
        app.contact().checkContact(contact.getId());
        click(By.name("remove"));
    }

    private void selectGroupFromDropDownListById(GroupData group) {
        app.goTo().home();
        new Select(wd.findElement(By.xpath("//select[@name='group']"))).selectByValue(String.valueOf(group.getId()));
    }
}
