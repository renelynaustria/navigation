package com.ortega.project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.ViewFlipper;




public class Menu extends AppCompatActivity {

    SlidingDrawer slidingDrawer;
    RelativeLayout rl;
    LinearLayout LinearLay;
    TextView slide;
    TextView slideUp;
    TextView slideUp1;

    ImageView btnAbout;
    ImageView btnShs;
    ImageView btnUG;
    ImageView btnGS;
    ImageView btnLife;
    ImageView btnDirectory;
    ImageView helpbtn;

    Animation fade_in, fade_out;
    ViewFlipper viewFlipper;

    int alpha = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


            slidingDrawer = (SlidingDrawer) findViewById(R.id.slidingDrawer);
            rl = (RelativeLayout) findViewById(R.id.content);
            LinearLay = (LinearLayout) findViewById(R.id.handle);
            slide = (TextView) findViewById(R.id.slideup);
          slideUp = (TextView) findViewById(R.id.slide);
        helpbtn = (ImageView) findViewById(R.id.imageButton);
        //  slideUp1 = (TextView) findViewById(R.id.slid1);
            if(slidingDrawer.isMoving()){
                slidingDrawer.animateOpen();
            }

        viewFlipper = (ViewFlipper) this.findViewById(R.id.bckgrndViewFlipper1);

        fade_in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
    //    fade_out = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);

        viewFlipper.setInAnimation(fade_in);
    //    viewFlipper.setInAnimation(fade_out);

        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.startFlipping();


            slidingDrawer.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {
                @Override
                public void onDrawerOpened() {

                    slidingDrawer.setBackgroundResource(R.drawable.bg_mainmenu);
                    //   slidingDrawer.setBackgroundResource(R.drawable.gradient_bg);
                    slideUp.setTextColor(Color.argb(alpha,255,0,0));
                //    slideUp1.setTextColor(Color.argb(alpha,255,0,0));

                }
            });

            slidingDrawer.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener()
            {
                @Override
                public void onDrawerClosed()
                {
                    slidingDrawer.setBackgroundColor(Color.TRANSPARENT);
                    slideUp.setTextColor(Color.rgb(255,255,255));
                //    slideUp1.setTextColor(Color.rgb(255,255,255));

                }
            });

        btnAbout = (ImageView) findViewById(R.id.button1);

        btnAbout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent about = new Intent(Menu.this, AboutTip.class);
                startActivity(about);
            }
        });

        btnShs = (ImageView) findViewById(R.id.button2);

        btnShs.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent shs = new Intent(Menu.this, ShsProgram.class);
                startActivity(shs);
            }
        });

        btnUG = (ImageView) findViewById(R.id.button3);

        btnUG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent undergrad = new Intent(Menu.this, UndergraduatePrograms.class);
                startActivity(undergrad);
            }
        });

        btnGS = (ImageView) findViewById(R.id.button4);

        btnGS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gradstudies = new Intent(Menu.this, GraduateStudies.class);
                startActivity(gradstudies);
            }
        });

        btnLife = (ImageView) findViewById(R.id.button5);

        btnLife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tiplife = new Intent(Menu.this, TipLife.class);
                startActivity(tiplife);
            }
        });

        btnDirectory = (ImageView) findViewById(R.id.button7);

        btnDirectory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent connectwithus =  new Intent(Menu.this, TipDirectory.class);
                startActivity(connectwithus);
            }
        });

        helpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent help = new Intent(Menu.this, HelpPage.class);
                startActivity(help);
            }
        });
    }

    public void onBackPressed(){
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing Activity")
                .setMessage("Are you sure you want to close this activity?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
            @Override
                    public void onClick(DialogInterface dialog, int which){
                finish();
            }
        })
        .setNegativeButton("No", null)
                .show();
    }
}
