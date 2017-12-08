package com.pm.rn;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.pm.rn.hot.HotUpdateOwner;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        RNCacheViewManager.init(this);
        ReactPreLoader.init(this, new ReactInfo("RN", null));
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_main);
        Button textView = (Button) findViewById(R.id.tv);
        Button button = (Button) findViewById(R.id.button);
        Button hot = (Button) findViewById(R.id.btn_hot);
        Button rn = (Button) findViewById(R.id.btn_rn);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LauncherActivity.class));
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MyReactActivity.class));
            }
        });

        hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HotUpdateOwner updateOwner = new HotUpdateOwner(getApplicationContext());
            }
        });

        rn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RNActivity.class));
            }
        });


//        Handler handler = new Handler();
//        handler.postDelayed(new SplashHandler(), 2000);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    class SplashHandler implements Runnable {
        @Override
        public void run() {
            startActivity(new Intent(getApplication(), MainActivity.class));
            MainActivity.this.finish();
        }
    }
}
