package com.example.persistency;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class OtherActivity extends AppCompatActivity {

    DB db;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        db = DB.getDB(this);
        System.out.println(db.getAll().toString());
    }
}
