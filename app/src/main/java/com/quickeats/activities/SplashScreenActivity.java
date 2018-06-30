package com.quickeats.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.quickeats.MvpBaseActivity;
import com.quickeats.R;

public class SplashScreenActivity  extends AppCompatActivity{
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreenActivity.this,SignInActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}
