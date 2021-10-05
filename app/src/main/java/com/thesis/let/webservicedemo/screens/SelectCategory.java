package com.thesis.let.webservicedemo.screens;

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

import androidx.appcompat.app.AppCompatActivity;

import com.thesis.let.webservicedemo.MainActivity;
import com.thesis.let.webservicedemo.MainActivity2;
import com.thesis.let.webservicedemo.MainActivity3;
import com.thesis.let.webservicedemo.MainActivity4;
import com.thesis.let.webservicedemo.MainActivity5;
import com.thesis.let.webservicedemo.R;
import com.thesis.let.webservicedemo.classes.ReviewDatabaseHelper;

public class SelectCategory extends AppCompatActivity {

    ReviewDatabaseHelper helper;
    Button film, books, videogames, sports, anime ;
    ImageView image;
    Animation frombottom, fromtop;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category);

        helper = new ReviewDatabaseHelper(this);

        film = findViewById(R.id.film);
        books = findViewById(R.id.books);
        videogames = findViewById(R.id.videogames);
        sports = findViewById(R.id.sports);
        anime = findViewById(R.id.anime);
        image = findViewById(R.id.image);


        frombottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);
        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);




        film.setAnimation(frombottom);
        anime.setAnimation(frombottom);
        sports.setAnimation(frombottom);

        videogames.setAnimation(frombottom);
        books.setAnimation(frombottom);
        image.setAnimation(frombottom);
    }



    public void mainActivity(View view) {
        Intent intention = new Intent(this, PlayerNameScreen.class);
        startActivity(intention);
    }
    public void mainActivity2 (View view) {
        Intent intention = new Intent(this, PlayerNameScreen2.class);
        startActivity(intention);
    }
    public void mainActivity3 (View view) {
        Intent intention = new Intent(this, PlayerNameScreen3.class);
        startActivity(intention);
    }
    public void mainActivity4 (View view) {
        Intent intention = new Intent(this, PlayerNameScreen4.class);
        startActivity(intention);
    }
    public void mainActivity5 (View view) {
        Intent intention = new Intent(this, PlayerNameScreen5.class);
        startActivity(intention);
    }

    public void endGame(View v){
        Intent intention = new Intent(this, Home.class);
        startActivity(intention);
    }

}
