package com.example.asus.mycar.logoActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;

import com.example.asus.mycar.mainActivity.MainActivity;

/**
 * Created by asus on 2016-08-25.
 */
public class LogoActivityPresenter {

    Activity activity;


    LogoActivityPresenter(Activity activity){
        this.activity = activity;

    }

    public void startNewActivity() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                Intent intent = new Intent(activity, MainActivity.class);
                activity.startActivity(intent);

            }
        }, 3000);
    }

}
