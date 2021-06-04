package com.example.intents;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class NoteActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        logAnyIntents(getIntent().getExtras());
    }

    private void logAnyIntents(Bundle extras){
        if(extras == null) {
            return;
        }else{
            System.out.println(extras.getString(Intent.EXTRA_TEXT));
        }
    }
}
