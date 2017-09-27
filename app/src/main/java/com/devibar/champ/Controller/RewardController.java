package com.devibar.champ.Controller;

import com.devibar.champ.Model.Reward;

import java.util.ArrayList;

/**
 * Created by namai on 9/27/2017.
 */

public class RewardController {

    public static ArrayList<Reward> getRewards(){
        ArrayList<Reward> rewards = new ArrayList<>();

        rewards.add(new Reward("","HongKong","","Not Claimed"));
        rewards.add(new Reward("","Jollibee","","Not Claimed"));
        rewards.add(new Reward("","McDo","","Not Claimed"));
        rewards.add(new Reward("","Toy Kingdom","","Not Claimed"));

        return rewards;
    }
}
