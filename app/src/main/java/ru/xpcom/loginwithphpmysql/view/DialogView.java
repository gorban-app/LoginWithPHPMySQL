package ru.xpcom.loginwithphpmysql.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class DialogView {

    public static void showDialogError(Context context, int message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setMessage(message);
        builder.setPositiveButton("Closed", (dialog, which) -> dialog.dismiss());
        builder.create().show();
    }

}



