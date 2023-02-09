package com.example.asynctaskhomework;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {
    private WeakReference<TextView> mTextView;
    private WeakReference<ProgressBar> mProgressBar;

    SimpleAsyncTask(TextView tv, ProgressBar progressBar) {
        mTextView = new WeakReference<>(tv);
        mProgressBar = new WeakReference<>(progressBar);
    }

    @Override
    protected String doInBackground(Void... voids) {
        Random r = new Random();
        int n = r.nextInt(11);
        int s = n * 200;

        int chunkSize = s/5;

        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(chunkSize);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress(((i + 1)*100)/5);
        }

        return "Awake at last after sleep for " + s + " miliseconds!";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        mProgressBar.get().setProgress(values[0]);
    }

    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }
}
