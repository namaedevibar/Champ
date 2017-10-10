package com.devibar.champ.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.devibar.champ.Model.Wish;
import com.devibar.champ.R;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

import fr.tvbarthel.lib.blurdialogfragment.SupportBlurDialogFragment;

/**
 * Created by namai on 9/28/2017.
 */

public class AddWishDialogFragment extends SupportBlurDialogFragment {

    private EditText mWish;
    private Button mAdd;
    Firebase childWishlistDB;
    String id;
    ArrayList<Wish>wishList;

    public static AddWishDialogFragment newInstance(String id) {

        Bundle args = new Bundle();

        AddWishDialogFragment fragment = new AddWishDialogFragment();
        args.putString("id",id);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_add_wish_dialog, null);
        builder.setView(view);
        final Dialog dialog = builder.create();
        id = getArguments().getString("id");
        wishList = new ArrayList<>();
        mWish = view.findViewById(R.id.etWish);
        mAdd = view.findViewById(R.id.btnAddWish);
        childWishlistDB = new Firebase("https://finalsattendanceapp.firebaseio.com/CHILD_REWARD");

        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("line63",id);

                String wishId = childWishlistDB.push().getKey();
                String name = mWish.getText().toString();
                Wish wish = new Wish(name,wishId,"wala pa");
                childWishlistDB.child(id).child(wishId).setValue(wish);

                dismiss();
            }
        });

        return dialog;

    }


    @Override
    protected boolean isDimmingEnable() {
        return true;
    }

}
