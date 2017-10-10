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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.devibar.champ.Interface.OnManageTaskListener;
import com.devibar.champ.Model.Task;
import com.devibar.champ.Model.Wish;
import com.devibar.champ.R;
import com.devibar.champ.Utility.DialogUtility;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
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
    Firebase childWishlist;
    private MaterialSpinner mRewards;
    private Button mAdd;
    Wish wish;
    private List<Wish> rewardsList;
    private List<String> list;
    private Task task;
    private String purpose;

    //ADD TASK
    public static ManageTaskDialogFragment newInstance(String purpose, String child_id) {

        Bundle args = new Bundle();
        args.putString("PURPOSE",purpose);
        args.putString("child_id",child_id);

        ManageTaskDialogFragment fragment = new ManageTaskDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //EDIT OR VIEW TASK
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
        childWishlist = new Firebase("https://finalsattendanceapp.firebaseio.com/CHILD_REWARD");



        mTask = view.findViewById(R.id.tvAddTask);
        mTaskName = view.findViewById(R.id.etTaskName);
        mTaskDesc = view.findViewById(R.id.etTaskDescription);
        mRewards = view.findViewById(R.id.spnRewards);
        mAdd = view.findViewById(R.id.btnAdd);

        rewardsList = new ArrayList<>();
        list = new ArrayList<>();
        task = new Task();
        wish = new Wish();






        if (getArguments()!=null){
           purpose = getArguments().getString("PURPOSE");
            if (task!=null){
                if (purpose.equals("EDIT")){
                    mAdd.setText("EDIT");
                    mTask.setText("EDIT TASK");
                    task = getArguments().getParcelable("TASK");
                    mTaskName.setText(task.getTaskName());
                    mTaskDesc.setText(task.getTaskDescription());
                }else if(purpose.equals("VIEW")) {
                    task = getArguments().getParcelable("TASK");
                    mTaskName.setEnabled(false);
                    mTaskDesc.setEnabled(false);
                    mTask.setText("TASK");
                    mAdd.setText("DONE TASK");
                    mTaskName.setText(task.getTaskName());
                    mTaskDesc.setText(task.getTaskDescription());
                }else {
                    setRewards();
                }
            }

        }


        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (purpose.equals("VIEW")){
                    //// TODO: Mark done task

                }else {
                    String taskName = mTaskName.getText().toString();
                    String taskDesc = mTaskDesc.getText().toString();

                    if (taskName.equals("") || taskDesc.equals("")){
                        DialogUtility.messageDialog("Please don't leave any empty field.",getContext());
                    }else {
                        if (purpose.equals("ADD")){
                            task = new Task("",taskName,taskDesc,"To Do");
                            int index = mRewards.getSelectedIndex();
                            Wish wish = rewardsList.get(index);

                            mManageTaskListener.manageTask("ADD",task,wish);

                        }else {
                            task.setTaskDescription(taskDesc);
                            task.setTaskName(taskName);
                            Wish wish = new Wish();

                            mManageTaskListener.manageTask("EDIT",task,wish);
                        }
                        dismiss();
                    }
                }


            }
        });


        return dialog;

    }

    public void setRewards(){

        String child_id = getArguments().getString("child_id");

       childWishlist.child(child_id).addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               Wish wish = dataSnapshot.getValue(Wish.class);
               rewardsList.add(wish);
               list.add(wish.getName());

               ArrayAdapter spinnerAdapter = new ArrayAdapter(getContext(),R.layout.support_simple_spinner_dropdown_item,list);
               mRewards.setAdapter(spinnerAdapter);


           }

           @Override
           public void onChildChanged(DataSnapshot dataSnapshot, String s) {

           }

           @Override
           public void onChildRemoved(DataSnapshot dataSnapshot) {

           }

           @Override
           public void onChildMoved(DataSnapshot dataSnapshot, String s) {

           }

           @Override
           public void onCancelled(FirebaseError firebaseError) {

           }
       });

    }

    @Override
    protected boolean isDimmingEnable() {
        return true;
    }


}
