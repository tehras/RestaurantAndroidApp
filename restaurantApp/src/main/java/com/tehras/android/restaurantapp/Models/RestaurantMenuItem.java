package com.tehras.android.restaurantapp.Models;

import java.util.ArrayList;

/**
 * Created by tehras on 5/5/14.
 */
public class RestaurantMenuItem {
    String id;
    String name;
    String description;
    String imageUrl;
    String price;

    public ArrayList<String> getBelongs_to() {
        return belongs_to;
    }

    public void setBelongs_to(ArrayList<String> belongs_to) {
        this.belongs_to = belongs_to;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    ArrayList<String> belongs_to;
    int user_id;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return id + " - " + name + " - " + description + " - " + imageUrl + " - " + price;
    }
}
