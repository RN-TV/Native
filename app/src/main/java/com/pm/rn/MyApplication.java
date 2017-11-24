package com.pm.rn;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;
import com.pm.rn.utils.FileUtils;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

/**
 * Created by pm on 17-7-17.
 */

public class MyApplication extends Application implements ReactApplication {
    public static final String TAG = MyApplication.class.getSimpleName();

    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(
                    new MainReactPackage()
            );
        }

        @Nullable
        @Override
        protected String getJSBundleFile() {
            String jsBundlePath = FileUtils.getJsBundlePath(getApplicationContext());
            Log.d(TAG, "getJSBundleFile: jsBundlePath=" + jsBundlePath);
            if (jsBundlePath != null) {
                return jsBundlePath;
            } else {
                return super.getJSBundleFile();
            }
        }
    };

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, /* native exopackage */ false);
    }
}
