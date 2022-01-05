package ru.courses.addressbook.tests;

import org.testng.annotations.Test;
import ru.courses.addressbook.model.ContactData;
import ru.courses.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactTests extends TestBase{


  @Test
  public void testAddContact() throws Exception {

    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
        .withName("testname")
        .withLastname("testlname")
        .withAddress("testaddress")
        .withPhoneHome("123454321")
        .withEmail("1@1.ru")
        .withGroup("test1");
    app.contact().createContact(contact);
    app.goTo().homePage();
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
        before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }


}
