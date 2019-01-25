package com.m2.developer.DBFlow.Config;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by M2-Developer.
 */

public class AppConfig extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        FlowManager.init(this);
    }
}
