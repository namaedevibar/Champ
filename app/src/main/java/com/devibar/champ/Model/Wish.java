package com.devibar.champ.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by asus on 03/10/2017.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Wish {
    @JsonProperty
    String name;
    @JsonProperty
    String reward_id;
    @JsonProperty
    String status;

    public Wish() {
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReward_id() {
        return reward_id;
    }

    public void setReward_id(String reward_id) {
        this.reward_id = reward_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Wish(String name, String reward_id, String status) {

        this.name = name;
        this.reward_id = reward_id;
        this.status = status;
    }
}
