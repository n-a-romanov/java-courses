package ru.courses.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{

    @Test (description = "Удаление контакта из карточки редактирования")
    public void testContactDeletionFromEdit(){
        app.getContactHelper().editContact();
        app.getContactHelper().deleteEditContact();
        app.getNavigationHelper().gotoHomePage();
    }

    @Test (description = "Удаление контакта из таблицы")
    public void testContactDeletionFromTable(){
        app.getContactHelper().checkContact();
        app.getContactHelper().deleteContact();
        app.getContactHelper().confirmDeleteContact();
        app.getNavigationHelper().gotoHomePage();
    }

    @Test (description = "Удаление всех контактов")
    public void testContactAllDeletionFromTable(){
        app.getContactHelper().checkAllContact();
        app.getContactHelper().deleteContact();
        app.getContactHelper().confirmDeleteContact();
        app.getNavigationHelper().gotoHomePage();
    }
}
