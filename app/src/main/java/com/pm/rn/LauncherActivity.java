package com.pm.rn;


public class LauncherActivity extends CacheReactActivity {


    @Override
    protected String getMainComponentName() {
        return ReactPreLoader.getReactInfo().getMainComponentName();
    }

    @Override
    public ReactInfo getReactInfo() {
        return ReactPreLoader.getReactInfo();
    }

}
