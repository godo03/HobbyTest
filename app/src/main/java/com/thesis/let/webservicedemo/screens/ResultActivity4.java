package com.thesis.let.webservicedemo.screens;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.thesis.let.webservicedemo.R;
import com.thesis.let.webservicedemo.databases.DatabaseHelper;
import com.thesis.let.webservicedemo.databases.MyAdapter;


public class ResultActivity4 extends Activity {
    String token, playerName, category;
    int  score;
    TextView res, tv_playerName, cat;
    DatabaseHelper myDb;
    MyAdapter myAdapter;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);



		res = findViewById(R.id.textRes);

		tv_playerName = findViewById(R.id.playerName_tv_list);
		cat = findViewById(R.id.cat);

		//get score
		//Bundle b = getIntent().getExtras();

		//score= b.getInt("Score");

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        playerName = bundle.getString("PlayerName");
        score = bundle.getInt("Score");
        category = bundle.getString("Category");




        res.setText("You got "+score+" points");
		tv_playerName.setText(playerName);
        cat.setText("in the "+category+" category!" );

	}

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }
    public void reviewClick(View v){
        Intent intention = new Intent(this, Review.class);
        intention.putExtra("PlayerName", playerName);
        intention.putExtra("Score", score);
        intention.putExtra("Category", category);

        startActivity(intention);
    }
    public void playAgain(View v){
        Intent intention = new Intent(this, PlayerNameScreen4.class);
        startActivity(intention);
    }
    public void endGame(View v){
        onStop();
        Intent intention = new Intent(this, Home.class);
        startActivity(intention);
    }




}
