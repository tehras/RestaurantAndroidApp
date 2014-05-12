package com.tehras.android.restaurantapp.Fetcher;

import android.util.Log;

import com.google.gson.Gson;
import com.tehras.android.restaurantapp.Models.RestaurantMenuItem;
import com.tehras.android.restaurantapp.Util.ConstantsUtil;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by tehras on 5/5/14.
 */
public class FetchItemCategories {
    //Final Strings
    private static final String TAG = "FetchMenuCategories";

    //Variables
    RestaurantMenuItem[] mMenuItems;
    ArrayList<RestaurantMenuItem> mArrayMenuItems;

    public RestaurantMenuItem[] getMenuItems(String url) throws IOException {
        StringBuilder builder = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        Gson gson = new Gson();

        try {
            HttpResponse response = client.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                try {
                    if (!String.valueOf(builder).matches("Error.*")) {
                        mMenuItems = gson.fromJson(String.valueOf(builder), RestaurantMenuItem[].class);
                    } else {
                        return null;
                    }
                } catch (IllegalStateException ise) {
                    return null;
                }
            } else {
                Log.e("", "Failed to download file");
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mMenuItems;
    }

    public ArrayList<RestaurantMenuItem> fetchAllMenuItems(String categoryId) {
        try {
            String urlStringBuilder = new String();
            urlStringBuilder = ConstantsUtil.finalUrl+"menu_items.json?api="+ConstantsUtil.apiKey+"&category="+categoryId;
            RestaurantMenuItem[] result = new FetchItemCategories().getMenuItems(urlStringBuilder);
            if (result != null) {
                return mArrayMenuItems = new ArrayList(Arrays.asList(result));
            }
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch menu categories: ", ioe);
        }
        return null;
    }
}
