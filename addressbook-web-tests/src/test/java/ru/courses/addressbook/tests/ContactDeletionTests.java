package ru.courses.addressbook.tests;

import org.testng.annotations.Test;
import ru.courses.addressbook.model.ContactData;
import ru.courses.addressbook.model.GroupData;

public class ContactDeletionTests extends TestBase{

    @Test (description = "Удаление контакта из карточки редактирования")
    public void testContactDeletionFromEdit(){
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact();
            app.getNavigationHelper().gotoHomePage();
        }
        app.getContactHelper().editContact();
        app.getContactHelper().deleteEditContact();
        app.getNavigationHelper().gotoHomePage();
    }

    @Test (description = "Удаление контакта из таблицы")
    public void testContactDeletionFromTable(){
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact();
            app.getNavigationHelper().gotoHomePage();
        }
        app.getContactHelper().checkContact();
        app.getContactHelper().deleteContact();
        app.getContactHelper().confirmDeleteContact();
        app.getNavigationHelper().gotoHomePage();
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
    }
}
