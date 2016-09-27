package com.example.lmont.iceicebb;

/**
 * Created by GabeKeyner on 9/24/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class SplashScreenActivity extends AppCompatActivity {


    //Set waktu lama SplashScreenActivity
    private static int splashInterval = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splashscreen);
        ImageView splashView = (ImageView)findViewById(R.id.splashView);

        final Animation anim = AnimationUtils.loadAnimation(SplashScreenActivity.this,R.anim.fade);
        splashView.startAnimation(anim);


        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {



                Intent i = new Intent(SplashScreenActivity.this, GameActivity.class);
                startActivity(i);



                this.finish();
            }

            private void finish() {


            }
        }, splashInterval);

    };

}