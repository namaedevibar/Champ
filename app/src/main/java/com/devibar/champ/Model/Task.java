package com.devibar.champ.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by namai on 9/26/2017.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Task implements Parcelable{
    @JsonProperty
    private String taskId;
    @JsonProperty
    private String taskName;
    @JsonProperty
    private String taskDescription;
    @JsonProperty
    private String status;

    public Task() {
    }

    public Task(String taskId, String taskName, String taskDescription, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.status = status;
    }

    protected Task(Parcel in) {
        taskId = in.readString();
        taskName = in.readString();
        taskDescription = in.readString();
        status = in.readString();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(taskId);
        parcel.writeString(taskName);
        parcel.writeString(taskDescription);
        parcel.writeString(status);
    }
}
