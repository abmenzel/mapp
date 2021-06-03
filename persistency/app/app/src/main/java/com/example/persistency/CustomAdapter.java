package com.example.persistency;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private List<String> items;
    private LayoutInflater mInflater;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView nameField;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            nameField = (TextView) view.findViewById(R.id.name_field);
        }

    }

    public CustomAdapter (Context context, List items){
        this.mInflater = LayoutInflater.from(context);
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = mInflater.inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        System.out.println("Test, onbind");
        viewHolder.nameField.setText("TEST " + position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
