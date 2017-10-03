package com.devibar.champ.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devibar.champ.Model.Reward;
import com.devibar.champ.Model.Wish;
import com.devibar.champ.R;

import java.util.ArrayList;

/**
 * Created by namai on 9/27/2017.
 */

public class RewardAdapter extends RecyclerView.Adapter<RewardAdapter.ViewHolder>{

    private ArrayList<Wish> wishList;
    private Context context;
    private ViewHolder holder;

    public RewardAdapter(ArrayList<Wish> wishList) {
        this.wishList = wishList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        final View itemLayoutView = LayoutInflater.from(context)
                .inflate(R.layout.layout_rewards, null);
        holder = new ViewHolder(itemLayoutView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Wish reward = wishList.get(position);
        if (reward!=null){
            holder.tvReward.setText(reward.getName());
        }
    }

    @Override
    public int getItemCount() {
        return wishList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvReward;


        public ViewHolder(View itemView) {
            super(itemView);

            tvReward = itemView.findViewById(R.id.txtReward);

        }
    }
}
