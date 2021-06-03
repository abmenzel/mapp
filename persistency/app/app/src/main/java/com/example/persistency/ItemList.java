package com.example.persistency;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemList extends RecyclerView.Adapter<ItemList.ViewHolder> {

    private List<String> items;
    private LayoutInflater mInflater;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView nameField;

        public ViewHolder(View view) {
            super(view);
            nameField = view.findViewById(R.id.name_field);
        }
    }

    public ItemList(Context context, List items){
        this.mInflater = LayoutInflater.from(context);
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = mInflater.inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.nameField.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
