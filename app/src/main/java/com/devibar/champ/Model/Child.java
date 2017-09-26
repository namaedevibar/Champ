package com.devibar.champ.Model;

/**
 * Created by namai on 9/24/2017.
 */

public class Child {

    private String userId;
    private String childId;
    private String guardianId;

    public Child(String userId, String childId, String guardianId) {
        this.userId = userId;
        this.childId = childId;
        this.guardianId = guardianId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public String getGuardianId() {
        return guardianId;
    }

    public void setGuardianId(String guardianId) {
        this.guardianId = guardianId;
    }
}
