package com.thesis.let.webservicedemo.screens;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.thesis.let.webservicedemo.MainActivity3;
import com.thesis.let.webservicedemo.R;
import com.thesis.let.webservicedemo.databases.DatabaseHelper;


public class PlayerNameScreen3 extends AppCompatActivity {

    EditText playerName;
    static String MESSAGE = "Player";
    DatabaseHelper myDb;
    Context context;
    TextView cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_name_screen);

        playerName = findViewById(R.id.playerName_et);

        cat = findViewById(R.id.categoryName);

        cat.setText("VideoGames Category");


    }
    public void start(View v) {
        if ((playerName.getText().toString().equals(""))) {
            Toast.makeText(this, "Input Name", Toast.LENGTH_SHORT).show();

        } else {
            Bundle bundle = new Bundle();
            String name = playerName.getText().toString();
            bundle.putString("PlayerName", name);

            Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }

    }

    public void addData(){
        //boolean isInserted =
                myDb.insertData(playerName.getText().toString(), null);
       /*if(isInserted)
            Toast.makeText(this, "Data Inserted", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Data not Inserted", Toast.LENGTH_LONG).show();

        */
    }
    public void endGame(View v){
        Intent intention = new Intent(this, Home.class);
        startActivity(intention);
    }





}
