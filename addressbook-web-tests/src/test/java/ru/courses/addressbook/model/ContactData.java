package ru.courses.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "addressbook")
public class ContactData {
    @Expose
    @Column(name = "firstname")
    private String name;
    @Expose
    private String lastname;
    @Expose
    @Type(type = "text")
    private String address;
    @Type(type = "text")
    private String address2;
    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String phoneHome;
    @Column(name = "mobile")
    @Type(type = "text")
    private String phoneMobile;
    @Column(name = "work")
    @Type(type = "text")
    private String phoneWork;
    @Type(type = "text")
    private String phone2;
    @Transient
    private String allPhones;
    @Type(type = "text")
    private String email2;
    @Type(type = "text")
    private String email3;
    @Transient
    private String allEmails;
    @Expose
    @Type(type = "text")
    private String email;
    @Id
    private int id = Integer.MAX_VALUE;
    @Expose

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<>();

    @Type(type = "text")
    private String photo;

    public File getPhoto() {
        return new File(photo);
    }

    public ContactData withGroup(GroupData group) {
        groups.add(group);
        return this;
    }
    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public ContactData withName(String name) {
        this.name = name;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withAddress2(String address2) {
        this.address2 = address2;
        return this;
    }

    public ContactData withPhoneHome(String phoneHome) {
        this.phoneHome = phoneHome;
        return this;
    }

    public ContactData withPhoneMobile(String phoneMobile) {
        this.phoneMobile = phoneMobile;
        return this;
    }

    public ContactData withPhoneWork(String phoneWork) {
        this.phoneWork = phoneWork;
        return this;
    }


    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getEmail2() {
        return email2;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public String getEmail3() {
        return email3;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public int getId() {
        return id;
    }

    public String getPhoneMobile() {
        return phoneMobile;
    }

    public String getPhoneWork() {
        return phoneWork;
    }

    public String getPhone2() {
        return phone2;
    }

    public ContactData withPhone2(String phone2) {
        this.phone2 = phone2;
        return this;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
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

    public String getAddress2() {
        return address2;
    }

    public String getPhoneHome() {
        return phoneHome;
    }

    public Groups getGroups() {
        return new Groups(groups);
    }

    public int getGroupId(GroupData groups) {
        return groups.getId();
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(lastname, that.lastname) && Objects.equals(address, that.address) && Objects.equals(phoneHome, that.phoneHome) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastname, address, phoneHome, email, id);
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
