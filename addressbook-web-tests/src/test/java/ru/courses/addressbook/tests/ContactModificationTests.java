package ru.courses.addressbook.tests;

import org.testng.annotations.Test;
import ru.courses.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModification(){
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("testname", "testlname", "testAddress", "1234567890", "test@test.ru", "test1"),true);
            app.getNavigationHelper().gotoHomePage();
        }
        app.getContactHelper().editContact();
        app.getContactHelper().fillContact(new ContactData("Mtestname", "Mtestlname", "MtestAddress", "71234567890", "Mtest@test.ru", null),false);
        app.getContactHelper().saveEditContact();
        app.getNavigationHelper().returnHomePage();
    }
}
