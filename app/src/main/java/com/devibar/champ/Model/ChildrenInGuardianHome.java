package com.devibar.champ.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by asus on 27/09/2017.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class ChildrenInGuardianHome {
    @JsonProperty
    String firstName;
    @JsonProperty
    String lastname;
    @JsonProperty
    String child_id;
    @JsonProperty
    String status;

    public ChildrenInGuardianHome() {

    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getChild_id() {
        return child_id;
    }

    public void setChild_id(String child_id) {
        this.child_id = child_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ChildrenInGuardianHome(String firstName, String lastname, String child_id, String status) {

        this.firstName = firstName;
        this.lastname = lastname;
        this.child_id = child_id;
        this.status = status;
    }
}
