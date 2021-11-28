package ru.courses.addressbook.model;

public class ContactData {
    private final String name;
    private final String lastname;
    private final String address;
    private final String phoneHome;
    private final String email;

    public ContactData(String name, String lastname, String address, String phoneHome, String email) {
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.phoneHome = phoneHome;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneHome() {
        return phoneHome;
    }

    public String getEmail() {
        return email;
    }
}
