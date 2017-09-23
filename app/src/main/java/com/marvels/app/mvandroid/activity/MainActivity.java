package com.marvels.app.mvandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.marvels.app.mvandroid.R;

public class MainActivity extends AppCompatActivity implements MainActivityPresenter.View {

    private MainActivityPresenter activityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        activityPresenter = new MainActivityPresenter(this);
    }

    @Override
    public void showErrorDialog() {

    }

    @Override
    public void updateListAdapter() {

    }

    @Override
    public void showActionBar() {

    }

    @Override
    public void hideActionBar() {

    }
}
