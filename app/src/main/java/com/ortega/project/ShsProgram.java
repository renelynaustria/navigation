package com.ortega.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class ShsProgram extends AppCompatActivity {

    ImageView btnAbm;
    ImageView btnHumms;
    ImageView btnStem;
    ImageView btnGeneralAcademic;
    ImageView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shs_program);

        backbtn = (ImageView) findViewById(R.id.back);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnAbm = (ImageView) findViewById(R.id.btnabm);
        btnAbm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abm = new Intent(ShsProgram.this, ShsProgram_Abm.class);
                startActivity(abm);
            }
        });

        btnHumms = (ImageView) findViewById(R.id.btnhumms);
        btnHumms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent humms = new Intent(ShsProgram.this, ShsProgram_Humms.class);
                startActivity(humms);
            }
        });

        btnStem = (ImageView) findViewById(R.id.btnstem);
        btnStem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stem = new Intent(ShsProgram.this, ShsProgram_Stem.class);
                startActivity(stem);
            }
        });

        btnGeneralAcademic = (ImageView) findViewById(R.id.btngeneralacademic);
        btnGeneralAcademic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent generalacademic = new Intent(ShsProgram.this, ShsProgram_GeneralAcademic.class);
                startActivity(generalacademic);
            }
        });
    }
}
