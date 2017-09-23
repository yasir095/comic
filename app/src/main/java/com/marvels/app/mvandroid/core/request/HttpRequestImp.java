package com.marvels.app.mvandroid.core.request;

import android.os.Build;

import com.marvels.app.mvandroid.core.helper.XError;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Created by yasirmahmood on 23/09/2017.
 */

public class HttpRequestImp implements HttpRequest
{
    private HttpResponseDelegate responseDelegate;

    public HttpRequestImp() {
        disableConnectionReuseIfNecessary();
    }

    @Override
    public void setDelegate(HttpResponseDelegate responseDelegate) {
        this.responseDelegate = responseDelegate;
    }

    //@todo: implement error response handler exception
    public InputStream getInputStream(URL url) throws Exception
    {
        //java.net.MalformedURLException exception
        InputStream in = null;
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        in = new BufferedInputStream(urlConnection.getInputStream());

        return in;
    }

    public String readStream(InputStream inputStream)
    {
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try
        {
            for (String line = reader.readLine(); line != null; line =reader.readLine())
            {
                stringBuffer.append(line);
            }

            inputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return stringBuffer.toString();
    }

    private String getParseUrlEncodeParams(Map<String, String> params) throws UnsupportedEncodingException
    {
        StringBuilder postParams = new StringBuilder();
        String key, value, result = "";

        for (Map.Entry<String, String> entry : params.entrySet())
        {
            key = entry.getKey();
            value = URLEncoder.encode(entry.getValue(), "UTF-8");
            postParams.append(key+"="+value+"&");
        }

        result = postParams.toString();
        //remove the last & character.
        result = result.substring(0, result.length() - 1);

        return result;
    }

    @Override
    public void get(String url)
    {
        try
        {
            InputStream inputStream = getInputStream(new URL(url));
            String result = readStream(inputStream);
            callSuccess(result);
        }
        catch(Exception e)
        {
            //@todo: implement Error message class.
            callFail(new XError(e.getMessage(), 1, e.getMessage(), e.getMessage()));
        }
    }

    @Override
    public void post(String strUrl, Map<String, String> params)
    {
        HttpURLConnection urlConnection = null;

        try
        {
            URL url = new URL(strUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setChunkedStreamingMode(0);

            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());

            if(params != null)
            {
                byte[] postDataBytes = getParseUrlEncodeParams(params).getBytes(StandardCharsets.UTF_8);
                out.write(postDataBytes);
                out.flush();
                out.close();
            }

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            String result = readStream(in);
            callSuccess(result);
        }
        catch(Exception e)
        {
            callFail(new XError(e.getMessage(), 1, e.getMessage(), e.getMessage()));
        }
        finally
        {
            urlConnection.disconnect();
        }
    }

    private void disableConnectionReuseIfNecessary() {
        // Work around pre-Froyo bugs in HTTP connection reuse.
        if (Integer.parseInt(Build.VERSION.SDK) < Build.VERSION_CODES.FROYO) {
            System.setProperty("http.keepAlive", "false");
        }
    }

    private void callSuccess(String response)
    {
        if(responseDelegate != null)
        {
            responseDelegate.onSuccess(response);
        }
    }

    private void callFail(XError error){
        if(responseDelegate != null)
        {
            responseDelegate.onFailHttpRequest(error);
        }
    }
}
