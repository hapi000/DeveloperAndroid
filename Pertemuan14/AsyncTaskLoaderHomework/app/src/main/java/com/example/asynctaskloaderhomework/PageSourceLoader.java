package com.example.asynctaskloaderhomework;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class PageSourceLoader extends AsyncTaskLoader<String> {
    private String mURL;
    private String mProtocol;

    public PageSourceLoader(@NonNull Context context, String protocol, String URL) {
        super(context);
        mProtocol = protocol;
        mURL = URL;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.getPageSource(mProtocol, mURL);
    }
}