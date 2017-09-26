package com.devibar.champ.Model;

/**
 * Created by namai on 9/26/2017.
 */

public class Reward {
    private String rewardId;
    private String name;
    private String childId;
    private String status;

    public Reward(String rewardId, String name, String childId, String status) {
        this.rewardId = rewardId;
        this.name = name;
        this.childId = childId;
        this.status = status;
    }

    public String getRewardId() {
        return rewardId;
    }

    public void setRewardId(String rewardId) {
        this.rewardId = rewardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
