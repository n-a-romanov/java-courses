package ru.courses.addressbook.tests;

import org.testng.annotations.Test;
import ru.courses.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModification(){
        app.getContactHelper().editContact();
        app.getContactHelper().fillContact(new ContactData("Mtestname", "Mtestlname", "MtestAddress", "71234567890", "Mtest@test.ru", null),false);
        app.getContactHelper().saveEditContact();
        app.getNavigationHelper().returnHomePage();
    }
}
