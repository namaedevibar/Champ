package com.devibar.champ.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by namai on 9/24/2017.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Child implements Serializable{

    @JsonProperty
    private String user_id;
    @JsonProperty
    private String child_id;
    @JsonProperty
    private String guardian_id;
    @JsonProperty
    private String firstName;
    @JsonProperty
    private String lastName;
    @JsonProperty
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getChild_id() {
        return child_id;
    }

    public void setChild_id(String child_id) {
        this.child_id = child_id;
    }

    public String getGuardian_id() {
        return guardian_id;
    }

    public void setGuardian_id(String guardian_id) {
        this.guardian_id = guardian_id;
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

    public Child(String user_id, String child_id, String guardian_id, String firstName, String lastName, String status) {

        this.user_id = user_id;
        this.child_id = child_id;
        this.guardian_id = guardian_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
    }

    public Child() {
    }
}
