package com.eci.cosw.springbootsecureapi.model;

import java.util.ArrayList;

/**
 * @author Santiago Carrillo
 * 8/21/17.
 */
public class User {

    private String id;

    private String email;

    private String password;

    private String firstname;

    private String lastname;

    private String username;
    private ArrayList<Task> tasks;



    public User() {
    }


    public User(String id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        tasks = new ArrayList<>();
    }



    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email='" + email + '\'' + ", password='" + password + '\'' + ", firstname='"
                + firstname + '\'' + '}';
    }
}
