package com.smyhvae.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;

import com.smyhvae.myapplication.BottomActivity;


public class DialogUtil {
    public static void showDialog(final Context ctx, String msg, Boolean goHome){
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx).setMessage(msg).setCancelable(false);
        if(goHome){
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    Intent intent = new Intent(ctx, BottomActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    ctx.startActivity(intent);
                }
            });
        }else{
            builder.setPositiveButton("确定", null);
        }
        builder.create().show();
    }

    public static void showDialog(Context ctx, View view){
        new AlertDialog.Builder(ctx).setView(view).setPositiveButton("确定", null).create().show();
    }
}
