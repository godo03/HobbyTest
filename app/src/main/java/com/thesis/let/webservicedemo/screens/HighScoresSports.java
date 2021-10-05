package com.thesis.let.webservicedemo.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.thesis.let.webservicedemo.R;
import com.thesis.let.webservicedemo.databases.DatabaseHelper;
import com.thesis.let.webservicedemo.databases.DatabaseHelperSports;
import com.thesis.let.webservicedemo.databases.MyAdapter;
import com.thesis.let.webservicedemo.databases.MyAdapterSports;
import com.thesis.let.webservicedemo.databases.Player;
import com.thesis.let.webservicedemo.databases.PlayerSports;

import java.util.ArrayList;

import static android.widget.AdapterView.AdapterContextMenuInfo;

public class HighScoresSports extends AppCompatActivity {

    ListView listView;
    int position;
    DatabaseHelperSports helper;
    ArrayList<PlayerSports> playerArrayList;
    MyAdapterSports myAdapter;
    TextView playerName;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores_sports);

        helper = new DatabaseHelperSports(this);
        listView = findViewById(R.id.scoreList);
        playerName = findViewById(R.id.player_list_item);

        registerForContextMenu(listView);

        playerArrayList = helper.getAllData();
        myAdapter = new MyAdapterSports(this, playerArrayList);
        listView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();




    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
        position = info.position;


        getMenuInflater().inflate(R.menu.example_menu, menu);




    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();

        position = info.position;


        switch (item.getItemId()) {
            case R.id.option1:

               //mdataBase.delete("players", "name =" +name, null);
              delete();
                Toast.makeText(this, "Wow gumana", Toast.LENGTH_SHORT).show();

                //myAdapter.notifyDataSetChanged();
                return true;
            case R.id.option2:
                Toast.makeText(this,"Edi Sige", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);

    }


}
    public void delete(){
        PlayerSports pos = playerArrayList.get(position);
        playerArrayList.remove(position);
        boolean deleted = helper.deleteData(pos);
            if(deleted) {
                Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
            myAdapter.notifyDataSetChanged();
        }
    public void endGame(View v){
        Intent intention = new Intent(this, Home.class);
        startActivity(intention);
    }








}




