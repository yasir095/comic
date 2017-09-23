package com.marvels.app.mvandroid.core.request;

import java.util.Map;

/**
 * Created by yasirmahmood on 23/09/2017.
 */

public class HttpRequestRunnable implements Runnable
{
    private String url;
    private HttpRequest httpRequest;
    private String requestType;
    private Map<String, String> httpPostParams;

    public HttpRequestRunnable(String urlString, String requestType, HttpResponseDelegate delegate)
    {
        this.url = urlString;
        this.requestType = requestType;
        httpRequest = new HttpRequestImp();
        httpRequest.setDelegate(delegate);
    }

    public void setPostParams(Map<String, String> httpPostParams){
        this.httpPostParams = httpPostParams;
    }

    @Override
    public void run()
    {
        if(requestType != null && requestType == "POST")
        {
            httpRequest.post(url, httpPostParams);
        }
        else
        {
            httpRequest.get(url);
        }

    }
}
