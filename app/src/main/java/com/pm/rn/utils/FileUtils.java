package com.pm.rn.utils;

import android.content.Context;
import android.util.Log;

import java.io.File;

/**
 * Created by pm on 17-11-24.
 */

public class FileUtils implements FileConstant {
    private static final String TAG = FileUtils.class.getSimpleName();

    private static String getAppFileDir(Context context) {
        return context.getFilesDir().getAbsolutePath();
    }

    public static String getJsBundlePath(Context context) {
        String appFileDir = getAppFileDir(context);
        File bundle = context.getDir("bundle", Context.MODE_PRIVATE);
        Log.d(TAG, "getJsBundlePath: "+bundle.getAbsolutePath());
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(appFileDir).append(File.separator).append(JS_BUNDLE_FILE);
        String jsBundleFile = stringBuffer.toString();
        File file = new File(jsBundleFile);
        if (file.exists()) {
            return jsBundleFile;
        }
        return null;
    }

    public boolean isExistsFolder(String path) {
        if (path != null && !path.isEmpty()) {
            File jsBundle = new File(path);
            return jsBundle.exists();
        }
        return false;
    }

    public static boolean createBundleFolder(String path) {
        if (path != null && !path.isEmpty()) {
            File jsBundle = new File(path);
            if (!jsBundle.exists()) {
                return jsBundle.mkdirs();
            }
        }
        return false;
    }

    public static boolean copy(){
        return false;
    }
}
