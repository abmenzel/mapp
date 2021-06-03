package com.example.persistency;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    DB db;
    Button add;
    TextView itemField;
    ItemList itemList;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = findViewById(R.id.add_item);
        itemField = findViewById(R.id.item_name);
        db = new DB(this);

        // 5. Add a simple way of adding items to the database
        add.setOnClickListener(v -> {
            String itemName = itemField.getText().toString();
            if(!itemName.isEmpty()){
                DB.add(itemName);
            }
        });

        // 6. Add a simple list to show items from the database
        itemList = new ItemList(this, db.getAll());
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