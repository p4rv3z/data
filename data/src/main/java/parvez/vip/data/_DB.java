package parvez.vip.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import parvez.vip.data.helper._Crypto;
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
    private static final String TAG = "_Data_DB_";


    /**
     * constructor
     */
    private _DB() {

    }

    /**
     * Create database
     *
     * @param context
     */
    private static void getInstance(Context context) {
        if (sqLiteOpenHelper == null) {
            sqLiteOpenHelper = new _Database(context);
        }
    }


    /**
     * open database
     */
    private static void dbOpen() {
        sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
        Log.d(TAG, "DATABASE OPENED");
    }


    /**
     * close database
     */
    private static void dbClose() {
        sqLiteOpenHelper.close();
        Log.d(TAG, "DATABASE CLOSED");
    }


    /**
     * check variable is available or not
     *
     * @param key
     * @return
     */
    private static boolean isAvailable(String key) {
        String where = _Database.COLUMNS[0] + "=" + "\"" + key + "\"";
        boolean isAvailable = sqLiteDatabase.query(_Database.TABLE_NAME, _Database.COLUMNS, where, null, null, null, null, "1").getCount() > 0;
        Log.d(TAG, "Data Available: " + isAvailable);
        return isAvailable;
    }







    /*========================Save and Load String========================*/

    /**
     * Save String type of data in SQLite Database
     *
     * @param context passing your current activity or context
     * @param data    your String value(Data can be null)
     * @param key     The name of your data
     * @return true if successfully save data
     */
    public static boolean saveString(@NonNull Context context, String data, @NonNull String key) {
        boolean isInsertedOrUpdated;
        getInstance(context);
        dbOpen();
        ContentValues contentValues = new ContentValues();
        contentValues.put(_Database.COLUMNS[0], key);
        contentValues.put(_Database.COLUMNS[1], data);
        String currentTimeStamp = "0000-00-00 00:00:00";
        try {
            currentTimeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Timestamp(new Date().getTime()));
        } catch (Exception e) {
            Log.e(TAG, "can't update current time.");
        }
        if (!isAvailable(key)) {
            //insert in database
            contentValues.put(_Database.COLUMNS[2], currentTimeStamp);
            isInsertedOrUpdated = sqLiteDatabase.insert(_Database.TABLE_NAME, null, contentValues) > 0;
        } else {
            //update in database
            contentValues.put(_Database.COLUMNS[3], currentTimeStamp);
            isInsertedOrUpdated = sqLiteDatabase.update(_Database.TABLE_NAME, contentValues, _Database.COLUMNS[0] + "=" + "\"" + key + "\"", null) > 0;
        }
        dbClose();
        return isInsertedOrUpdated;
    }

    /**
     * Save String type of data in SQLite Database
     *
     * @param context  passing your current activity or context
     * @param data     your String value(Data can be null)
     * @param key      The name of your data
     * @param password encrypted your data with your password
     * @return true if successfully save data
     */
    public static boolean saveString(@NonNull Context context, String data, @NonNull String key, @Nullable String password) {
        data = _Crypto.encrypt(data, password);
        return saveString(context, data, key);
    }


    /**
     * Load String type of value
     *
     * @param context passing your current activity or context
     * @param key     The name of your data
     * @return String data (Default value null)
     */
    public static String loadString(@NonNull Context context, @NonNull String key) {
        String data = null;
        getInstance(context);
        dbOpen();
        if (isAvailable(key)) {
            Cursor cursor = sqLiteDatabase.query(_Database.TABLE_NAME, _Database.COLUMNS, _Database.COLUMNS[0] + "=" + "\"" + key + "\"", null, null, null, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    data = cursor.getString(cursor.getColumnIndex(_Database.COLUMNS[1]));
                    Log.d(TAG, data);
                }
            }
        }
        dbClose();
        return data;
    }

    /**
     * Load String type of value
     *
     * @param context  passing your current activity or context
     * @param key      The name of your data
     * @param password decrypted your data with your password
     * @return Decrypted String data (Default value null)
     */
    public static String loadString(@NonNull Context context, @NonNull String key, @Nullable String password) {
        String enData = loadString(context, key);
        String deData = _Crypto.decrypt(enData, password);
        return deData;
    }

    /*========================End String========================*/



    /*========================Save and Load Integer========================*/

    /**
     * Save Integer type of data in SQLite Database
     *
     * @param context passing your current activity or context
     * @param data    your Integer value
     * @param key     The name of your data
     * @return true if successfully save data
     */
    public static boolean saveInt(@NonNull Context context, int data, @NonNull String key) {
        return saveString(context, String.valueOf(data), key);
    }

    /**
     * Save Integer type of data in SQLite Database
     *
     * @param context  passing your current activity or context
     * @param data     your Integer value
     * @param key      The name of your data
     * @param password encrypted your data with your password
     * @return true if successfully save data
     */
    public static boolean saveInt(@NonNull Context context, int data, @NonNull String key, @Nullable String password) {
        return saveString(context, String.valueOf(data), key, password);
    }


    /**
     * Load Integer type of data.
     *
     * @param context passing your current activity or context
     * @param key     The name of your data
     * @return Integer value (Default value 0)
     */
    public static int loadInt(@NonNull Context context, @NonNull String key) {
        return loadInt(context, key, 0);
    }

    /**
     * Load Integer type of data.
     *
     * @param context      passing your current activity or context
     * @param key          The name of your data
     * @param defaultValue if data not found return default value
     * @return Integer value
     */
    public static int loadInt(@NonNull Context context, @NonNull String key, int defaultValue) {
        String tmpData = loadString(context, key);
        if (tmpData != null) {
            try {
                return Integer.valueOf(tmpData);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
        return defaultValue;
    }

    /**
     * Load Integer type of data.
     *
     * @param context  passing your current activity or context
     * @param key      The name of your data
     * @param password encrypted your data with your password
     * @return Integer value (Default value 0)
     */
    public static int loadInt(@NonNull Context context, @NonNull String key, @Nullable String password) {
        return loadInt(context, key, password, 0);
    }


    /**
     * Load Integer type of data.
     *
     * @param context      passing your current activity or context
     * @param key          The name of your data
     * @param password     encrypted your data with your password
     * @param defaultValue if data not found return default value
     * @return Integer value
     */
    public static int loadInt(@NonNull Context context, @NonNull String key, @Nullable String password, int defaultValue) {
        String data = loadString(context, key, password);
        if (data != null) {
            try {
                return Integer.valueOf(data);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
        return defaultValue;

    }

    /*========================End Integer========================*/



    /*========================Save and Load Boolean========================*/

    /**
     * Save Boolean type of data in SQLite Database
     *
     * @param context passing your current activity or context
     * @param data    your Boolean value
     * @param key     The name of your data
     * @return true if successfully save data
     */
    public static boolean saveBoolean(@NonNull Context context, boolean data, @NonNull String key) {
        return saveString(context, String.valueOf(data), key);
    }

    /**
     * Save Boolean type of data in SQLite Database
     *
     * @param context  passing your current activity or context
     * @param data     your Boolean value
     * @param key      The name of your data
     * @param password encrypted your data with your password
     * @return true if successfully save data
     */
    public static boolean saveBoolean(@NonNull Context context, boolean data, @NonNull String key, @Nullable String password) {
        return saveString(context, String.valueOf(data), key, password);
    }


    /**
     * Load Boolean type of data.
     *
     * @param context passing your current activity or context
     * @param key     The name of your data
     * @return Boolean value (Default value false)
     */
    public static boolean loadBoolean(@NonNull Context context, @NonNull String key) {
        return loadBoolean(context, key, false);
    }

    /**
     * Load Boolean type of data.
     *
     * @param context      passing your current activity or context
     * @param key          The name of your data
     * @param defaultValue if data not found return default value
     * @return Boolean value
     */
    public static boolean loadBoolean(@NonNull Context context, @NonNull String key, boolean defaultValue) {
        String tmpData = loadString(context, key);
        if (tmpData != null) {
            try {
                return Boolean.valueOf(tmpData);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
        return defaultValue;
    }

    /**
     * Load Boolean type of data.
     *
     * @param context  passing your current activity or context
     * @param key      The name of your data
     * @param password encrypted your data with your password
     * @return Boolean value (Default value false)
     */
    public static boolean loadBoolean(@NonNull Context context, @NonNull String key, @Nullable String password) {
        return loadBoolean(context, key, password, false);
    }


    /**
     * Load Boolean type of data.
     *
     * @param context      passing your current activity or context
     * @param key          The name of your data
     * @param password     encrypted your data with your password
     * @param defaultValue if data not found return default value
     * @return Boolean value
     */
    public static boolean loadBoolean(@NonNull Context context, @NonNull String key, @Nullable String password, boolean defaultValue) {
        String data = loadString(context, key, password);
        if (data != null) {
            try {
                return Boolean.valueOf(data);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
        return defaultValue;

    }

    /*========================End Boolean========================*/



    /*========================Save and Load Float========================*/

    /**
     * Save Float type of data in SQLite Database
     *
     * @param context passing your current activity or context
     * @param data    your Float value
     * @param key     The name of your data
     * @return true if successfully save data
     */
    public static boolean saveFloat(@NonNull Context context, float data, @NonNull String key) {
        return saveString(context, String.valueOf(data), key);
    }

    /**
     * Save Float type of data in SQLite Database
     *
     * @param context  passing your current activity or context
     * @param data     your Float value
     * @param key      The name of your data
     * @param password encrypted your data with your password
     * @return true if successfully save data
     */
    public static boolean saveFloat(@NonNull Context context, float data, @NonNull String key, @Nullable String password) {
        return saveString(context, String.valueOf(data), key, password);
    }


    /**
     * Load Float type of data.
     *
     * @param context passing your current activity or context
     * @param key     The name of your data
     * @return Float value (Default value 0)
     */
    public static float loadFloat(@NonNull Context context, @NonNull String key) {
        return loadFloat(context, key, 0);
    }

    /**
     * Load Float type of data.
     *
     * @param context      passing your current activity or context
     * @param key          The name of your data
     * @param defaultValue if data not found return default value
     * @return Float value
     */
    public static float loadFloat(@NonNull Context context, @NonNull String key, float defaultValue) {
        String tmpData = loadString(context, key);
        if (tmpData != null) {
            try {
                return Float.valueOf(tmpData);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
        return defaultValue;
    }

    /**
     * Load Float type of data.
     *
     * @param context  passing your current activity or context
     * @param key      The name of your data
     * @param password encrypted your data with your password
     * @return Float value (Default value 0)
     */
    public static float loadFloat(@NonNull Context context, @NonNull String key, @Nullable String password) {
        return loadFloat(context, key, password, 0);
    }


    /**
     * Load Float type of data.
     *
     * @param context      passing your current activity or context
     * @param key          The name of your data
     * @param password     encrypted your data with your password
     * @param defaultValue if data not found return default value
     * @return Float value
     */
    public static float loadFloat(@NonNull Context context, @NonNull String key, @Nullable String password, float defaultValue) {
        String data = loadString(context, key, password);
        if (data != null) {
            try {
                return Float.valueOf(data);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
        return defaultValue;

    }

    /*========================End Float========================*/





    /*========================Save and Load Long========================*/

    /**
     * Save Long type of data in SQLite Database
     *
     * @param context passing your current activity or context
     * @param data    your Long value
     * @param key     The name of your data
     * @return true if successfully save data
     */
    public static boolean saveLong(@NonNull Context context, long data, @NonNull String key) {
        return saveString(context, String.valueOf(data), key);
    }

    /**
     * Save Long type of data in SQLite Database
     *
     * @param context  passing your current activity or context
     * @param data     your Long value
     * @param key      The name of your data
     * @param password encrypted your data with your password
     * @return true if successfully save data
     */
    public static boolean saveLong(@NonNull Context context, long data, @NonNull String key, @Nullable String password) {
        return saveString(context, String.valueOf(data), key, password);
    }


    /**
     * Load Long type of data.
     *
     * @param context passing your current activity or context
     * @param key     The name of your data
     * @return Long value (Default value 0)
     */
    public static long loadLong(@NonNull Context context, @NonNull String key) {
        return loadLong(context, key, 0);
    }

    /**
     * Load Long type of data.
     *
     * @param context      passing your current activity or context
     * @param key          The name of your data
     * @param defaultValue if data not found return default value
     * @return Long value
     */
    public static long loadLong(@NonNull Context context, @NonNull String key, long defaultValue) {
        String tmpData = loadString(context, key);
        if (tmpData != null) {
            try {
                return Long.valueOf(tmpData);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
        return defaultValue;
    }

    /**
     * Load Long type of data.
     *
     * @param context  passing your current activity or context
     * @param key      The name of your data
     * @param password encrypted your data with your password
     * @return Long value (Default value 0)
     */
    public static long loadLong(@NonNull Context context, @NonNull String key, @Nullable String password) {
        return loadLong(context, key, password, 0);
    }


    /**
     * Load Long type of data.
     *
     * @param context      passing your current activity or context
     * @param key          The name of your data
     * @param password     encrypted your data with your password
     * @param defaultValue if data not found return default value
     * @return Long value
     */
    public static long loadLong(@NonNull Context context, @NonNull String key, @Nullable String password, long defaultValue) {
        String data = loadString(context, key, password);
        if (data != null) {
            try {
                return Long.valueOf(data);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
        return defaultValue;

    }

    /*========================End Long========================*/
}