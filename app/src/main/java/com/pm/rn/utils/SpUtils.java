package com.pm.rn.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by pm on 17-11-30.
 */

public class SpUtils {

    private static final String ISFIRST="isFirst";

    public static void put(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences("hot", Context.MODE_PRIVATE).edit();
        editor.putBoolean(ISFIRST, false);
        boolean isSuccess = editor.commit();
    }

    public static boolean get(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("", Context.MODE_PRIVATE);
        boolean b = sharedPreferences.getBoolean(ISFIRST, true);
        return b;
    }
}
