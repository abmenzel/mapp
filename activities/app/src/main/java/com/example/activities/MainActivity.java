package com.example.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button start_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println(savedInstanceState);

        start_2 = findViewById(R.id.start_2);

        if(savedInstanceState != null && savedInstanceState.containsKey("BUTTON_COLOR")){
            start_2.setBackgroundColor(Color.parseColor(savedInstanceState.getString("BUTTON_COLOR")));
        }

        start_2.setOnClickListener(button -> {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("Paused");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("Destroyed");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("INFO", "Name: Alexander");
    }
}