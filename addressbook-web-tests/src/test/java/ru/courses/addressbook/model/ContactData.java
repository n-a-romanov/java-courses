package ru.courses.addressbook.model;

import java.util.Objects;

public class ContactData {
    private final String name;
    private final String lastname;
    private final String address;
    private final String phoneHome;
    private final String email;
    private int id;
    private String group;

    public int getId() {
        return id;
    }

    public ContactData setId(int id) {
        this.id = id;
        return this;
    }

    public ContactData(int id, String name, String lastname) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.address = null;
        this.phoneHome = null;
        this.email = null;
        this.group = null;
    }

    public ContactData(String name, String lastname, String address, String phoneHome, String email, String group) {
        this.id = Integer.MAX_VALUE;
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.phoneHome = phoneHome;
        this.email = email;
        this.group = group;
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

    public String getEmail() { return email; }

    public String getGroup() { return group; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(name, that.name) && Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastname);
    }

    @Override
    public String toString() {
        return "ContactData{" +
            "name='" + name + '\'' +
            ", lastname='" + lastname + '\'' +
            ", id=" + id +
            '}';
    }


}
