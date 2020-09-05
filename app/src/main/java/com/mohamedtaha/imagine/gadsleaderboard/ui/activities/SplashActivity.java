package com.mohamedtaha.imagine.gadsleaderboard.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mohamedtaha.imagine.gadsleaderboard.R;
import com.mohamedtaha.imagine.gadsleaderboard.SplashInteractor;
import com.mohamedtaha.imagine.gadsleaderboard.SplashView;

public class SplashActivity extends AppCompatActivity implements SplashView {
    private SplashInteractor splashInteractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashInteractor = new SplashInteractor(this, this);
        splashInteractor.goToMainActivity();
    }

    @Override
    public void showMainActivity() {
        Intent showMainActivity = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(showMainActivity);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        splashInteractor.onDestory();
    }
}