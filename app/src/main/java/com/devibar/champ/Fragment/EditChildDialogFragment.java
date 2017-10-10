package com.devibar.champ.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;


import com.devibar.champ.Model.Child;
import com.devibar.champ.R;

import fr.tvbarthel.lib.blurdialogfragment.SupportBlurDialogFragment;

/**
 * Created by namai on 9/27/2017.
 */

public class EditChildDialogFragment extends SupportBlurDialogFragment implements View.OnClickListener {



    private Button mEdit;


    public static EditChildDialogFragment newInstance() {

        Bundle args = new Bundle();

        EditChildDialogFragment fragment = new EditChildDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_edit_child_dialog, null);
        builder.setView(view);
        final Dialog dialog = builder.create();

        mEdit = view.findViewById(R.id.btnEditChild);

        mEdit.setOnClickListener(this);


        return dialog;
    }

    @Override
    protected boolean isDimmingEnable() {
        return true;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnEditChild){
            // TODO: edit child
        }

        dismiss();
    }
}
