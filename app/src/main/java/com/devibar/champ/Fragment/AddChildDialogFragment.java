package com.devibar.champ.Fragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.text.GetChars;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.devibar.champ.Activity.GuardianHomeActivity;
import com.devibar.champ.Model.Child;
import com.devibar.champ.R;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import fr.tvbarthel.lib.blurdialogfragment.SupportBlurDialogFragment;

/**

 */
public class AddChildDialogFragment extends SupportBlurDialogFragment {


    EditText name;
    Button add;
    private FirebaseAuth mAuth;
    Firebase childDB;
    String passId;
    ArrayList<Child>children;
    Firebase guardianChild;
    int index = 0;
    int counter = 0;

    public AddChildDialogFragment() {
        // Required empty public constructor
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_add_child_dialog, null);
        builder.setView(view);
        final Dialog dialog = builder.create();

        // Inflate the layout for this fragment

        name = view.findViewById(R.id.etSearch);
        add = view.findViewById(R.id.btnSearchChild);
        Firebase.setAndroidContext(getActivity());

        passId = getArguments().getString("id");
        //childlist = new ArrayList<>();
        childDB = new Firebase("https://finalsattendanceapp.firebaseio.com/CHILD");
        guardianChild = new Firebase("https://finalsattendanceapp.firebaseio.com/GUARDIANCHILDREN");
        children = new ArrayList<>();


        mAuth = FirebaseAuth.getInstance();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //// TODO: di sha masearch


                childDB.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Log.e("sdsd",dataSnapshot.toString());
                        Child child = dataSnapshot.getValue(Child.class);
                        Log.e("wew",dataSnapshot.getChildrenCount()+"");
                        //Log.e("childdasd",child.getFirstName());
                        counter = counter + (int)dataSnapshot.getChildrenCount();
                        index++;

                        if(name.getText().toString().equals(child.getFirstName() + " "+child.getLastName())){

                            Log.e("asds",child.getFirstName());
                            children.add(child);

                            Intent intent = new Intent(getActivity(), GuardianHomeActivity.class);

                            intent.putExtra("list",children);
                            intent.putExtra("id",passId);
                            intent.putExtra("type","search");


                            startActivity(intent);


//                            if(counter/6 == index){
//
//                                Intent intent = new Intent(getActivity(), GuardianHomeActivity.class);
//
//                                intent.putExtra("list",children);
//                                intent.putExtra("id",passId);
//                                intent.putExtra("type","search");
//
//
//                                startActivity(intent);
//
//                            }else {
//                                Toast.makeText(getContext(), "No child found.", Toast.LENGTH_SHORT).show();
//                            }

                         //   childDB.child(dataSnapshot.getKey()).child("info").child("guardian_id").setValue(passId);
                           // guardianChild.child(passId).child(dataSnapshot.getKey()).setValue(child);

                         /*   childDB.child(dataSnapshot.getKey()).addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                                    Log.e("79child",dataSnapshot.toString());
                                    Child child = dataSnapshot.getValue(Child.class);

                                    Toast.makeText(getActivity(), "Added "+child.getFirstName()+" as child", Toast.LENGTH_SHORT).show();

                                    dismiss();
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
                            });*/



                        }

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
        });

        return dialog;
    }

    @Override
    protected boolean isDimmingEnable() {
        return true;
    }

}
