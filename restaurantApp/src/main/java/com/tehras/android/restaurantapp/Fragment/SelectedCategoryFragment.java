package com.tehras.android.restaurantapp.Fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.tehras.android.restaurantapp.Fetcher.FetchItemCategories;
import com.tehras.android.restaurantapp.Models.RestaurantMenuItem;
import com.tehras.android.restaurantapp.R;
import com.tehras.android.restaurantapp.Util.Util;
import java.util.ArrayList;


/**
 * Created by tehras on 5/5/14.
 */
public class SelectedCategoryFragment extends Fragment {
    ArrayList<RestaurantMenuItem> mMenuItems;
    ListView mMenuItemListView;
    private String categoryId;
    private static final String TAG = "SelectedCategoryFragment";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        categoryId = getActivity().getIntent().getStringExtra(RestaurantFragment.MENUCATEGORY_EXTRA);

        new FetchMenuItemsTask().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //TO DO
        View v = inflater.inflate(R.layout.restaurant_chose_item_fragment,container,false);

        mMenuItemListView = (ListView)v.findViewById(android.R.id.list);

        v.findViewById(android.R.id.list).setVisibility(View.VISIBLE);
        setupAdapter();

        v.findViewById(R.id.pb_loading).setVisibility(View.INVISIBLE);
        return v;
    }

    void setupAdapter() {
        if(getActivity() == null || mMenuItemListView == null) return;

        if(mMenuItems != null){
            mMenuItemListView.setAdapter(new MenuItemAdapter(mMenuItems));
        } else {
            mMenuItemListView.setAdapter(null);
        }
    }

    /**
     * This Method Will fetch the menu_categories
     * Has to be AsyncTask because it's not allowed to be on the mainActivity
     */
    private class FetchMenuItemsTask extends AsyncTask<Void, Void, ArrayList<RestaurantMenuItem>> {
        ProgressDialog dialog = new ProgressDialog(getActivity());
        @Override
        protected void onPreExecute(){
            super.onPreExecute();

            dialog.setMessage("Loading Menu Items, Please Wait...");
            dialog.setProgressStyle(R.style.TextAppearance_AppCompat_Base_CompactMenu_Dialog);
            dialog.show();
        }

        @Override
        protected ArrayList<RestaurantMenuItem> doInBackground(Void... params) {
            mMenuItems = new FetchItemCategories().fetchAllMenuItems(categoryId);
            return mMenuItems;
        }

        @Override
        protected void onPostExecute(ArrayList<RestaurantMenuItem> result) {
            setupAdapter();
            dialog.dismiss();

            super.onPostExecute(result);
        }
    }

    public class MenuItemAdapter extends ArrayAdapter<RestaurantMenuItem> {

        public MenuItemAdapter(ArrayList<RestaurantMenuItem> menuItems) {
            super(getActivity(), R.layout.menu_item, menuItems);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            RestaurantMenuItem menuItem = getItem(position);
            if (convertView == null || menuItem == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.menu_item, parent, false);
            }

            TextView itemName = (TextView) convertView.findViewById(R.id.menu_item_name);
            TextView itemDescription = (TextView) convertView.findViewById(R.id.menu_item_description);
            TextView itemPrice = (TextView) convertView.findViewById(R.id.menu_item_price);

            if (Util.isNotEmptyOrNull(menuItem.getName())) {
                itemName.setText(menuItem.getName());
            }
            if (Util.isNotEmptyOrNull(menuItem.getDescription())) {
                itemDescription.setText(menuItem.getDescription());
            }
            if (menuItem.getPrice()!=null) {
                itemPrice.setText("$" + menuItem.getPrice().toString());
            }
            return convertView;

        }
    }
}
