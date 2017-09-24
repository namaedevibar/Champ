package com.devibar.champ.Model;

/**
 * Created by namai on 9/24/2017.
 */

public class Child {
    private String firstName;
    private String lastName;

    public Child(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
