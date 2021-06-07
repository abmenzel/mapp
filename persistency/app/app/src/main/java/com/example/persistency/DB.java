package com.example.persistency;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DB {

    private static DB db;
    private static SQLiteDatabase sql;

    DB(Context context){
        System.out.println("creating new DB");
        sql = new DBHelper(context).getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    // 2. Use a singleton pattern to create a database that is accessible from multiple parts of the app
    //    this way we can easily share data between activities etc.
    public static DB getDB(Context context){
        if (db == null) db = new DB(context);
        return db;
    }

    // 3. Create methods for adding to the database, by using simple sql queries.
    //    we can use the ContentValues class to make this easier.
    public static void add(String itemName){
        ContentValues values = new ContentValues();
        values.put("Item", itemName);
        sql.insert("Items",null, values);
    }

    // 4. Grab all rows from the database and save item names in a list.
    //    Here we use the cursor class to access each row, by calling moveToNext()
    //    We can specify usual sql parameters in order to grab specific rows, using a where clause.
    public List<String> getAll(){
        System.out.println("getting all");
        List items = new ArrayList();
        Cursor cursor = sql.query(
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
