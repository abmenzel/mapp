package com.example.scrollableviews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

    Items itemList;
    Button addButton;
    TextView nameField;
    RecyclerView rv;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemList = new Items();
        addButton = findViewById(R.id.add);
        nameField = findViewById(R.id.name_field);
        rv = findViewById(R.id.recycler_view);
        adapter = new Adapter(this, itemList);
        addButton.setOnClickListener(v -> {
            String newItemName = nameField.getText().toString();

            if(!newItemName.isEmpty()){
                itemList.add(newItemName);
                adapter.notifyDataSetChanged();
            }
        });

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
        itemList.addObserver(this);
    }

    @Override
    public void update(Observable observer, Object data){
        adapter.notifyDataSetChanged();
    }

    private class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

        private Items items;
        private LayoutInflater inflater;

        private class ViewHolder extends RecyclerView.ViewHolder {
            public TextView itemName;

            public ViewHolder(View view) {
                super(view);
                itemName = view.findViewById(R.id.item_name);
            }
        }

        public Adapter(Context context, Items items){
            this.inflater = LayoutInflater.from(context);
            this.items = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = inflater.inflate(R.layout.item, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int position) {
            viewHolder.itemName.setText(items.get(position));
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }

}