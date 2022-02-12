package com.itay.finalproject.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.FirebaseApp;
import com.itay.finalproject.R;

import java.util.Random;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();


        FirebaseApp.initializeApp(getApplicationContext());
        setContentView(R.layout.activity_splash);
        Thread thread= new Thread(){
            @Override
            public void run() {
                try {
                    //Time that splash screen will show on screen
                    sleep(4000);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                finally {
                    Intent mainIntent= new Intent(SplashActivity.this, AuthActivity.class);
                    startActivity(mainIntent);

                }
            }
        };thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
