package com.marvels.app.mvandroid;

import android.app.Application;

/**
 * Created by yasirmahmood on 23/09/2017.
 */

public class App extends Application
{
    private static AppComponent component;

    private static App instance = new App();

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        component = createComponent();
    }

    public AppComponent createComponent()
    {
        return DaggerAppComponent.builder()
                .mainDaggerModule(new MainDaggerModule())
                .build();
    }

    public AppComponent getComponent() {
        return component;
    }
}
