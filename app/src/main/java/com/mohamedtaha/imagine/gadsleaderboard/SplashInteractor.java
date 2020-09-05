package com.mohamedtaha.imagine.gadsleaderboard;

import android.app.Activity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashInteractor {
    private static final int WAIT_TIME = 2500;
    private Activity context ;
    private Timer waitTimer;
    private SplashView splashView;

    public SplashInteractor(Activity context, SplashView splashView) {
        this.context = context;
        this.splashView = splashView;
    }

    public void goToMainActivity(){
        if (splashView != null){
            waitTimer = new Timer();
            waitTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            splashView.showMainActivity();

                        }
                    });
                }
            },WAIT_TIME);
        }
    }
    public void onDestory(){
        splashView = null ;
        waitTimer = null;
    }

}
