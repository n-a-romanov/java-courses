package ru.courses.addressbook.tests;

import org.testng.annotations.Test;
import ru.courses.addressbook.model.ContactData;

public class AddContactTests extends TestBase{


  @Test
  public void testAddContact() throws Exception {

    app.getContactHelper().addNew();
    app.getContactHelper().fillContact(new ContactData("testname", "testlname", "testAddress", "1234567890", "test@test.ru"));
    app.getContactHelper().saveContact();
    app.getNavigationHelper().returnHomePage();
  }


}
