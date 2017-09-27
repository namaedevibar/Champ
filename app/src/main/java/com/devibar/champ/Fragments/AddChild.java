package com.devibar.champ.Fragments;


import android.app.DialogFragment;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.devibar.champ.Model.Child;
import com.devibar.champ.R;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;

/**

 */
public class AddChild extends DialogFragment {


    EditText id;
    Button add;
    private FirebaseAuth mAuth;
    Firebase childDB;
    String passId;

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

        childDB = new Firebase("https://finalsattendanceapp.firebaseio.com/CHILD");

        mAuth = FirebaseAuth.getInstance();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                childDB.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        if(id.getText().toString().equals(dataSnapshot.getKey())){


                            Log.e("asds",passId);
                            childDB.child(dataSnapshot.getKey()).child("info").child("guardian_id").setValue(passId);

                            childDB.child(dataSnapshot.getKey()).addChildEventListener(new ChildEventListener() {
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
                            });



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
