package com.example.adg_bmi_application;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView avd_done;
    private AnimatedVectorDrawableCompat avdc;
    private AnimatedVectorDrawable avd;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        );

        getSupportActionBar().hide();
        avd_done = findViewById(R.id.avd_done);

        Drawable drawable = avd_done.getDrawable();
        if(drawable instanceof  AnimatedVectorDrawableCompat) {
            avdc = (AnimatedVectorDrawableCompat) drawable;
            avdc.start();
        }

        if( drawable instanceof  AnimatedVectorDrawable) {
            avd = (AnimatedVectorDrawable) drawable;
            avd.start();
        }

        Thread thread = new Thread(new Runnable() {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {
                SystemClock.sleep(4000);
                Intent intent = new Intent(MainActivity.this, FirstPage.class);
                startActivity(intent);
                finish();
            }
        });

        thread.start();

    }
}