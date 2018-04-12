package com.ortega.project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ConnectWithUs extends AppCompatActivity {

    TextView tipfb;
    TextView tiptw;
    ImageView backbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_with_us);

        backbtn = (ImageView) findViewById(R.id.back);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tipfb = (TextView) findViewById(R.id.fbpage);

        tipfb.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/TIP1962official"));
                startActivity(browserIntent);
            }
        });

        tiptw = (TextView) findViewById(R.id.twpage);

        tiptw.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/TIP1962official"));
                startActivity(browserIntent);
            }
        });

    }
}
