package ru.courses.addressbook.tests;

import org.testng.annotations.Test;
import ru.courses.addressbook.model.ContactData;

public class AddContactTests extends TestBase{


  @Test
  public void testAddContact() throws Exception {

    app.getContactHelper().createContact(new ContactData("testname", "testlname", "testAddress", "1234567890", "test@test.ru", "test1"),true);
    app.getNavigationHelper().returnHomePage();
  }


}
