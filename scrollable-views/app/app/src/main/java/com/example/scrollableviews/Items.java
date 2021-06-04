package com.example.scrollableviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Items extends Observable {
    List<String> itemList;

    public Items(){
        itemList = new ArrayList<>();
    }

    public void add(String newItem){
        itemList.add(newItem);
        setChanged();
        notifyObservers();
        System.out.println(this.size());
    }

    public String get(int position){
        return itemList.get(position);
    }

    public int size(){
        return itemList.size();
    }
}
