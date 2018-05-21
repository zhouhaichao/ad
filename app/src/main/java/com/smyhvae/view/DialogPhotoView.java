package com.smyhvae.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.bm.library.PhotoView;
import com.smyhvae.myapplication.R;

/**
 * Created by Administrator on 2018/4/19.
 */

public class DialogPhotoView extends Dialog {

    private PhotoView PV_picasso;
    private String urlStr;
    private Bitmap bitmap;

    private Context context;
    public DialogPhotoView(@NonNull Context context, Bitmap bitmap) {
        super(context,R.style.Dialog_Fullscreen);
        this.context = context;
        this.bitmap =bitmap;
    }

    public DialogPhotoView(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    protected DialogPhotoView(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context = context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_photoview);

        PV_picasso = findViewById(R.id.PV_dialog);
        PV_picasso.enable();
        PV_picasso.setImageBitmap(bitmap);
        PV_picasso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                close();
            }
        });
    }


    @Override
    public void show() {
        super.show();

    }

    private void close()
    {
        this.dismiss();
    }
}
