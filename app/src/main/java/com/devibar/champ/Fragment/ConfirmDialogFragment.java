package com.devibar.champ.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.devibar.champ.Interface.OnConfirmListener;
import com.devibar.champ.R;

import fr.tvbarthel.lib.blurdialogfragment.SupportBlurDialogFragment;

/**
 * Created by namai on 9/26/2017.
 */

public class ConfirmDialogFragment extends SupportBlurDialogFragment implements View.OnClickListener {

    private OnConfirmListener mConfirmListener;

    private TextView mMessage;
    private Button mYes;
    private Button mNo;


    public static ConfirmDialogFragment newInstance(String message) {

        Bundle args = new Bundle();
        args.putString("MESSAGE",message);

        ConfirmDialogFragment fragment = new ConfirmDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            this.mConfirmListener = (OnConfirmListener) activity;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_confirm_dialog, null);
        builder.setView(view);
        final Dialog dialog = builder.create();

        mMessage = view.findViewById(R.id.txtMessage);
        mYes = view.findViewById(R.id.btnYes);
        mNo = view.findViewById(R.id.btnNo);

        String message = getArguments().getString("MESSAGE");

        mMessage.setText(message);

        mYes.setOnClickListener(this);
        mNo.setOnClickListener(this);


        return dialog;
    }

    @Override
    protected boolean isDimmingEnable() {
        return true;
    }

    @Override
    public void onClick(View view) {
        int id  = view.getId();
        if  (id == R.id.btnYes){
            mConfirmListener.response(true);
        }
        dismiss();

    }
}
