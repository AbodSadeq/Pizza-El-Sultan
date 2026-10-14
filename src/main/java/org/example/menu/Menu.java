package org.example.menu;

import org.example.model.MenuItem;

import java.util.ArrayList;

public class Menu {
    private ArrayList<MenuItem> items = new ArrayList<>();

    public void addItem(MenuItem item){
        items.add(item);
    }

    public boolean isAvailable(String name){
        for(MenuItem item : items){
            if(item.getName().equals(name)&& item.isAvailable()){
                return true;
            }
        }
        return false;
    }

}
