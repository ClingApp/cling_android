package com.cling.cling.Rest;

import android.content.Context;
import android.content.Intent;

import com.cling.cling.Models.Product;
/**
 * Created by igor on 21.01.2015.
 */
public class ServiceHelper {

    private Context context;

    public ServiceHelper(Context context) {
        this.context = context;
    }

    void getGood(int id) {
        Intent intent = new Intent(context, RestService.class);
        intent.putExtra(RestConsts.METHOD_NAME.toString(), RestConsts.GET);
        intent.putExtra(RestConsts.SUBJECT.toString(), RestConsts.PRODUCT);
        intent.putExtra(RestConsts.PARAMS.toString(), id);

        context.startService(intent);
    }

    void getGoodsForId(int id) {
        Intent intent = new Intent(context, RestService.class);
        intent.putExtra(RestConsts.METHOD_NAME.toString(), RestConsts.GET);
        intent.putExtra(RestConsts.SUBJECT.toString(), RestConsts.PRODUCT);
        intent.putExtra(RestConsts.PARAMS.toString(), "user_id=" + id);

        context.startService(intent);
    }

    void addGood(Product product) {
        Intent intent = new Intent(context, RestService.class);
        intent.putExtra(RestConsts.METHOD_NAME.toString(), RestConsts.POST);
        intent.putExtra(RestConsts.SUBJECT.toString(), RestConsts.PRODUCT);
        intent.putExtra(RestConsts.PARAMS.toString(), product.name);

        context.startService(intent);
    }


    void getUser(int id) {
        Intent intent = new Intent(context, RestService.class);
        intent.putExtra(RestConsts.METHOD_NAME.toString(), RestConsts.GET);
        intent.putExtra(RestConsts.SUBJECT.toString(), RestConsts.USER);
        intent.putExtra(RestConsts.PARAMS.toString(), id);

        context.startService(intent);
    }

    void updateUser(int id) {

    }


    void getFeed() {
        Intent intent = new Intent(context, RestService.class);
        intent.putExtra(RestConsts.METHOD_NAME.toString(), RestConsts.GET);
        intent.putExtra(RestConsts.SUBJECT.toString(), RestConsts.FEED);

        context.startService(intent);
    }
}
