package com.thesis.let.webservicedemo.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.thesis.let.webservicedemo.R;
import com.thesis.let.webservicedemo.classes.ReviewAdapter;
import com.thesis.let.webservicedemo.classes.ReviewDatabaseHelper;

import java.util.HashMap;

public class Review extends AppCompatActivity {
    ListView listView;
    String playerName, category;
    int score;
    TextView res, tv_playerName, cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        ReviewDatabaseHelper helper = new ReviewDatabaseHelper(this);

        listView = findViewById(R.id.listView);
        res = findViewById(R.id.score_tv);
        tv_playerName = findViewById(R.id.playerName_tv);
        cat = findViewById(R.id.cat);


        Bundle b = getIntent().getExtras();

        score= b.getInt("Score");

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();


        playerName = bundle.getString("PlayerName");
        category = bundle.getString("Category");



        res.setText("Score : "+score);
        tv_playerName.setText(playerName);
        cat.setText(category);




        HashMap<String, String> data = helper.getAllData();
        ReviewAdapter adapter = new ReviewAdapter(data);
        listView.setAdapter(adapter);
    }

}
