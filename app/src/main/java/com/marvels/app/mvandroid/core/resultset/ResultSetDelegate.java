package com.marvels.app.mvandroid.core.resultset;

/**
 * Created by yasirmahmood on 12/06/2017.
 */

public interface ResultSetDelegate
{
    void onCompleteResult(ResultSet resultSet);
    void onFailResult(ResultSet resultSet, int statusCode);
}
