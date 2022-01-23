package ru.courses.mantis.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mantis_user_table")
public class UserData {
    @Id
    private int id;
    private String username;
    private String email;
    @Column (name = "access_level")
    private short accessLevel;

    public short getAccessLevel() {
        return accessLevel;
    }

    public UserData setAccessLevel(short accessLevel) {
        this.accessLevel = accessLevel;
        return this;
    }

    public int getId() {
        return id;
    }

    public UserData setId(int id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserData setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserData setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public String toString() {
        return "UserData{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            '}';
    }
}
