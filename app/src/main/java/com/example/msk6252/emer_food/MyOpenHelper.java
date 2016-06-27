package com.example.msk6252.emer_food;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by msk6252 on 16/06/26.
 */
public class MyOpenHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "emerfood.db";
    public static final int DB_VERSION = 1;
    public static final String CREATE_TABLE =
            "create table " + FoodContract.Food.TABLE_NAME + " (" +
                    FoodContract.Food._ID + " integer primary key autoincrement, " +
                    FoodContract.Food.COL_NAME + " text," +
                    FoodContract.Food.COL_DAY + " text)";


    public static final String DROP_TABLE =
            "drop table if exists foods";

    public MyOpenHelper(Context context) {
        super(context, DB_NAME,null,DB_VERSION);
    }

    public  void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion){
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}
