package com.example.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    Button start_1;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        start_1 = findViewById(R.id.start_1);

        start_1.setOnClickListener(button -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

}
