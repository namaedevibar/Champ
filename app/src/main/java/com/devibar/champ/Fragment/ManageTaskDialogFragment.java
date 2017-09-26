package com.devibar.champ.Fragment;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.devibar.champ.Interface.OnManageTaskListener;
import com.devibar.champ.Model.Task;
import com.devibar.champ.R;
import com.devibar.champ.Utility.DialogUtility;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import fr.tvbarthel.lib.blurdialogfragment.SupportBlurDialogFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManageTaskDialogFragment extends SupportBlurDialogFragment {

    private OnManageTaskListener mManageTaskListener;

    private TextView mTask;
    private EditText mTaskName;
    private EditText mTaskDesc;
    private MaterialSpinner mRewards;
    private Button mAdd;

    private List<String> rewardsList;
    private Task task;
    private String purpose;

    //ADD TASK
    public static ManageTaskDialogFragment newInstance(String purpose) {

        Bundle args = new Bundle();
        args.putString("PURPOSE",purpose);

        ManageTaskDialogFragment fragment = new ManageTaskDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //EDIT TASK
    public static ManageTaskDialogFragment newInstance(String purpose, Task task) {

        Bundle args = new Bundle();
        args.putString("PURPOSE",purpose);
        args.putParcelable("TASK",task);

        ManageTaskDialogFragment fragment = new ManageTaskDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            this.mManageTaskListener = (OnManageTaskListener) activity;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_add_task_dialog, null);
        builder.setView(view);
        final Dialog dialog = builder.create();

        mTask = view.findViewById(R.id.tvAddTask);
        mTaskName = view.findViewById(R.id.etTaskName);
        mTaskDesc = view.findViewById(R.id.etTaskDescription);
        mRewards = view.findViewById(R.id.spnRewards);
        mAdd = view.findViewById(R.id.btnAdd);

        rewardsList = new ArrayList<>();
        task = new Task();
        rewardsList.add("Select your child's reward.");

        // TODO: Fetch Rewards 
        mRewards.setItems(rewardsList);

        if (getArguments()!=null){
           purpose = getArguments().getString("PURPOSE");
            if (purpose.equals("EDIT")){
                mAdd.setText("EDIT");
                mTask.setText("EDIT TASK");
                task = getArguments().getParcelable("TASK");
                if (task!=null){
                    mTaskName.setText(task.getTaskName());
                    mTaskDesc.setText(task.getTaskDescription());
                }
            }
        }





        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taskName = mTaskName.getText().toString();
                String taskDesc = mTaskDesc.getText().toString();

                if (taskName.equals("") || taskDesc.equals("")){
                    DialogUtility.messageDialog("Please don't leave any empty field.",getContext());
                }else {
                    if (purpose.equals("ADD")){
                        task = new Task("",taskName,taskDesc,"To Do");
                        mManageTaskListener.manageTask("ADD",task);
                    }else {
                        task.setTaskDescription(taskDesc);
                        task.setTaskName(taskName);
                        mManageTaskListener.manageTask("EDIT",task);
                    }
                    dismiss();
                }



            }
        });


        return dialog;


    }



    @Override
    protected boolean isDimmingEnable() {
        return true;
    }


}
