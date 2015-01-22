package com.cling.cling.Rest;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

import org.json.JSONException;

import java.io.IOException;


public class RestService extends IntentService {

    private static final String LOG_TAG = "RestService";

    private static final String API_URL = "http://188.166.6.241/api/";

    private Network network;

    public RestService() {
        super("RestService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {

            network = new Network();

            Processor processor = new Processor(getBaseContext());
            try {
                processor.request(intent, network);
            } catch (JSONException e) {
                e.printStackTrace();
                ResultReceiver receiver = intent.getParcelableExtra(ServiceHelper.RECEIVER);
                final Bundle data = new Bundle();
                data.putString("ERROR","no connection");
                receiver.send(RestConsts.STATUS_ERROR, data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
