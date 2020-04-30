package com.axionteq.newsapp.detail;

import android.annotation.SuppressLint;
import android.content.Intent;
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

import com.axionteq.newsapp.MainActivity;
import com.axionteq.newsapp.R;

public class DetailActivity extends AppCompatActivity implements WebView.FindListener {

    Toolbar toolbar;
    String strTitle, strTechName;
    WebView webView;
    ProgressBar progressBar;
    TextView tvTech, tvTitle;
    ImageView imageView;

    public static String strUrl="https://web.whatsapp.com";
    public static String key_url="web";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_detail );
        webView = findViewById( R.id.wv );
        progressBar = findViewById( R.id.pb );

        tvTitle = findViewById( R.id.tv_title_ad );
        tvTech = findViewById( R.id.tv_name_ad );
        imageView = findViewById( R.id.iv_tb_ad );

        strTitle = getIntent().getStringExtra( "title" );
        strUrl = getIntent().getStringExtra( "url" );
        strTechName = getIntent().getStringExtra( "techname" );

        tvTech.setText( strTechName );
        tvTitle.setText( strTitle );

        progressBar.setVisibility( View.VISIBLE );

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled( true );
        webView.loadUrl( strUrl );

        new ProgressTask().execute();
    }

    @Override
    public void onFindResultReceived(int i, int i1, boolean b) {
        progressBar.setVisibility( View.GONE );
    }

    public void ImageView(View view) {
        Intent intent = new Intent(this, MainActivity.class );
        startActivity( intent );
    }

    @SuppressLint("StaticFieldLeak")
    public class ProgressTask extends AsyncTask<Void, Integer, Void> {
        int progressCount;

        @SuppressLint("WrongThread")
        @Override
        protected Void doInBackground(Void... voids) {
            while (progressCount<100) {
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
            progressBar.setProgress( values[0] );
        }

    }
}
