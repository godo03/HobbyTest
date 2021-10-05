package com.thesis.let.webservicedemo.classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class ReviewDatabaseHelper extends SQLiteOpenHelper {

    public static int DB_VERSION = 1;
    public static String DB_NAME = "review.db";

    public static String TABLE_NAME = "review";
    public static String COL_ID = "id";
    public static String COL_1 = "questions";
    public static String COL_2 = "answers";



    private static String query = "CREATE TABLE "+ TABLE_NAME +" (" + COL_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_1 + " TEXT NOT NULL," + COL_2 +
            " TEXT NOT NULL);";

    public ReviewDatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(query);
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String question, String answer){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, question);
        contentValues.put(COL_2, answer);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.execSQL("DELETE FROM " +TABLE_NAME);
        db.close();
    }


   /* public Cursor getAllData(){
        SQLiteDatabase query = this.getWritableDatabase();
        Cursor res = query.rawQuery("SELECT * FROM players", null);
        return res;
    }*/

    public HashMap<String, String> getAllData() {
        HashMap<String, String> hashMap = new HashMap<>();

        /// hp = new HashMap();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor res = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            String question = res.getString(res.getColumnIndex(COL_1));
            String answer = res.getString(res.getColumnIndex(COL_2)) + "";
            hashMap.put(question, answer);
            res.moveToNext();
        }
        return hashMap;
    }
}




