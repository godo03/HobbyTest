package com.thesis.let.webservicedemo.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.thesis.let.webservicedemo.R;
import com.thesis.let.webservicedemo.screens.Home;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
    public void endGame(View v){
        Intent intention = new Intent(this, Home.class);
        startActivity(intention);
    }
}
