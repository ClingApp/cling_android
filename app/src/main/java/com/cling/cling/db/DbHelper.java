package com.cling.cling.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by igor on 22.01.2015.
 */
public class DbHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME_GOODS = "goods";
    public static final String TABLE_COLUMN_ID = "id";
    public static final String TABLE_COLUMN_TITLE = "title";
    public static final String TABLE_COLUMN_PRICE = "price";
    public static final String TABLE_COLUMN_DESCRIPTION = "description";
    public static final String TABLE_COLUMN_TIMESTAMP = "timestamp";
    public static final String TABLE_COLUMN_SELLER_ID = "seller_id";
    public static final String TABLE_COLUMN_PHOTO = "photo";

    public static final String CREATE_TABLE_GOODS =
            "CREATE TABLE " + TABLE_NAME_GOODS + " (" +
            TABLE_COLUMN_ID + " INTEGER NOT NULL," +
            TABLE_COLUMN_TITLE + " VARCHAR(64) NOT NULL," +
            TABLE_COLUMN_PRICE + " INTEGER NOT NULL," +
            TABLE_COLUMN_DESCRIPTION + " TEXT," +
            TABLE_COLUMN_TIMESTAMP + " DATETIME," +
            TABLE_COLUMN_SELLER_ID + " INTEGER," +
            TABLE_COLUMN_PHOTO + " VARCHAR(64)," +
            "PRIMARY KEY (id)," +
            "FOREIGN KEY(seller_id) REFERENCES users (id) )";

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "clingDB", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE_GOODS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
