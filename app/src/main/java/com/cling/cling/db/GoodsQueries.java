package com.cling.cling.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cling.cling.Models.Product;

/**
 * Created by igor on 22.01.2015.
 */
public class GoodsQueries {

    private Context context;
    private DbHelper dbHelper;
    private SQLiteDatabase db;

    public GoodsQueries(Context context) {
        this.context = context;
        dbHelper = new DbHelper(context, "clingDB", null, 1);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        if (dbHelper != null)
            dbHelper.close();
    }

    public void insertGood(Product product) {

        ContentValues cv = new ContentValues();
        cv.put(DbHelper.TABLE_COLUMN_ID, product.id);
        cv.put(DbHelper.TABLE_COLUMN_TITLE, product.title);
        cv.put(DbHelper.TABLE_COLUMN_DESCRIPTION, product.description);
        cv.put(DbHelper.TABLE_COLUMN_PRICE, product.price);
        cv.put(DbHelper.TABLE_COLUMN_SELLER_ID, product.seller_id.id);
        cv.put(DbHelper.TABLE_COLUMN_TIMESTAMP, product.date.toString());
        cv.put(DbHelper.TABLE_COLUMN_PHOTO, product.photo);

        db.update(DbHelper.TABLE_NAME_GOODS, cv, DbHelper.TABLE_COLUMN_ID + " =?",
                new String[]{Integer.toString(product.id)});
    }

    public Cursor getGoodById(int id) {
        String where = DbHelper.TABLE_COLUMN_ID + "=?";
        String[] selectionArgs = {Integer.toString(id)};
        Cursor cur = db.query(DbHelper.TABLE_NAME_GOODS, null, where, selectionArgs, null, null, null);
        return cur;
    }
}
