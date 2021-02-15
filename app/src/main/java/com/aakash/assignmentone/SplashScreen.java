package com.aakash.assignmentone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {
    Handler handler;
    Runnable runnable;
    Long timeInMills = 5000L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
    }
    @Override
    protected void onResume(){
        super.onResume();
        handler.postDelayed(runnable, timeInMills);
        timeInMills = System.currentTimeMillis();
    }

    @Override
    protected void onStop(){
        super.onStop();
        handler.removeCallbacks(runnable);
    }
}