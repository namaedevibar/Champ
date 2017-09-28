package com.devibar.champ.Fragments;


import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

/**

 */
public class AddChild extends DialogFragment {


    EditText id;
    Button add;
    private FirebaseAuth mAuth;
    Firebase childDB;
    String passId;
    ArrayList<Child>children;
    Firebase guardianChild;
    int index = 0;

    public AddChild() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_child2, container, false);
        id = (EditText)view.findViewById(R.id.id);
        add = (Button)view.findViewById(R.id.add);
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


                childDB.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Log.e("sdsd",dataSnapshot.toString());
                        Child child = dataSnapshot.child("info").getValue(Child.class);
                        Log.e("childdasd",child.getFirstName());


                        if(id.getText().toString().equals(child.getFirstName() + " "+child.getLastName())){

                            Log.e("asds",child.getFirstName());
                            children.add(child);
                            index++;
                            if(dataSnapshot.getChildrenCount() == index++){

                                Intent intent = new Intent(getActivity(), GuardianHomeActivity.class);

                                intent.putExtra("list",children);
                                intent.putExtra("id",passId);
                                intent.putExtra("type","search");

                                startActivity(intent);

                            }

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

        return view;
    }

}