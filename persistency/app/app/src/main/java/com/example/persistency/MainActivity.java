package com.example.persistency;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    DB db;
    Button add;
    TextView itemField;
    CustomAdapter itemList;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = findViewById(R.id.add_item);
        itemField = findViewById(R.id.item_name);
        db = new DB(this);

        add.setOnClickListener(v -> {
            String itemName = itemField.getText().toString();
            if(!itemName.isEmpty()){
                DB.add(itemName);
            }
        });

        itemList = new CustomAdapter(this, db.getAll());
        rv = findViewById(R.id.item_list);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(itemList);
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }
}