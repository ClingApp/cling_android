package com.cling.cling.Rest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;

//import com.beautyteam.smartkettle.LoginActivity;
//import com.beautyteam.smartkettle.MainActivity;

import com.cling.cling.Fragments.ProductFragment;
import com.cling.cling.MainActivity;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class Processor {
    private String url = "http://188.166.6.241/";
    private JSONObject urlparametres;
    private JSONObject json = null;
    String action = "";
    private String title;
    private Context context;

    public Processor(Context _context) {
        context = _context;
    }

    public void request(Intent intent, Network network) throws JSONException, IOException {
        urlparametres = new JSONObject();
        if (intent != null) {
            String method = intent.getExtras().getString(RestConsts.METHOD_NAME.toString());

            if (RestConsts.GET.toString().equals(method)) {
                String subject = intent.getStringExtra(RestConsts.SUBJECT.toString());
                String params = intent.getStringExtra(RestConsts.PARAMS.toString());
                url += "api/";
                url += subject;
                url += "/";
                url += params;
                Log.v("url", url);
                json = new JSONObject(network.urlConnectionGet(url));
                ResultReceiver receiver = intent.getParcelableExtra(ServiceHelper.RECEIVER);
                final Bundle data = new Bundle();
                Log.v("json", json.toString());
                title = json.getJSONObject("good").getString("title");
                data.putString("title", title);
                receiver.send(RestConsts.STATUS_OK, data);
            }



//
//            if (JsonParser.ACTION_LOGIN.equals(action)||JsonParser.ACTION_REGISTER.equals(action)) {
//                String login = intent.getStringExtra(LoginActivity.LOGIN);
//                String password = intent.getStringExtra(LoginActivity.PASS);
//                if (JsonParser.ACTION_LOGIN.equals(action))
//                    url += "api/owners/login/";
//                else
//                    url += "api/owners/register/";
//                urlparametres.put("username", login);
//                urlparametres.put("password", password);
//                json = new JSONObject(network.urlConnectionPost(url, urlparametres.toString()));
//                ResultReceiver receiver = intent.getParcelableExtra(LoginActivity.RECEIVER);
//                final Bundle data = new Bundle();
//                if (!json.toString().contains("error")) {
//                    idOwner = json.getInt("owner_key");
//                    data.putInt("RECEIVER_DATA", idOwner);
//                    receiver.send(1, data);
//                    Log.d("json", "id to activity");
//                }
//                else {
//                    data.putString("ERROR",json.get("error").toString());
//                    receiver.send(LoginActivity.STATUS_ERROR, data);
//                }
//            } else if (JsonParser.ACTION_ADDING_DEVICE.equals(action)) {
//                int idOwner = intent.getIntExtra(MainActivity.OWNER, 0);
//                int idDevice = intent.getIntExtra(MainActivity.ID_DEVICE, 0);
//                String title = intent.getStringExtra(MainActivity.DEVICE_TITLE);
//                url += "api/devices/add/";
//                urlparametres.put("owner", idOwner);
//                urlparametres.put("title", title);
//                urlparametres.put("device", idDevice);
//                json = new JSONObject(network.urlConnectionPost(url, urlparametres.toString()));
//            } else if (JsonParser.ACTION_REMOVE_DEVICE.equals(action)) {
//                int idOwner = intent.getIntExtra(MainActivity.OWNER, 0);
//                int idDevice = intent.getIntExtra(MainActivity.ID_DEVICE, 0);
//                url += "api/devices/remove/?owner=" + idOwner + "&device=" + idDevice;
//            } else if (JsonParser.ACTION_ADDING_EVENTS.equals(action)) {
//                int idOwner = intent.getIntExtra(MainActivity.OWNER, 0);
//                int idDevice = intent.getIntExtra(MainActivity.ID_DEVICE, 0);
//                String eventDateBegin = intent.getStringExtra(MainActivity.EVENT_DATE_BEGIN);
//                int temperature = intent.getIntExtra(MainActivity.TEMPERATURE, 0);
//                url += "api/events/add/";
//                urlparametres.put("owner", idOwner);
//                urlparametres.put("device", idDevice);
//                urlparametres.put("event_date_begin", eventDateBegin);
//                urlparametres.put("temperature", temperature);
//                json = new JSONObject(network.urlConnectionPost(url, urlparametres.toString()));
//            } else if (JsonParser.ACTION_ADDING_MORE_EVENTS_INFO.equals(action)) {
//                int idOwner = intent.getIntExtra(MainActivity.OWNER, 0);
//                int idPage = intent.getIntExtra(MainActivity.ID_PAGE, 0);
//                url += "api/events/more/?owner=" + idOwner + "&page=" + idPage;
//                json = new JSONObject(network.urlConnectionGet(url));
//            } else if (JsonParser.ACTION_ENDED_EVENTS.equals(action)) {
//                int idOwner = intent.getIntExtra(MainActivity.OWNER, 0);
//                int idDevice = intent.getIntExtra(MainActivity.ID_DEVICE, 0);
//                int idEvent = intent.getIntExtra(MainActivity.ID_EVENT, 0);
//                url += "api/events/ended/?owner=" + idOwner + "&device=" + idDevice + "&event=" + idEvent;
//                json = new JSONObject(network.urlConnectionGet(url));
//            } else if (JsonParser.ACTION_ADDING_MORE_DEVICES_INFO.equals(action)) {
//                int idOwner = intent.getIntExtra(MainActivity.OWNER, 0);
//                int idDevice = intent.getIntExtra(MainActivity.ID_DEVICE, 0);
//                int idPage = intent.getIntExtra(MainActivity.ID_PAGE, 0);
//                url += "api/devices/about/more/?owner=" + idOwner + "&device=" + idDevice + "&page=" + idPage;
//                json = new JSONObject(network.urlConnectionGet(url));
//            }
        }
//        JsonParser jsonParser = new JsonParser(context);
//        jsonParser.jsonToContentProvider(action, json);
    }

}
