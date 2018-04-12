package com.ortega.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class GraduateStudies extends AppCompatActivity {

    ImageView btnDit;
    ImageView btnDoe;
    ImageView btnMe;
    ImageView btnMis;
    ImageView btnMit;
    ImageView btnMscs;
    ImageView btnPsm;
    ImageView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graduate_studies);

        backbtn = (ImageView) findViewById(R.id.back);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnDit = (ImageView) findViewById(R.id.btndit);
        btnDit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent dit = new Intent(GraduateStudies.this, GraduateStudies_dit.class);
                startActivity(dit);
            }
        });

        btnDoe = (ImageView) findViewById(R.id.btndoe);
        btnDoe.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent doe = new Intent(GraduateStudies.this, GraduateStudies_doe.class);
                startActivity(doe);
            }
        });

        btnMe = (ImageView) findViewById(R.id.btnme);
        btnMe.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent me = new Intent(GraduateStudies.this, GraduateStudies_me.class);
                startActivity(me);
            }
        });

        btnMis = (ImageView) findViewById(R.id.btnmis);
        btnMis.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent mis = new Intent(GraduateStudies.this, GraduateStudies_mis.class);
                startActivity(mis);
            }
        });

        btnMit = (ImageView) findViewById(R.id.btnmit);
        btnMit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent mit = new Intent(GraduateStudies.this, GraduateStudies_mit.class);
                startActivity(mit);
            }
        });


        btnMscs = (ImageView) findViewById(R.id.btnmscs);
        btnMscs.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent mscs = new Intent(GraduateStudies.this, GraduateStudies_mscs.class);
                startActivity(mscs);
            }
        });

        btnPsm = (ImageView) findViewById(R.id.btnpsm);
        btnPsm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent psm = new Intent(GraduateStudies.this, GraduateStudies_psm.class);
                startActivity(psm);
            }
        });
    }
}
