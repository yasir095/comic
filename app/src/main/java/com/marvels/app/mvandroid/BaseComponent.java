package com.marvels.app.mvandroid;

import com.marvels.app.mvandroid.activity.MainActivityPresenter;
import com.marvels.app.mvandroid.core.resultset.ComicResultSet;

/**
 * Created by yasirmahmood on 23/09/2017.
 */

public interface BaseComponent {

    void inject(ComicResultSet target);
    void inject(MainActivityPresenter target);
}
