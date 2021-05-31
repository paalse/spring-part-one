package ru.paalse.persist;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class User {
    private long id;

    @NotEmpty
    private String username;

    @Email
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String matchingPassword;

    public User(String username) {
        this.username = username;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
}
