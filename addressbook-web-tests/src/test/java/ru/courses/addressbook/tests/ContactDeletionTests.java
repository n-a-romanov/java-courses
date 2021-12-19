package ru.courses.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.courses.addressbook.model.ContactData;
import ru.courses.addressbook.model.GroupData;

import java.util.List;

public class ContactDeletionTests extends TestBase{

    @Test (description = "Удаление контакта из карточки редактирования")
    public void testContactDeletionFromEdit(){
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact();
            app.getNavigationHelper().gotoHomePage();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().editContact(before.size() - 1);
        app.getContactHelper().deleteEditContact();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);
    }

    @Test (description = "Удаление контакта из таблицы")
    public void testContactDeletionFromTable(){
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact();
            app.getNavigationHelper().gotoHomePage();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().checkContact(before.size() - 1);
        app.getContactHelper().deleteContact();
        app.getContactHelper().confirmDeleteContact();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);
    }

    @Test (description = "Удаление всех контактов")
    public void testContactAllDeletionFromTable(){
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact();
            app.getNavigationHelper().gotoHomePage();
        }
        app.getContactHelper().checkAllContact();
        app.getContactHelper().deleteContact();
        app.getContactHelper().confirmDeleteContact();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), 0);
    }
}
