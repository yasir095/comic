package com.marvels.app.mvandroid.core.request;

import com.marvels.app.mvandroid.core.helper.XError;

/**
 * Created by yasirmahmood on 23/09/2017.
 */

public interface HttpResponseDelegate
{
    //void onConnect();
    void onSuccess(String result);
    void onFailHttpRequest(XError e);
    void onProgressHttpRequest(int progress);
}
