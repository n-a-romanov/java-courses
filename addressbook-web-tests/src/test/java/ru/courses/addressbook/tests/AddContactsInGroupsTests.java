package ru.courses.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.courses.addressbook.model.ContactData;
import ru.courses.addressbook.model.GroupData;
import ru.courses.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactsInGroupsTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
        if (app.db().contacts().size() == 0) {
            app.contact().createContact();
        }
        app.goTo().home();
    }

    @Test
    public void AddContactsInGroup() {
        ContactData modifiedContact = app.db().contacts().iterator().next();
        GroupData selectedGroup = app.db().groups().iterator().next();
        Groups before = modifiedContact.getGroups();
        if (modifiedContact.getGroups().size() == 0) {
            selectedGroup = app.group().addGroupAndCheck(selectedGroup);
        } else if (app.contact().isContactGroupFind(modifiedContact, app.db().groups())) {
            selectedGroup = app.group().addGroupAndCheck(selectedGroup);
        }
        app.contact().addGroupToContact(modifiedContact, selectedGroup);
        app.goTo().home();
        Groups after = app.db().contacts().iterator().next().withId(modifiedContact.getId()).getGroups();
        assertThat(after, equalTo(before.withAdded(selectedGroup)));
    }


}

