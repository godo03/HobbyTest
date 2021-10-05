package com.thesis.let.webservicedemo.databases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.thesis.let.webservicedemo.R;

import java.util.ArrayList;


public class MyAdapterBooks extends BaseAdapter {

    Context context;
    ArrayList <PlayerBooks> playerArrayList;

    public MyAdapterBooks(Context context, ArrayList<PlayerBooks> playerArrayList){
        this.context = context;
        this.playerArrayList = playerArrayList;
    }

    /* public MyAdapter(Map<String, String> map) {
         arrayList = new ArrayList();
         arrayList.addAll(map.entrySet());


     }*/
    @Override
    public int getCount() {
        //return arrayList.size();

        return playerArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return playerArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }


  /*  @Override
    public Map.Entry<String, String> getItem(int i) {
        return (Map.Entry) arrayList.get(i);
    } */

    /*  @Override
    public long getItemId(int i) {
        return i;
    }

     */

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final View result;


        result = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_books, viewGroup, false);


        //  Map.Entry<String, String> item = getItem(i);

        // ((TextView) result.findViewById(R.id.player_list_item)).setText(item.getKey());
        //  ((TextView) result.findViewById(R.id.score_list_item)).setText(item.getValue());


       TextView idtv = result.findViewById(R.id.id_item_list);
        TextView playerName = result.findViewById(R.id.player_list_item);
        TextView score = result.findViewById(R.id.score_list_item);


        PlayerBooks player = playerArrayList.get(position);

        //idtv.setText(player.getId());
        playerName.setText(player.getName());
        score.setText(player.getScore());


        return result;
    }



}
