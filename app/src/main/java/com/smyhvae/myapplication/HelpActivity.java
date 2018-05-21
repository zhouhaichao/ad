package com.smyhvae.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class HelpActivity extends Activity implements View.OnClickListener{

    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_help);

        back = findViewById(R.id.back);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
               /* Intent myIntent = new Intent(HelpActivity.this, MainActivity.class);
                myIntent.putExtra("i", "3");
                startActivity(myIntent);*/
                this.finish();
                break;
            default:
                break;
        }
    }
}