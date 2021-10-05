package com.thesis.let.webservicedemo.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.thesis.let.webservicedemo.R;
import com.thesis.let.webservicedemo.classes.ReviewDatabaseHelper;

public class Home extends AppCompatActivity {

    ReviewDatabaseHelper helper;
    Button play, instructions, about, highscores;
    ImageView image;
    TextView tvFilmTest;
    Animation frombottom, fromtop;
   // private ListView listView;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        helper = new ReviewDatabaseHelper(this);

        play = findViewById(R.id.play);
        instructions = findViewById(R.id.instructions);
        about = findViewById(R.id.about);
        //image = findViewById(R.id.image);
        tvFilmTest = findViewById(R.id.tvFilmTest);
        highscores = findViewById(R.id.highscores);

        frombottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);
        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);



        play.setAnimation(frombottom);
        instructions.setAnimation(frombottom);
        about.setAnimation(frombottom);
        highscores.setAnimation(frombottom);

        tvFilmTest.setAnimation(fromtop);
       // image.setAnimation(fromtop);
    }



    public void clickPlay (View view) {

        Intent intention = new Intent(this, SelectCategory.class);
        startActivity(intention);

    }
    public void clickHow2Play (View view) {
        Intent intention = new Intent(this, How2Play.class);
        startActivity(intention);
    }
    public void clickAbout (View view) {
        Intent intention = new Intent(this, About.class);
        startActivity(intention);
    }
    public void clickHighScores (View view) {
        Intent intention = new Intent(this, CategoryHS.class);
        startActivity(intention);
    }

}
