package com.tehras.android.restaurantapp.Models;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by tehras on 5/4/14.
 */
public class MenuCategory {
    String id;
    String name;
    String description;
    ArrayList<String> menu_items;
    int user_id;

    public ArrayList<String> getMenu_items() {
        return menu_items;
    }

    public void setMenu_items(ArrayList<String> menu_items) {
        this.menu_items = menu_items;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description + " - " + name + " - " + id + " - "+ menu_items;
    }

}
