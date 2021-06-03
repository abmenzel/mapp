package com.example.persistency;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DB {

    static SQLiteDatabase db;

    DB(Context context){
        db = new DBHelper(context).getWritableDatabase();
    }

    public void close(){
        db.close();
    }

    public static void add(String itemName){
        ContentValues values = new ContentValues();
        values.put("Item", itemName);
        db.insert("Items",null, values);
    }

    public List<String> getAll(){
        List items = new ArrayList();
        Cursor cursor = db.query(
                "Items",
                null,
                null,
                null,
                null,
                null,
                null
        );

        while(cursor.moveToNext()){
            String itemName = cursor.getString(0);
            items.add(itemName);
            System.out.println(itemName);
        }

        return items;
    }
}
