package com.marvels.app.mvandroid;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by yasirmahmood on 23/09/2017.
 */

@Singleton
@Component(modules = {MainDaggerModule.class})
public interface AppComponent extends BaseComponent
{

}
