package com.devibar.champ.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cheekiat.indicatorsteplib.StepProgress;
import com.devibar.champ.Activity.TaskActivity;
import com.devibar.champ.Fragment.ManageTaskDialogFragment;
import com.devibar.champ.Model.Task;
import com.devibar.champ.R;

import java.util.ArrayList;

/**
 * Created by namai on 9/26/2017.
 */

public class TaskAdapter  extends RecyclerView.Adapter<TaskAdapter.ViewHolder>{

    private final String[] labels = {"To Do","On-Going", "Completed"};

    private ArrayList<Task> tasks;
    private Context context;
    private ViewHolder holder;
    private FragmentManager fragmentManager;
    private String guardianName;
    private String id;


    public TaskAdapter(ArrayList<Task> tasks, FragmentManager fragmentManager, String id) {
        this.tasks = tasks;
        this.fragmentManager = fragmentManager;
        this.id = id;

    }

    public TaskAdapter(ArrayList<Task> tasks, String guardianName, String id) {
        this.tasks = tasks;
        this.guardianName = guardianName;
        this.id = id;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        final View itemLayoutView = LayoutInflater.from(context)
                .inflate(R.layout.layout_tasks, null);
        holder = new ViewHolder(itemLayoutView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Task task = tasks.get(position);
        if (task!=null){
            holder.tvTask.setText(task.getTaskName());
            holder.txtStatus.setText(task.getStatus());


            for (int i = 0; i < 3; i++) {
                holder.tvStatus.addDot();
            }

            if (task.getStatus().equals("To Do")){
                holder.tvStatus.selected(0);
            }else if (task.getStatus().equals("On-Going")){
                holder.tvStatus.selected(1);
            }else if (task.getStatus().equals("Pending")){
                holder.tvStatus.selected(1);
            }else {
                holder.tvStatus.selected(2);
            }


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (fragmentManager!=null){
                        ManageTaskDialogFragment manageTaskDialogFragment;

                        if (task.getStatus().equals("To Do")){
                            manageTaskDialogFragment = ManageTaskDialogFragment.newInstance("EDIT",task,id);
                        }else if (task.getStatus().equals("Pending")){
                            manageTaskDialogFragment = ManageTaskDialogFragment.newInstance("PENDING",task,id);
                        } else{
                            manageTaskDialogFragment = ManageTaskDialogFragment.newInstance("VIEW",task,id);
                        }

                        manageTaskDialogFragment.show(fragmentManager,"");
                    }else {
                        Intent intent = new Intent(context, TaskActivity.class);
                        intent.putExtra("TASK",task);
                        intent.putExtra("guardianName",guardianName);
                        intent.putExtra("id",id);
                        context.startActivity(intent);
                    }

                }
            });





        }

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTask;
        TextView txtStatus;
        StepProgress tvStatus;


        public ViewHolder(View itemView) {
            super(itemView);

            tvTask = itemView.findViewById(R.id.txtTask);
            tvStatus = itemView.findViewById(R.id.status);
            txtStatus = itemView.findViewById(R.id.txtStatus);


        }
    }
}
