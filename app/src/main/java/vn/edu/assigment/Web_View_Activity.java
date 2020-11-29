package vn.edu.assigment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Web_View_Activity extends AppCompatActivity {
WebView wvData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web__view_);
        wvData=findViewById(R.id.wvData);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String lk = bundle.getString("link");

            wvData.loadUrl(lk);
            wvData.setWebViewClient(new WebViewClient());
        }


    }
}
