package com.example.layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class LinearLayoutActivity extends AppCompatActivity {

    Button trigger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_layout);
        trigger = findViewById(R.id.constraint_button);

        trigger.setOnClickListener(v -> {
            Intent intent = new Intent(this, ConstraintLayoutActivity.class);
            startActivity(intent);
        });
    }
}