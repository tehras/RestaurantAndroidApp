package com.tehras.android.restaurantapp;

import android.support.v4.app.Fragment;

import com.tehras.android.restaurantapp.Fragment.RestaurantFragment;


public class RestaurantActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new RestaurantFragment();
    }
}
