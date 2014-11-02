package com.cling.cling.Utilities;

import android.util.Log;
import android.widget.Toast;

import com.cling.cling.ClingApp;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

/**
 * Created by Tier on 02.11.14.
 */

public class NetworkManager {

    public static final String BASE_URL = "http://cling.com/";
    public static final String API_PATH = "api_path/";

    private static AsyncHttpClient client = new AsyncHttpClient();


    /* Basic methods */

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        Log.i("GET URL", getRequestUrl(url));
        client.get(getRequestUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        Log.i("POST URL", getRequestUrl(url));
        client.post(getRequestUrl(url), params, responseHandler);
    }

    private static String getRequestUrl(String methodUrl) {

        return BASE_URL + API_PATH + methodUrl;
    }


    //add this method in every onFailure callback
    public static String parseErrors(Object errorsObject, boolean showMessage) {

        String message = null;

        if (errorsObject != null) {

            if (errorsObject instanceof JSONArray) {

                message = errorsObject.toString();

            } else if (errorsObject instanceof String) {

                message = (String) errorsObject;
            }

            if (showMessage) {
                Toast.makeText(ClingApp.getAppContext(), message, Toast.LENGTH_SHORT).show();
            }
        }

        return message;
    }


    /* Api methods */

    //TODO: .....
}
