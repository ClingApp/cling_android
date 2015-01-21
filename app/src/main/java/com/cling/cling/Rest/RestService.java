package com.cling.cling.Rest;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RestService extends IntentService {

    private static final String LOG_TAG = "RestService";

    private static final String API_URL = "http://188.166.6.241/api/";

    public RestService() {
        super("RestService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            String method = intent.getExtras().getString(RestConsts.METHOD_NAME.toString());

            if (RestConsts.GET.name().equals(method)) {
                sendGet(intent);
            } else if (RestConsts.POST.equals(method)) {
                sendPost(intent);
            }
        }
    }

    private void sendGet(Intent intent) {

        HttpURLConnection connection = null;

        try {
            URL url = new URL(API_URL +
                    intent.getExtras().getString(RestConsts.SUBJECT.apiName()) +
                    intent.getExtras().get(RestConsts.PARAMS.toString()));

            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream is = connection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }

            String dataAsString = sb.toString();
            Log.v(LOG_TAG, dataAsString);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendPost(Intent intent) {

    }
}
