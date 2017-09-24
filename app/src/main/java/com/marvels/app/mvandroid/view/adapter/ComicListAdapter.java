package com.marvels.app.mvandroid.view.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.marvels.app.mvandroid.R;
import com.marvels.app.mvandroid.core.helper.Logger;
import com.marvels.app.mvandroid.core.model.Comic;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by yasirmahmood on 12/06/2017.
 * should display title and image.
 * details page should display title, description, page count, price and authors of the comic
 */

public class ComicListAdapter extends ListAdapterImp<Comic>
{
    private Activity context;
    private ViewHolder viewHolder;

    public ComicListAdapter(Activity context) {
        this.context = context;
        this.arrayList = new ArrayList<>();
    }

    @Override
    public void setData(ArrayList<Comic> comics) {
        this.arrayList.addAll(comics);
    }

    @Override
    public void resetData() {
        this.arrayList.clear();
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }


    @Override
    public Object getItem(int index)
    {
        try
        {
            return this.arrayList.get(index);
        }
        catch(Exception e)
        {
            //@todo log error
        }

        return null;
    }

    @Override
    public View getAdapterView(int position, View convertView, ViewGroup parent)
    {
        if(convertView == null)
        {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(R.layout.list_cell, null);

            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.txtComicTitle);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.imgThumbnail);


            convertView.setTag(viewHolder);

        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.title.setText(this.arrayList.get(position).getTitle());

        if(this.arrayList.get(position).getThumbnail() != null)
        {
            Logger.logSuccess("| "+this.arrayList.get(position).getThumbnail());
            Picasso.with(context).load(this.arrayList.get(position).getThumbnail()).into(viewHolder.image);
            //Broker.getDefaultImageCache(0).loadBitmap(context, this.arrayListComic.get(position).getThumbnail() , viewHolder.image);
        }

        return convertView;
    }

    static class ViewHolder {

        public TextView title;
        public ImageView image;
    }
}
