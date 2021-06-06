package com.example.navigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentStart extends Fragment {

    Button button1;
    Button button2;


    public FragmentStart() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_start, container, false);

        button1 = v.findViewById(R.id.button1);
        button2 = v.findViewById(R.id.button2);

        button1.setOnClickListener(button -> {
            Navigation.findNavController(v).navigate(R.id.action_fragmentStart_to_fragment1);
        });

        button2.setOnClickListener(button-> {
            System.out.println("TEST");
            Navigation.findNavController(v).navigate(R.id.action_fragmentStart_to_fragment2);
        });
        return v;
    }
}