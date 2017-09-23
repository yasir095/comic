package com.marvels.app.mvandroid.core.helper;

import android.util.Log;

/**
 * Created by yasirmahmood on 11/06/2017.
 */

public class Logger {

    public static void logSuccess(String message)
    {
        Log.d("SUCCESS", message);
    }

    public static void logInfo(String message)
    {
        Log.i("Info", message);
    }

    public static void logError(String message)
    {
        Log.e("Error", message);
    }
}
