package parvez.vip.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import parvez.vip.data.helper._Database;

/**
 * Project: Data
 * Created by Muhammad Harun-Or-Roshid
 * Email: md.parvez28@gmail.com
 * Web: https://parvez.vip
 * On 19 August 2018 at 12:47 AM
 */
public class _DB {
    private static SQLiteOpenHelper sqLiteOpenHelper;
    private static SQLiteDatabase sqLiteDatabase;
    private static final String TAG = "_DB";

    private _DB() {

    }

    private static void getInstance(Context context) {
        sqLiteOpenHelper = new _Database(context);

    }

    private static void dbOpen() {
        sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
        Log.d(TAG, "DATABASE OPENED");
    }

    private static void dbClose() {
        sqLiteOpenHelper.close();
        Log.d(TAG, "DATABASE CLOSED");
    }

    public static boolean save(Context context, String key, String data) {
        getInstance(context);
        dbOpen();
        ContentValues contentValues = new ContentValues();
        contentValues.put(_Database.COLUMNS[0], key);
        contentValues.put(_Database.COLUMNS[1], data);
        if (!isAvailable(key)) {
            boolean isInserted = sqLiteDatabase.insert(_Database.TABLE_NAME, null, contentValues) > 0;
            dbClose();
            return isInserted;
        } else {
            boolean isUpdated = sqLiteDatabase.update(_Database.TABLE_NAME, contentValues, _Database.COLUMNS[0] + "=" + "\"" + key + "\"", null) > 0;
            dbClose();
            return isUpdated;
        }
    }

    public static String load(Context context, String key) {
        getInstance(context);
        dbOpen();
        if (isAvailable(key)) {
            Cursor cursor = sqLiteDatabase.query(_Database.TABLE_NAME, _Database.COLUMNS, _Database.COLUMNS[0] + "=" + "\"" + key + "\"", null, null, null, null);
            if (cursor.getCount() > 0) {
                String data = null;
                while (cursor.moveToNext()) {
                    data = cursor.getString(cursor.getColumnIndex(_Database.COLUMNS[1]));
                    Log.d(TAG, data);
                }
                dbClose();
                return data;
            }
        }
        dbClose();
        return null;

    }

    private static boolean isAvailable(String key) {
        String where = "variable=" + "\"" + key + "\"";
        boolean isAvailable = sqLiteDatabase.query(_Database.TABLE_NAME, _Database.COLUMNS, where, null, null, null, null).getCount() > 0;
        Log.d(TAG, "Data Available: " + isAvailable);
        return isAvailable;
    }
}
