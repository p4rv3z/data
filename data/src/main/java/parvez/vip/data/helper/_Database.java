package parvez.vip.data.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Project: Data
 * Created by Muhammad Harun-Or-Roshid
 * Email: md.parvez28@gmail.com
 * Web: https://parvez.vip
 * On 23 August 2018 at 2:55 AM
 */
public class _Database extends SQLiteOpenHelper {

    private static final String TAG = "_DB";

    private static final String DATABASE_NAME = "data.sqlite";
    public static final String TABLE_NAME = "data";
    public static final String[] COLUMNS = {"variable", "data", "created_at", "updated_at"};
    private static final int DATABASE_VERSION = 1;


    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + COLUMNS[0] + " varchar(100) NOT NULL, " + COLUMNS[1] + " text NOT NULL, " + COLUMNS[2] + " timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP, " + COLUMNS[3] + " timestamp NOT NULL DEFAULT '0000-00-00 00:00:00')";

    public _Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Log.d(TAG, "Database created.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        Log.d(TAG, "Previous table has been deleted.");
        onCreate(db);
    }
}