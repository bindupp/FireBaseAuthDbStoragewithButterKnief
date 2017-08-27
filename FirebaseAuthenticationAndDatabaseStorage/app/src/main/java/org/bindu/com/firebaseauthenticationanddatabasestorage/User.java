package org.bindu.com.firebaseauthenticationanddatabasestorage;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {
 
    public String name;
    public String email;
    public String password;
    public String number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public User() {
    }
 
    public User(String name, String email, String password, String number) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.number =number;
    }
}