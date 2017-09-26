package com.devibar.champ.Model;

/**
 * Created by namai on 9/26/2017.
 */

public class Guardian {

    private String userId;
    private String guardianId;

    public Guardian(String userId, String guardianId) {
        this.userId = userId;
        this.guardianId = guardianId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGuardianId() {
        return guardianId;
    }

    public void setGuardianId(String guardianId) {
        this.guardianId = guardianId;
    }
}
