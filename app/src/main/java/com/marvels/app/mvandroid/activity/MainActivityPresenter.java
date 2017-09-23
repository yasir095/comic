package com.marvels.app.mvandroid.activity;

import com.marvels.app.mvandroid.App;
import com.marvels.app.mvandroid.Config;
import com.marvels.app.mvandroid.core.helper.Common;
import com.marvels.app.mvandroid.core.parser.ComicParser;
import com.marvels.app.mvandroid.core.request.Url;
import com.marvels.app.mvandroid.core.resultset.ComicResultSet;
import com.marvels.app.mvandroid.core.resultset.ResultSet;

import java.util.HashMap;

import javax.inject.Inject;

/**
 * Created by yasirmahmood on 23/09/2017.
 */

public class MainActivityPresenter extends BasePresenter {

    private View viewDelegate;
    public @Inject Config config;

    public MainActivityPresenter(View viewDelegate)
    {
        this.viewDelegate = viewDelegate;
        App.getInstance().getComponent().inject(this);

        ComicParser comicParser = new ComicParser();
        ComicResultSet comicResultSet = new ComicResultSet();

        comicResultSet.setParser(comicParser);
        comicResultSet.setDelegate(this);

        String ts = Long.toString(Common.getTimeInMillis());
        String combineKey = ts+config.getPrivateApiKey()+config.getPublicApiKey();

        HashMap<String, String> params = new HashMap<>();
        params.put("ts", ts);
        params.put("hash", Common.md5(combineKey));
        params.put("apikey", config.getPublicApiKey());

        Url url = new Url();
        url.setBaseUrl(config.getBaseUrl());
        url.setMethod("/v1/public/comics");
        url.addParams(params);
        comicResultSet.setRequestUrl(url.toString());

        comicResultSet.fetch(this);
    }

    @Override
    public void onCompleteResult(ResultSet resultSet) {

        viewDelegate.updateListAdapter();
    }

    @Override
    public void onFailResult(ResultSet resultSet, int statusCode) {

    }

    public interface View
    {
        void showErrorDialog();
        void updateListAdapter();
        void showActionBar();
        void hideActionBar();
    }
}
