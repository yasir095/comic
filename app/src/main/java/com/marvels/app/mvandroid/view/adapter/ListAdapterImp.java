package com.marvels.app.mvandroid.view.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by yasirmahmood.
 */

public abstract class ListAdapterImp<T> extends BaseAdapter implements ListAdapter<T>
{
    protected ArrayList<T> arrayList;

    @Override
    public int getCount()
    {
        return arrayList.size();
    }

    @Override
    public Object getItem(int index)
    {
        return arrayList.get(index);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        return this.getAdapterView(position, convertView, parent);
    }

    public abstract View getAdapterView(int position, View convertView, ViewGroup parent);
}
