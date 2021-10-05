package com.thesis.let.webservicedemo.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.thesis.let.webservicedemo.R;

public class How2Play extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how2_play);
    }
    public void endGame(View v){
        Intent intention = new Intent(this, Home.class);
        startActivity(intention);
    }

}
