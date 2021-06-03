package com.example.persistency;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DB {

    static SQLiteDatabase db;

    // 2. Instantiate a DB class by getting the writable database from the helper class.
    //    The helper class makes sure that we get the database that already exists.
    DB(Context context){
        db = new DBHelper(context).getWritableDatabase();
    }

    public void close() {
        db.close();
    }


    // 3. Create methods for adding to the database, by using simple sql queries.
    //    we can use the ContentValues class to make this easier.
    public static void add(String itemName){
        ContentValues values = new ContentValues();
        values.put("Item", itemName);
        db.insert("Items",null, values);
    }


    // 4. Grab all rows from the database and save item names in a list.
    //    Here we use the cursor class to access each row, by calling moveToNext()
    //    We can specify usual sql parameters in order to grab specific rows, using a where clause.
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
        }

        return items;
    }
}
