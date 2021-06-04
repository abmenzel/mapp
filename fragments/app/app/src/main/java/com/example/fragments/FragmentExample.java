package com.example.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.Fragment;
import android.widget.TextView;

public class FragmentExample extends Fragment {

    TextView text;

    // 1. When a fragment view is created, we must use an inflater to create views for the given xml file.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment, container, false);
        text = view.findViewById(R.id.frag_title);
        return view;
    }

    // 2. When the activity hosting our fragment is created, we pass some information from the activity onto the fragment.
    //    In this case I passed some text and a color string.
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            text.setText(bundle.getString("text"));
            text.setBackgroundColor(Color.parseColor(bundle.getString("color")));
        }
    }


}
