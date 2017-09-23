package com.marvels.app.mvandroid.core.request;

import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by yasirmahmood on 23/09/2017.
 */

public class Url {
    private String baseUrl;
    private String method;
    private String paramStart;
    private String paramSeparator;
    private String paramKeyValueSeparator;
    private HashMap<String, String> params;

    private String wholeUrl;

    public Url() {
        this.baseUrl = "";
        this.method = "";
        this.paramStart = "?";
        this.paramSeparator = "&";
        this.paramKeyValueSeparator = "=";
        this.params = new HashMap<String, String>();
    }

    // just orverride the url with url supplied.
    // dont need to create url from params;
    public Url(String url){
        this.wholeUrl = url;
    }

	/* Get/Set Methods */

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setParamStart(String paramStart) {
        this.paramStart = paramStart;
    }

    public void setParamSeparator(String paramSeparator) {
        this.paramSeparator = paramSeparator;
    }

    public void setParamKeyValueSeparator(String paramKeyValueSeparator) {
        this.paramKeyValueSeparator = paramKeyValueSeparator;
    }

    public void addParams(HashMap<String, String> params) {
        this.getUrlParams().putAll(params);
    }

    public HashMap<String, String> getUrlParams() {
        if(this.params == null){
            this.params = new HashMap<String, String>();
        }
        return this.params;
    }

    @Override
    public String toString() {

        String urlString = "";

        if(this.wholeUrl != null && this.wholeUrl.length()>0)
        {
            urlString = this.wholeUrl;
        }
        else
        {
            urlString = "" + this.baseUrl + this.method + this.paramStart;

            for (String key : this.params.keySet()) {

                urlString += key + this.paramKeyValueSeparator;
                if(this.params.get(key)!=null){

                    try
                    {
                        urlString += URLEncoder.encode(this.params.get(key), "UTF-8");
                    }
                    catch(Exception e)
                    {
                        urlString += this.params.get(key);
                    }

                }
                else
                {
                    urlString+=null;
                }

                urlString += this.paramSeparator;
            }
        }

        return urlString.substring(0, urlString.length() - 1);
    }
}
