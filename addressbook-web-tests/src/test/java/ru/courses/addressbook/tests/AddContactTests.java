package ru.courses.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.courses.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class AddContactTests extends TestBase{


  @Test
  public void testAddContact() throws Exception {

    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("testname","testlname","testaddress","123454321","1@1.ru","test1");
    app.getContactHelper().createContact(contact);
    app.goTo().returnHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
