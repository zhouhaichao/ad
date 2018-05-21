package com.smyhvae.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ContentActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);
        button = (Button) findViewById(R.id.back1);
        textView = (TextView) findViewById(R.id.text11);
        Intent i = getIntent();
        String a = i.getStringExtra("msgA");
        textView.setText(a);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data=new Intent();
                data.putExtra("msgB","哇咔咔");
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}
