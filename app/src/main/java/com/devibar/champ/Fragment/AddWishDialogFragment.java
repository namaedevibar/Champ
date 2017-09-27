package com.devibar.champ.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.devibar.champ.R;

import fr.tvbarthel.lib.blurdialogfragment.SupportBlurDialogFragment;

/**
 * Created by namai on 9/28/2017.
 */

public class AddWishDialogFragment extends SupportBlurDialogFragment {

    private EditText mWish;
    private Button mAdd;

    public static AddWishDialogFragment newInstance() {

        Bundle args = new Bundle();

        AddWishDialogFragment fragment = new AddWishDialogFragment();
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

        mWish = view.findViewById(R.id.etWish);
        mAdd = view.findViewById(R.id.btnAddWish);

        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Add Wish
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
