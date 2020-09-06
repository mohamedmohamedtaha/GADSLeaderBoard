package com.mohamedtaha.imagine.gadsleaderboard.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.mohamedtaha.imagine.gadsleaderboard.ClickSubmit;
import com.mohamedtaha.imagine.gadsleaderboard.CloseApp;
import com.mohamedtaha.imagine.gadsleaderboard.HelperClass;
import com.mohamedtaha.imagine.gadsleaderboard.R;
import com.mohamedtaha.imagine.gadsleaderboard.SectionsPagerAdapter;
import com.mohamedtaha.imagine.gadsleaderboard.databinding.ActivityMainBinding;

import androidx.databinding.DataBindingUtil;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ClickSubmit {
    private ActivityMainBinding activityMainBinding = null;
    private Boolean exitApp = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding   = DataBindingUtil.setContentView(this,R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        activityMainBinding.viewPagerMainActivity.setAdapter(sectionsPagerAdapter);
        activityMainBinding.tabsMainActivity.setupWithViewPager(activityMainBinding.viewPagerMainActivity);
        activityMainBinding.setClickSubmitMainActivity(this::submit);

    }

    @Override
    public void submit() {
        Intent submit_activity = new Intent(MainActivity.this,SubmitActivity.class);
        startActivity(submit_activity);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        exitApp();
    }
    private void exitApp(){
        if (exitApp) {
            CloseApp.closeApp(this);
            return;
        }
        exitApp = true;
        CloseApp.customToast(this,getString(R.string.exit_app));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                exitApp = false;
            }
        }, 2000);
    }
}