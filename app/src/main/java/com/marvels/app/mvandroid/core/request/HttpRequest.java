package com.marvels.app.mvandroid.core.request;

import java.util.Map;

/**
 * Created by yasirmahmood on 23/09/2017.
 */

public interface HttpRequest
{
    public void setDelegate(HttpResponseDelegate responseDelegate);
    public void get(String strUrl);
    public void post(String url, Map<String, String> params);
}
