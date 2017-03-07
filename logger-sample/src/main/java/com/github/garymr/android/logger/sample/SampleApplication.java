package com.github.garymr.android.logger.sample;

import android.app.Application;

import com.github.garymr.android.logger.Level;
import com.github.garymr.android.logger.Logger;

public class SampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Logger.initialize(
                Logger.newConfigurationBuilder()
                    .setLevel(Level.ALL)
                    .setTag("LoggerSample")
                    .create());
    }
}
