package com.devibar.champ.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.devibar.champ.Activity.ChildProfileActivity;
import com.devibar.champ.Model.Child;
import com.devibar.champ.Model.User;
import com.devibar.champ.R;

import java.util.ArrayList;

/**
 * Created by namai on 9/24/2017.
 */

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ViewHolder>{

    private ArrayList<User> children;
    private Context context;
    private ViewHolder holder;

    public ChildAdapter(ArrayList<User> children) {
        this.children = children;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        final View itemLayoutView = LayoutInflater.from(context)
                .inflate(R.layout.layout_child, null);
        holder = new ViewHolder(itemLayoutView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User item = children.get(position);

        if (item!=null){
            holder.tvName.setText(item.getFirstName());
            holder.tvDescription.setText("No Pending Tasks.");
            Glide.with(context).load(R.drawable.champ_logo)
                    .into(holder.tvImage);


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ChildProfileActivity.class);
                    context.startActivity(intent);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return children.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvDescription;
        ImageView tvImage;


        public ViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.txtName);
            tvDescription = itemView.findViewById(R.id.txtDescription);
            tvImage = itemView.findViewById(R.id.imgChild);


        }
    }
}
