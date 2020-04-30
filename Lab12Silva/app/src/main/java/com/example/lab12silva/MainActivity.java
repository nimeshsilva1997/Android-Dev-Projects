package com.example.lab12silva;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.os.Bundle;
import android.util.Log;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import android.os.AsyncTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void onStart() {
        super.onStart();
        new HttpRequestTask().execute();
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, Payload> {

        @Override
        protected Payload doInBackground(Void... params) {
            try {
                final String url = "https://httpbin.org/get";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Payload greeting = restTemplate.getForObject(url, Payload.class);
                return greeting;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Payload greeting) {
            TextView args = (TextView) findViewById(R.id.args);
            TextView headers = (TextView) findViewById(R.id.headers);
            TextView origin = (TextView) findViewById(R.id.origin);
            TextView url = (TextView) findViewById(R.id.url);
            args.setText(greeting.getArgs().toString());
            headers.setText(greeting.getArgs().toString());
            origin.setText(greeting.getOrigin());
            url.setText(greeting.getUrl());
        }
    }
}
