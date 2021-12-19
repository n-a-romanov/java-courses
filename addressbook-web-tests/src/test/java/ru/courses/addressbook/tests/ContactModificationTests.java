package ru.courses.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.courses.addressbook.model.ContactData;
import ru.courses.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModification(){
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact();
            app.getNavigationHelper().gotoHomePage();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().editContact(before.size() - 1);
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"Mktestname", "Mtestlname");
        app.getContactHelper().fillContact(contact,false);
        app.getContactHelper().saveEditContact();
        app.getNavigationHelper().returnHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
