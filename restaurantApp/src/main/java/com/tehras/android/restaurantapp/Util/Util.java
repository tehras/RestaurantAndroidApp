package com.tehras.android.restaurantapp.Util;

import android.content.res.Resources;

/**
 * Created by tehras on 5/5/14.
 */
public class Util {

    /**
     * Adapter to get Proper layout
     */

    public static int setRandomColor(int rand, Resources resources){
        switch (rand) {
            case 1:
                return resources.getColor(android.R.color.holo_purple);
            case 2:
                return resources.getColor(android.R.color.holo_green_light);
            case 3:
                return resources.getColor(android.R.color.holo_blue_light);
            case 4:
                return resources.getColor(android.R.color.holo_red_light);
            case 5:
                return resources.getColor(android.R.color.holo_orange_light);
            default:
                return resources.getColor(android.R.color.holo_red_dark);
        }
    }

    public static boolean isNotEmptyOrNull(String inputString){
        if(inputString!=null || "".equalsIgnoreCase(inputString)){
            return true;
        } else {
            return false;
        }
    }
}
