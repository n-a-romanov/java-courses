package ru.courses.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.courses.addressbook.model.ContactData;
import ru.courses.addressbook.model.Contacts;

import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.contact().createContact();
            app.goTo().home();
        }
    }

    @Test(description = "Удаление контакта из карточки редактирования")
    public void testContactDeletionFromEdit() throws InterruptedException {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().home();
        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size() - 1);
        MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.without(deletedContact)));
    }

    @Test(description = "Удаление контакта из таблицы")
    public void testContactDeletionFromTable() {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().deleteCheckedContact(deletedContact);
        app.goTo().home();
        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size() - 1);
        MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.without(deletedContact)));
    }

    @Test(description = "Удаление всех контактов")
    public void testContactAllDeletionFromTable() {
        app.contact().checkAllContact();
        app.contact().deleteContact();
        app.contact().confirmDeleteContact();
        app.goTo().home();
        Contacts after = app.db().contacts();
        assertEquals(after.size(), 0);
    }
}
