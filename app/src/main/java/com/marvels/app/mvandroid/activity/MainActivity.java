package com.marvels.app.mvandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.marvels.app.mvandroid.R;
import com.marvels.app.mvandroid.core.resultset.ComicResultSet;
import com.marvels.app.mvandroid.view.adapter.ComicListAdapter;

public class MainActivity extends AppCompatActivity implements MainActivityPresenter.View {

    private MainActivityPresenter activityPresenter;
    private ComicListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.list_view);

        activityPresenter = new MainActivityPresenter(this);

        this.listAdapter = new ComicListAdapter(this);
        listView.setAdapter(listAdapter);
    }

    @Override
    public void showErrorDialog() {

    }

    @Override
    public void updateListAdapter(final ComicResultSet resultSet) {
//        runOnUiThread(()->{

//        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listAdapter.setData(resultSet.getParsedData());
                listAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void showActionBar() {

    }

    @Override
    public void hideActionBar() {

    }
}
