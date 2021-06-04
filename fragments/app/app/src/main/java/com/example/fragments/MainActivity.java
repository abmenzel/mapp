package com.example.fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // Buttons
    Button frag1_button;
    Button frag2_button;

    boolean frag1_attached = false;
    boolean frag2_attached = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frag1_button = findViewById(R.id.frag1);
        frag2_button = findViewById(R.id.frag2);

        FragmentManager fm = getFragmentManager();

        frag1_button.setOnClickListener(v -> {
            if(frag1_attached){
                FragmentTransaction ft = fm.beginTransaction();
                ft.remove(fm.findFragmentById(R.id.frag1_container));
                ft.commit();
                frag1_attached = false;
            }else{
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.frag1_container, new FragmentExample(), null);
                ft.commit();
                frag1_attached = true;
            }
        });

        frag2_button.setOnClickListener(v -> {
            if(frag2_attached){
                FragmentTransaction ft = fm.beginTransaction();
                ft.remove(fm.findFragmentById(R.id.frag2_container));
                ft.commit();
                frag2_attached = false;
            }else{
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.frag2_container, new FragmentExample(), null);
                ft.commit();
                frag2_attached = true;
            }
        });


    }
}