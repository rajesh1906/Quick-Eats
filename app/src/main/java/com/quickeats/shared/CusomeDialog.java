package com.quickeats.shared;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.quickeats.R;
import com.quickeats.activities.signin.SignInActivity;

/**
 * Created by Rajesh kumar on 08-09-2018.
 */

public class CusomeDialog {




    public void createDialog(Context context){
        final Dialog dialog = new Dialog(context);

        //tell the Dialog to use the dialog.xml as it's layout description
        dialog.setContentView(R.layout.dialog);
        dialog.setTitle("Android Custom Dialog Box");

        TextView txt = (TextView) dialog.findViewById(R.id.txt);

        txt.setText(context.getResources().getString(R.string.signoutcontext));

        Button btnCancel = (Button) dialog.findViewById(R.id.btncancel);
        Button btnOk = (Button) dialog.findViewById(R.id.btnok);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                ((Activity)context).finish();
                ((Activity)context).startActivity(new Intent(context, SignInActivity.class));
            }
        });

        dialog.show();
    }
}
