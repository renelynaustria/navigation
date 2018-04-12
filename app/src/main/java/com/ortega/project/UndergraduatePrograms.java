package com.ortega.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;


public class UndergraduatePrograms extends AppCompatActivity {

    ImageView btnCEA;
    ImageView btnCITE;
    ImageView btnMARE;
    ImageView btnCBE;
    ImageView btnCTE;
    ImageView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_undergraduate_programs);

        backbtn = (ImageView) findViewById(R.id.back);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnCEA = (ImageView) findViewById(R.id.btncea);
        btnCEA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cea = new Intent(UndergraduatePrograms.this, UnderGraduatePrograms_cea.class);
                startActivity(cea);
            }
        });

        btnCITE = (ImageView) findViewById(R.id.btncite);
        btnCITE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cite = new Intent(UndergraduatePrograms.this, UnderGraduatePrograms_cite.class);
                startActivity(cite);
            }
        });

        /*btnMARE = (ImageView) findViewById(R.id.btnmare);
        btnMARE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mare = new Intent(UndergraduatePrograms.this, UnderGraduatePrograms_mare.class);
                startActivity(mare);
            }
        });*/

        btnCBE = (ImageView) findViewById(R.id.btncbe);
        btnCBE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cbe = new Intent(UndergraduatePrograms.this, UnderGraduatePrograms_cbe.class);
                startActivity(cbe);
            }
        });

        btnCTE = (ImageView) findViewById(R.id.btncte);
        btnCTE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cte = new Intent(UndergraduatePrograms.this, UnderGraduatePrograms_cte.class);
                startActivity(cte);
            }
        });

        btnMARE = (ImageView) findViewById(R.id.btnmare);
        btnMARE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mare = new Intent(UndergraduatePrograms.this, UnderGraduatePrograms_mare.class);
                startActivity(mare);
            }
        });

    }
}
