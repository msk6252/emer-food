package com.example.msk6252.emer_food;

import android.provider.BaseColumns;

/**
 * Created by msk6252 on 16/06/27.
 */
public final class FoodContract {

    public FoodContract() {}

    public static abstract class Food implements BaseColumns {
        public static final String TABLE_NAME = "emerfood";
        public static final String COL_NAME= "name";
        public static final String COL_DAY = "day";
    }
}
