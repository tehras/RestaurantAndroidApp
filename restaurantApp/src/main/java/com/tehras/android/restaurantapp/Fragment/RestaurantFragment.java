package com.tehras.android.restaurantapp.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.tehras.android.restaurantapp.Fetcher.FetchMenuCategories;
import com.tehras.android.restaurantapp.Models.MenuCategory;

import com.tehras.android.restaurantapp.R;
import com.tehras.android.restaurantapp.SelectedCategoryActivity;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by tehras on 5/4/14.
 */
public class RestaurantFragment extends Fragment {
    private static final String TAG = "RestaurantFragment";
    public static final String MENUCATEGORY_EXTRA = "menuCategory";
    ListView mStaggeredGridView;
    ArrayList<MenuCategory> mMenuCategories;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //TO DO
        View v = inflater.inflate(R.layout.restaurant_chose_category_fragment,container,false);

        mStaggeredGridView = (ListView)v.findViewById(R.id.gridView);
        //Call to retrieve Menu Items
        new FetchMenuCategoryTask().execute();

        return v;
    }

    void setupAdapter() {
        if(getActivity() == null || mStaggeredGridView == null) return;

        if(mMenuCategories != null){
            mStaggeredGridView.setAdapter(new MenuCategoryAdapter(mMenuCategories));
        } else {
            mStaggeredGridView.setAdapter(null);
        }
    }


    /**
     * This Method Will fetch the menu_categories
     * Has to be AsyncTask because it's not allowed to be on the mainActivity
     */
    private class FetchMenuCategoryTask extends AsyncTask<Void, Void, ArrayList<MenuCategory>> {
        ProgressDialog dialog = new ProgressDialog(getActivity());
        @Override
        protected void onPreExecute(){
            super.onPreExecute();

            dialog.setMessage("Loading Menu Categories, Please Wait...");
            dialog.setProgressStyle(R.style.TextAppearance_AppCompat_Base_CompactMenu_Dialog);
            dialog.show();
        }

        @Override
        protected ArrayList<MenuCategory> doInBackground(Void... params) {
            mMenuCategories = new FetchMenuCategories().fetchAllCategories();
            return mMenuCategories;
        }

        @Override
        protected void onPostExecute(ArrayList<MenuCategory> result) {
            dialog.dismiss();
            if(result != null && !result.isEmpty()) {
                setupAdapter();
            }

            super.onPostExecute(result);
        }
    }

    public class MenuCategoryAdapter extends ArrayAdapter<MenuCategory> {

        public MenuCategoryAdapter(ArrayList<MenuCategory> menuCategories) {
            super(getActivity(), 0, menuCategories);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.menu_category, parent, false);
            }

            final MenuCategory menuCategory = getItem(position);
            TextView categoryName = (TextView)convertView.findViewById(R.id.menu_category_name);
            categoryName.setText(menuCategory.getName());
            TextView categoryDescription = (TextView)convertView.findViewById(R.id.menu_category_description);
            categoryDescription.setText(menuCategory.getDescription());
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(TAG, "MenuCategory = " + menuCategory.getName() + " Was Selected");
                    Intent i = new Intent(getActivity(), SelectedCategoryActivity.class);
                    i.putExtra(MENUCATEGORY_EXTRA, menuCategory.getId());
                    startActivity(i);
                }
            });

            return convertView;

        }
    }
}
