package com.thesis.let.webservicedemo.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static int DB_VERSION = 1;
    public static String DB_NAME = "Player.db";

    public static String TABLE_NAME = "players";
    public static String COL_ID = "id";
    public static String COL_NAME = "name";
    public static String COL_SCORE = "score";



    private static String query = "CREATE TABLE "+ TABLE_NAME +" (" + COL_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_NAME + " TEXT NOT NULL," + COL_SCORE +
            " TEXT NOT NULL);";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public boolean deleteData (Player id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("players", "id = " +id.getId(), null);
        db.close();

        return true;

    }

    public void insertData(String name, String score){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        ArrayList<Player> playerArrayList = new ArrayList<>();



        contentValues.put(COL_NAME, name);
        contentValues.put(COL_SCORE, score);


        /*for(int i = 0; con1tentValues.size() > i; ++i){
            contentValues.put(COL_ID, i);

        }
         */
            long result = db.insert(TABLE_NAME, "ORDER BY score DESC", contentValues);




    }
   /* public Cursor getAllData(){
        SQLiteDatabase query = this.getWritableDatabase();
        Cursor res = query.rawQuery("SELECT * FROM players", null);
        return res;
    }*/

  /*  public HashMap<String, String> getAllPlayers() {
        HashMap<String, String> hashMap = new HashMap<>();

        /// hp = new HashMap();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor res = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            int id = res.getInt(res.getColumnIndex(COL_ID));
            String name = res.getString(res.getColumnIndex(COL_NAME));
            String score = res.getString(res.getColumnIndex(COL_SCORE)) + "";
            hashMap.put(name, score);
            res.moveToNext();
        }
        return hashMap;
    }

   */
  public ArrayList<Player> getAllData(){
      ArrayList<Player> playerArrayList = new ArrayList<>();
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor cursor = db.rawQuery("SELECT * FROM players ORDER BY score DESC", null);

      while (cursor.moveToNext()) {
          int id = cursor.getInt(0);
          String name = cursor.getString(1);
          String score = cursor.getString(2);

          Player player = new Player(id, name, score);

          playerArrayList.add(player);

      }
      return playerArrayList;
  }

  public Cursor getItemID(String name){
      SQLiteDatabase db = this.getWritableDatabase();
      String query = "SELECT" + COL_ID + "FROM" + TABLE_NAME + "WHERE" +COL_NAME+ "= '" +name +"'";
      Cursor data = db.rawQuery(query, null);
      return  data;

  }

}




