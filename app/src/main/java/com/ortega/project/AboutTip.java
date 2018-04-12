package com.ortega.project;

import android.content.Intent;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class AboutTip extends AppCompatActivity {

    ImageView btnHistory;
    ImageView btnAboutTheLogo;
    ImageView btnVisionMission;
    ImageView btnAcademicPolicies;
    ImageView btnAdministration;
    ImageView btnAboutThePres;
    ImageView btnTipHymn;
    ImageView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_tip);

        backbtn = (ImageView) findViewById(R.id.back);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnHistory = (ImageView) findViewById(R.id.btnhistory);
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent history = new Intent(AboutTip.this, AboutTip_History.class);
                startActivity(history);
            }
        });

        btnAboutTheLogo = (ImageView) findViewById(R.id.btnaboutthelogo);
        btnAboutTheLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aboutthelogo = new Intent(AboutTip.this, AboutTip_AboutTheLogo.class);
                startActivity(aboutthelogo);
            }
        });

        btnVisionMission = (ImageView) findViewById(R.id.btnvisionmission);
        btnVisionMission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent visionmission = new Intent(AboutTip.this, AboutTip_VisionMission.class);
                startActivity(visionmission);
            }
        });

       btnAcademicPolicies = (ImageView) findViewById(R.id.btnacademicpolicies);
        btnAcademicPolicies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent academicpol = new Intent(AboutTip.this, AcademicPolicies.class);
                startActivity(academicpol);
            }
        });

        btnAboutThePres = (ImageView) findViewById(R.id.btnaboutthepres);
        btnAboutThePres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pres = new Intent(AboutTip.this, AboutTip_AboutThePresident.class);
                startActivity(pres);
            }
        });

        btnTipHymn = (ImageView) findViewById(R.id.btnhymfightsong);
        btnTipHymn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hymn = new Intent(AboutTip.this, AboutTip_TipHymn.class);
                startActivity(hymn);
            }
        });

        btnAdministration = (ImageView) findViewById(R.id.btnadministration);
        btnAdministration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent admin = new Intent(AboutTip.this, AboutTip_Administration.class);
                startActivity(admin);
            }
        });
    }
}
