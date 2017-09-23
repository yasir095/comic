package com.marvels.app.mvandroid;

import com.marvels.app.mvandroid.core.request.TaskExecutor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yasirmahmood on 23/09/2017.
 */

@Module
public class MainDaggerModule {

    private Config config;
    private TaskExecutor taskExecutor;

    public MainDaggerModule() {

    }

    public MainDaggerModule(Config config) {
        this.config = config;
    }

    @Provides @Singleton
    public Config provideConfig()
    {
        if(config == null)
        {
            config = new Config(Config.Environment.BETA);
        }

        return config;
    }

    @Provides @Singleton
    public TaskExecutor provideTaskExecutor()
    {
        return new TaskExecutor();
    }
}
