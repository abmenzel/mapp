package com.example.fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // Buttons
    Button frag1_button;
    Button frag2_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frag1_button = findViewById(R.id.frag1);
        frag2_button = findViewById(R.id.frag2);

        // 3. We use a fragment manager to keep track of all the fragments that exist in our activity.
        FragmentManager fm = getFragmentManager();

        // 4. We set up two buttons to add/remove our fragment 1 and 2, using the samle fragment xml file.
        //    We pass different information to each fragment.
        frag1_button.setOnClickListener(v -> {
            FragmentTransaction ft = fm.beginTransaction();
            Fragment existingFrag = fm.findFragmentById(R.id.frag1_container);

            // 5. We check whether a fragment already exists, if not we create it.
            if(existingFrag == null){
                Fragment frag = new FragmentExample();

                Bundle bundle = new Bundle();
                bundle.putString("text", "Fragment1");
                bundle.putString("color", "#6b3ea3");
                frag.setArguments(bundle);

                ft.add(R.id.frag1_container, frag, null);
            }else{
                ft.remove(existingFrag);
            }
            ft.commit();
        });

        // 6. Fragments are useful for modularity, and reusability.
        // For example, here we are re-using the fragments in the landscape layout.

        frag2_button.setOnClickListener(v -> {
            FragmentTransaction ft = fm.beginTransaction();
            Fragment existingFrag = fm.findFragmentById(R.id.frag2_container);

            if(existingFrag == null){
                Fragment frag = new FragmentExample();

                Bundle bundle = new Bundle();
                bundle.putString("text", "Fragment2");
                bundle.putString("color", "#ac11fa");

                frag.setArguments(bundle);
                ft.add(R.id.frag2_container, frag, null);
            }else{
                ft.remove(existingFrag);
            }
            ft.commit();
        });
    }
}