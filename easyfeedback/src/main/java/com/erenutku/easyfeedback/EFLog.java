package com.erenutku.easyfeedback.easyfeedback;

import android.util.Log;

/**
 * Created by yeutku on 26/01/16.
 */
public class EFLog {
    private static final String TAG = "EasyFeedback";
    public static void i(String log){
        Log.i(TAG, log);
    }
    public static void e(String log){
        Log.e(TAG, log);
    }

}
