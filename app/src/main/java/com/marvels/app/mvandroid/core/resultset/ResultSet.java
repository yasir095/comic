package com.marvels.app.mvandroid.core.resultset;

import android.content.Context;

import com.marvels.app.mvandroid.core.helper.XError;
import com.marvels.app.mvandroid.core.parser.Parser;
import com.marvels.app.mvandroid.core.parser.ParserDelegate;
import com.marvels.app.mvandroid.core.request.HttpResponseDelegate;
import com.marvels.app.mvandroid.core.request.TaskExecutor;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by yasirmahmood on 11/06/2017.
 */

public abstract class ResultSet<T> implements HttpResponseDelegate, ParserDelegate
{
    private enum State {
        INITIALISED, FETCHING, PARSING, COMPLETE, FAIL
    };

    public enum Method{GET, POST, PUT, DELETE};

    protected @Inject TaskExecutor taskExecutor;

    protected State state;
    protected Parser<T> parser;
    protected ResultSetDelegate delegate;

    protected Context applicationContext;
    protected String requestUrl;
    protected Map<String, String> postParams;
    protected Map<String, String> headers;
    protected Method method;

    public ResultSet() {
        this.state = State.INITIALISED;
        this.postParams = new HashMap<>();
        this.method = Method.GET;
    }

    public Parser<T> getParser() {
        return parser;
    }

    public void setParser(Parser<T> parser)
    {
        this.parser = parser;
    }

    public ResultSetDelegate getDelegate() {
        return delegate;
    }

    public void setDelegate(ResultSetDelegate delegate) {
        this.delegate = delegate;
    }

    public Context getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public Map<String, String> getPostParams() {
        return postParams;
    }

    public void setPostParams(Map<String, String> postParams) {
        this.postParams = postParams;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public ResultSet<T> fetch(ResultSetDelegate delegate)
    {
        this.delegate = delegate;

        if (this.state != State.FETCHING) {
            this.state = State.FETCHING;

            taskExecutor.requestWebContents(this.requestUrl, this.method.name(), this.postParams, this);
        }

        return this;
    }

    public void cancel()
    {
        if(this.state == State.FETCHING)
        {
            //@todo: implement cancel operation.
        }
    }

    public void resetState()
    {
        this.state = State.INITIALISED;
    }

    @Override
    public void onSuccess(String response)
    {
        this.state = State.PARSING;
        this.parser.getParsedData(response, this);
    }

    @Override
    public void onFailHttpRequest(XError error)
    {
        this.state = State.FAIL;
        this.delegate.onFailResult(this, 503);
    }

    @Override
    public void onProgressHttpRequest(int progress)
    {
        //@todo: need implementation.
    }

    @Override
    public void onParseComplete(Object data)
    {
        this.state = State.COMPLETE;
        this.setParsedData((T) data);
        this.delegate.onCompleteResult(this);
    }

    @Override
    public void onParseFail(Error error)
    {
        this.state = State.FAIL;
        //@todo: implements statusCode response here.
        this.delegate.onFailResult(this, 0);
    }

    protected abstract void setParsedData(T parsedData);
    public abstract T getParsedData();
}
