package ru.paalse.service;

import ru.paalse.persist.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

// DTO Data Transfer Object
public class UserRepr {

    private long id;

    @NotEmpty
    private String username;


    private Integer age;

    @Email
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String matchingPassword;

    public UserRepr() {
    }

    public UserRepr(String username) {
        this.username = username;
    }

    public UserRepr(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.age = user.getAge();
        this.email = user.getEmail();
        this.password = user.getPassword();
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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
