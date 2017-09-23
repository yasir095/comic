package com.marvels.app.mvandroid.core.resultset;

import com.marvels.app.mvandroid.App;
import com.marvels.app.mvandroid.core.model.Comic;

import java.util.ArrayList;

/**
 * Created by yasirmahmood on 12/06/2017.
 */

public class ComicResultSet extends ResultSet<ArrayList<Comic>>
{
    private ArrayList<Comic> comics = new ArrayList<>();

    public ComicResultSet() {
        App.getInstance().getComponent().inject(this);
    }

    @Override
    protected void setParsedData(ArrayList<Comic> parsedData)
    {
        this.comics.addAll(parsedData);
    }

    @Override
    public ArrayList<Comic> getParsedData()
    {
        return this.comics;
    }
}