package com.axionteq.newsapp.detail;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.axionteq.newsapp.R;

public class DetailActivity extends AppCompatActivity implements WebView.FindListener {

    Toolbar toolbar;
    String strTitle, strTechName, strUrl;
    WebView webView;
    TextView tvTech, tvTitle;
    ImageView imageView;

    //testing-purpose
    public static String strurl = "https://web.whatsapp.com";
    public static String key_url = "web";

    ProgressBar progressBar;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_detail );
        webView = findViewById( R.id.wv );
        progressBar = findViewById( R.id.pb );
        toolbar = findViewById( R.id.tb_ad );

        strTitle = getIntent().getStringExtra( "title" );
        strUrl = getIntent().getStringExtra( "url" );
        strTechName = getIntent().getStringExtra( "techname" );

        setSupportActionBar( toolbar );
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled( true );
            getSupportActionBar().setDisplayShowHomeEnabled( true );
            getSupportActionBar().setHomeAsUpIndicator( R.drawable.ic_close_white_24dp );
            getSupportActionBar().setTitle( strTitle );
            getSupportActionBar().setSubtitle( strTechName );
        }

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled( true );
        webView.loadUrl( strUrl );

        new ProgressTask().execute();
    }

    @Override
    public void onFindResultReceived(int i, int i1, boolean b) {
    }

    @SuppressLint("StaticFieldLeak")
    public class ProgressTask extends AsyncTask<Void, Integer, Void> {
        int progressCount;

        @SuppressLint("WrongThread")
        @Override
        protected Void doInBackground(Void... voids) {
            while (progressCount < 100) {

                progressCount++;
                publishProgress( progressCount );
                SystemClock.sleep( 20 );
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setProgress( 0 );
            progressCount = 0;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate( values );
            progressBar.setVisibility( View.VISIBLE );
            progressBar.setProgress( values[0] );
        }

    }
}
