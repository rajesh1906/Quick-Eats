package com.quickeats.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.quickeats.R;
import com.quickeats.dashboard.DashboardActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

    }
    @OnClick(R.id.tv_next)
    void launchDashboardScreen(){
        startActivity(new Intent(this, DashboardActivity.class));
        finish();
    }
    @OnClick(R.id.txtsignin)
    void launchSignInScreen(){
        startActivity(new Intent(this,SignInActivity.class));
        finish();
    }

}
