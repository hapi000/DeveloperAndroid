package com.example.asynctaskloaderhomework;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private TextView page_source_code;
    private Spinner protocol;
    private TextView host;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        page_source_code = findViewById(R.id.page_source_code);
        protocol = findViewById(R.id.spinner);
        host = findViewById(R.id.textView);

        if(getSupportLoaderManager().getLoader(0) != null){
            getSupportLoaderManager().initLoader(0,null,this);
        }
    }

    public void getSource(View view) {
        
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        if (inputManager != null ) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

        
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();

            Bundle queryBundle = new Bundle();
            queryBundle.putString("protocol", getResources().getStringArray(R.array.http_protocol)[protocol.getSelectedItemPosition()]);
            queryBundle.putString("host", host.getText().toString());

            getSupportLoaderManager().restartLoader(0, queryBundle, this);

            page_source_code.setText(R.string.loading);
        } else {
            page_source_code.setText(R.string.no_network);
        }
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        String protocol = "";
        String host = "";

        if (args != null) {
            protocol = args.getString("protocol");
            host = args.getString("host");
        }

        return new PageSourceLoader(this, protocol, host);
    }

    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        page_source_code.setText(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}