package com.ortega.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class TipLife extends AppCompatActivity {

    ImageView btnAdmission;
    ImageView btnScholarships;
    ImageView btnCareerCenter;
    ImageView btnAccreditation;
    ImageView btnFacilities;
    ImageView btnFaq;

    ImageView backbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_life);

        backbtn = (ImageView) findViewById(R.id.back);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnAdmission = (ImageView) findViewById(R.id.btnadmission);
        btnAdmission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent admission = new Intent(TipLife.this, TipLife_Admission.class);
                startActivity(admission);
            }
        });

        btnScholarships = (ImageView) findViewById(R.id.btnscholarship);
        btnScholarships.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scholarships = new Intent(TipLife.this, TipLife_Scholarships.class);
                startActivity(scholarships);
            }
        });

        btnCareerCenter = (ImageView) findViewById(R.id.btncareercenter);
        btnCareerCenter.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent careercenter = new Intent(TipLife.this, TipLife_CareerCenter.class);
                startActivity(careercenter);
            }
        });

        btnAccreditation = (ImageView) findViewById(R.id.btnaccreditation);
        btnAccreditation.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent accreditation = new Intent(TipLife.this, TipLife_Accreditation.class);
                startActivity(accreditation);
            }
        });

        btnFacilities = (ImageView) findViewById(R.id.btndirectories);
        btnFacilities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent facilities = new Intent(TipLife.this, TipLife_Directories.class);
                startActivity(facilities);
            }
        });

        btnFaq = (ImageView) findViewById(R.id.btnfaq);
        btnFaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent facilities = new Intent(TipLife.this, TipLife_faq.class);
                startActivity(facilities);
            }
        });
    }
}
