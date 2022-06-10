package com.frabasoft.bookread.splashdetails;

import android.app.Application;
import android.os.SystemClock;

public class SplashRetardo extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        SystemClock.sleep(4000);
    }
}
