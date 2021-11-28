package ru.courses.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletionFromEdit(){
        app.getContactHelper().editContact();
        app.getContactHelper().deleteEditContact();
        app.getNavigationHelper().gotoHomePage();
    }

    @Test
    public void testContactDeletionFromTable(){
        app.getContactHelper().checkContact();
        app.getContactHelper().deleteContact();
        app.getContactHelper().confirmDeleteContact();
        app.getNavigationHelper().gotoHomePage();
    }
}
