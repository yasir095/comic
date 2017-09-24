package com.marvels.app.mvandroid.view.adapter;

import java.util.ArrayList;

/**
 * Created by yasirmahmood on 24/09/2017.
 */

public interface ListAdapter<T> {
    void setData(ArrayList<T> data);
    void resetData();
}
