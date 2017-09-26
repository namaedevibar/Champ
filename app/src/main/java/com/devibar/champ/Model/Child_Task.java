package com.devibar.champ.Model;

/**
 * Created by namai on 9/26/2017.
 */

public class Child_Task {

    private String childId;
    private String taskId;
    private String rewardId;
    private String status;

    public Child_Task(String childId, String taskId, String rewardId, String status) {
        this.childId = childId;
        this.taskId = taskId;
        this.rewardId = rewardId;
        this.status = status;
    }

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getRewardId() {
        return rewardId;
    }

    public void setRewardId(String rewardId) {
        this.rewardId = rewardId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
