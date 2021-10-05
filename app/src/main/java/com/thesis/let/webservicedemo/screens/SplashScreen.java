package com.thesis.let.webservicedemo.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.thesis.let.webservicedemo.R;
import com.thesis.let.webservicedemo.screens.Home;

public class SplashScreen extends AppCompatActivity {
    private ImageView iv;
    Animation frombottom, fromtop;
    TextView appName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        appName = findViewById(R.id.appName);
        iv = findViewById(R.id.iv);
        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mytransition);
        iv.startAnimation(myanim);
         final Intent i = new Intent(this, Home.class);
        Thread timer = new Thread (){
            public void run(){
                try{
                    sleep(3000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                }
            }
        };timer.start();

        frombottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);
        appName.setAnimation(frombottom);


    }
}
