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

    List<String> itemList;
    Button addButton;
    TextView nameField;
    RecyclerView rv;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButton = findViewById(R.id.add);
        nameField = findViewById(R.id.name_field);

        // 1. At first we reference our empty recycler view.
        rv = findViewById(R.id.recycler_view);

        // 2. We create some data that our RecyclerView should hold.
        //    For this example we just use an ArrayList.
        itemList = new ArrayList<>();
        itemList.add("Elephant");
        itemList.add("Zebra");
        itemList.add("Panther");
        itemList.add("Giraffe");

        // 3. We create an adapter, and pass our data.
        adapter = new Adapter(this, itemList);

        // 4. We create a text field to add new items to the list.
        //    Here we notify our adapter when the dataset has changed.
        //    This is the lazy approach, which forces the whole list to re-render.
        addButton.setOnClickListener(v -> {
            String newItemName = nameField.getText().toString();

            if(!newItemName.isEmpty()){
                itemList.add(newItemName);
                adapter.notifyDataSetChanged();
            }
        });

        // 5. We set the desired layout for the recyclerview, and attach the adapter.
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }


    @Override
    public void update(Observable observer, Object data){ }

    // 6. Each item in the recyclerview, is represented in a ViewHolder (ItemHolder).
    //    The item holder specifies what elements are present, and what should happen when it is created.
    private class ItemHolder extends RecyclerView.ViewHolder {
        public TextView itemName;

        public ItemHolder(View view) {
            super(view);
            itemName = view.findViewById(R.id.item_name);
        }

        public void bind(String text){
            itemName.setText(text);
        }
    }

    // 7. The adapter keeps hold of the dataset, and defines what happens when new data is added.
    //    This happens in the onCreateViewHolder and onBindViewHolder methods.
    //    First we inflate the layout from the xml file "item".
    //    Then we bind information from the dataset, to the layout.
    private class Adapter extends RecyclerView.Adapter<ItemHolder>{

        private List<String> items;
        private LayoutInflater inflater;

        public Adapter(Context context, List<String> items){
            this.inflater = LayoutInflater.from(context);
            this.items = items;
        }

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = inflater.inflate(R.layout.item, viewGroup, false);
            return new ItemHolder(view);
        }

        @Override
        public void onBindViewHolder(ItemHolder viewHolder, final int position) {
            viewHolder.bind(items.get(position));
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }

}