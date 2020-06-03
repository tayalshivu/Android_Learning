package com.example.androidinternity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread startTimer = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                    Intent intent0 = new Intent(SplashActivity.this, LoginSharedPref.class);
                    startActivity(intent0);
//                    overridePendingTransition(R.transition.fadin, R.transition.fadout);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };
        startTimer.start();

    }
}
