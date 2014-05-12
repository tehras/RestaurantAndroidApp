package com.tehras.android.restaurantapp;

import android.support.v4.app.Fragment;

import com.tehras.android.restaurantapp.Fragment.SelectedCategoryFragment;

/**
 * Created by tehras on 5/5/14.
 */
public class SelectedCategoryActivity extends SingleFragmentActivity{
    @Override
    protected Fragment createFragment() {
        return new SelectedCategoryFragment();
    }
}
