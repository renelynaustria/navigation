package com.ortega.project;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends Activity {

    private final int SPLASH_DISPLAY_LENGTH = 4000;
    Button slideButton,b1, b2,b3,b4;
    SlidingDrawer slidingDrawer;
    RelativeLayout rl;
    LinearLayout LinearLay;
    TextView slide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //slideButton = (Button) findViewById(R.id.slideButton);
        slidingDrawer = (SlidingDrawer) findViewById(R.id.slidingDrawer);
        rl = (RelativeLayout) findViewById(R.id.content);
        LinearLay = (LinearLayout) findViewById(R.id.handle);
        slide = (TextView) findViewById(R.id.slideup);
        if(slidingDrawer.isMoving()){
            slidingDrawer.animateOpen();
        }

        /*slidingDrawer.setOnDrawerScrollListener(new SlidingDrawer.OnDrawerScrollListener(){
            @Override
            public void onScrollStarted(){
                if(slidingDrawer.isMoving()){
                    slidingDrawer.setBackgroundResource(R.drawable.bg_mainmenu_1);
                }
            }

            @Override
            public void onScrollEnded() {
            }
        });*/

        slidingDrawer.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {
            @Override
            public void onDrawerOpened() {

                  slidingDrawer.setBackgroundResource(R.drawable.bg_mainmenu_1);
             //   slidingDrawer.setBackgroundResource(R.drawable.gradient_bg);

            }
        });

        slidingDrawer.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener()
        {
            @Override
            public void onDrawerClosed()
            {
                slidingDrawer.setBackgroundColor(Color.TRANSPARENT);

            }
        });
    }
}
