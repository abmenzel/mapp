package com.example.persistency;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    // 1. Create a DBHelper class extending SQLiteOpenHelper, which gives us access to the SQLite implementation in android.
    //    Decide what columns the table should have, this can be optimized using a seperate DB Schema.
    public DBHelper(Context context) {
        super(context, "Persistent.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("CREATING TABLE");
        db.execSQL("CREATE TABLE Items (Item TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Items");
        onCreate(db);
    }
}
