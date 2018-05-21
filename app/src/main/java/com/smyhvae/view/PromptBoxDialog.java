package com.smyhvae.view;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.smyhvae.myapplication.R;

import org.apache.http.util.TextUtils;

public class PromptBoxDialog extends Dialog implements OnClickListener{

    private TextView messageTxt;
    private EditText contentTxt;
    private TextView titleTxt;
    private TextView submitTxt;
    private TextView cancelTxt;

    private Context mContext;
    private String content;
    private OnCloseListener listener;
    private String positiveName;
    private String negativeName;
    private String title;
    private String message;

    public PromptBoxDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
    }

    public PromptBoxDialog(Context context, int themeResId, String content, OnCloseListener listener) {
        super(context, themeResId);
        this.mContext = context;
        this.message = content;
        this.listener = listener;
    }

    public PromptBoxDialog setTitle(String title){
        this.title = title;
        return this;
    }

    public PromptBoxDialog setPositiveButton(String name, OnCloseListener listener){
        this.positiveName = name;
        this.listener = listener;
        return this;
    }

    public PromptBoxDialog setNegativeButton(String name, OnCloseListener listener){
        this.negativeName = name;
        this.listener = listener;
        return this;
    }

    public PromptBoxDialog setMessage(String name){
        this.message = name;
        return this;
    }
    public PromptBoxDialog setContext(String content){
        this.content = content;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prompt_box_layout);
        setCanceledOnTouchOutside(false);
        initView();
    }

    private void initView(){
        messageTxt = findViewById(R.id.message);
        contentTxt = (EditText) findViewById(R.id.content);
        contentTxt.setRawInputType(Configuration.KEYBOARD_QWERTY);
        titleTxt = (TextView)findViewById(R.id.title);
        submitTxt = (TextView)findViewById(R.id.submit);
        submitTxt.setOnClickListener(this);
        cancelTxt = (TextView)findViewById(R.id.cancel);
        cancelTxt.setOnClickListener(this);

//        messageTxt.setText(content);
        if(!TextUtils.isEmpty(positiveName)){
            submitTxt.setText(positiveName);
        }

        if(!TextUtils.isEmpty(content))
        {
            contentTxt.setText(content);
            contentTxt.setSelection(contentTxt.getText().length());
        }

        if(!TextUtils.isEmpty(negativeName)){
            cancelTxt.setText(negativeName);
        }

        if(!TextUtils.isEmpty(title)){
            titleTxt.setText(title);
        }

        if (!android.text.TextUtils.isEmpty(message)){
            messageTxt.setText(message);
        }

    }


    //上弹软键盘
    public void showKeyboard() {
        if(contentTxt!=null){
            //设置可获得焦点
            contentTxt.setFocusable(true);
            contentTxt.setFocusableInTouchMode(true);
            //请求获得焦点
            contentTxt.requestFocus();
            //调用系统输入法
            InputMethodManager inputManager = (InputMethodManager) contentTxt
                    .getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(contentTxt, 0);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cancel:
                if(listener != null){
                    listener.onClick(this, false, null);
                }
                this.dismiss();
                break;
            case R.id.submit:
                if(listener != null){
                    listener.onClick(this, true, contentTxt.getText().toString());
                }
                break;
        }
    }

    public interface OnCloseListener{
        void onClick(Dialog dialog, boolean confirm, String content);
    }
}
