package com.tehras.android.restaurantapp.Fetcher;

import android.util.Log;

import com.google.gson.Gson;
import com.tehras.android.restaurantapp.Models.MenuCategory;
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
 * Created by tehras on 5/4/14.
 */
public class FetchMenuCategories {
    //Final Strings
    private static final String TAG = "FetchMenuCategories";

    //Variables
    MenuCategory[] mMenuCategories;
    ArrayList<MenuCategory> mArrayMenuCategories;

    public MenuCategory[] getMenuCategory(String url) throws IOException {
        StringBuilder builder = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        Gson gson = new Gson();

        try{
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
                String resultString = String.valueOf(builder);
                mMenuCategories = gson.fromJson(resultString, MenuCategory[].class);
            } else {
                Log.e("","Failed to download file");
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mMenuCategories;
    }

    public ArrayList<MenuCategory> fetchAllCategories() {
        try{
            String url = ConstantsUtil.finalUrl+"menu_category.json?api="+ConstantsUtil.apiKey;
            MenuCategory[] result = new FetchMenuCategories().getMenuCategory(url);
            if (result != null){
               return mArrayMenuCategories = new ArrayList(Arrays.asList(result));
            }
        } catch (IOException ioe){
            Log.e(TAG, "Failed to fetch menu categories: ", ioe);
        }
        return null;
    }
}
