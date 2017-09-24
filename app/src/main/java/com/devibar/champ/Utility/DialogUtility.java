package com.devibar.champ.Utility;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by namai on 9/24/2017.
 */

public class DialogUtility {

    public static void messageDialog(String message, Context context){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public static void progressDialogShow(ProgressDialog progress){
        progress.setMessage("Loading");
        progress.setIndeterminate(true);
        progress.setCanceledOnTouchOutside(false);
        progress.show();

    }

    public static void progressDialogDismiss(ProgressDialog progress){
        progress.dismiss();
    }
}
