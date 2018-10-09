package parvez.vip.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import parvez.vip.data.helper._Crypto;

/**
 * Project: Data
 * Created by Muhammad Harun-Or-Roshid
 * Email: md.parvez28@gmail.com
 * Web: https://parvez.vip
 * On 19 August 2018 at 12:47 AM
 */
public class _Preferences {
    private static final String TAG = "_Data_Preferences__";
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor sharedPreferenceEditor;

    /**
     * constructor
     */
    private _Preferences(){
    }
    /**
     * Create SharedPreferences
     *
     * @param context
     */
    private static void create(@NonNull Context context) {
        if (sharedPreferences == null) {
            String fileName = context.getPackageName() + "_Data";
            sharedPreferences = context.getSharedPreferences(fileName, context.MODE_PRIVATE);
            sharedPreferenceEditor = sharedPreferences.edit();
        }
    }






    /*========================Save and Load String========================*/

    /**
     * Save String type of data in SharedPreferences
     *
     * @param context passing your current activity or context
     * @param data    your String value(Data can be null)
     * @param key     The name of your data
     * @return true if successfully save data
     */
    public static boolean saveString(@NonNull Context context, String data, @NonNull String key) {
        try {
            create(context);
            sharedPreferenceEditor.putString(key, data);
            sharedPreferenceEditor.commit();
            Log.d(TAG, "Successfully saved data.");
            return true;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }

    /**
     * Save String type of data in SharedPreferences
     *
     * @param context  passing your current activity or context
     * @param data     your String value(Data can be null)
     * @param key      The name of your data
     * @param password encrypted your data with your password
     * @return true if successfully save data
     */
    public static boolean saveString(@NonNull Context context, String data, @NonNull String key, @Nullable String password) {
        String encData = _Crypto.encrypt(data, password);
        return saveString(context, encData, key);
    }


    /**
     * Load String type of value
     *
     * @param context passing your current activity or context
     * @param key     The name of your data
     * @return String data (Default value null)
     */
    public static String loadString(@NonNull Context context, @NonNull String key) {
        create(context);
        return sharedPreferences.getString(key, null);
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
        try {
            String encData = loadString(context, key);
            if (encData != null) {
                return _Crypto.decrypt(encData, password);
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }

    /*========================End String========================*/



    /*========================Save and Load Integer========================*/

    /**
     * Save Integer type of data in SharedPreferences
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
     * Save Integer type of data in SharedPreferences
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
     * Save Boolean type of data in SharedPreferences
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
     * Save Boolean type of data in SharedPreferences
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
     * Save Float type of data in SharedPreferences
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
     * Save Float type of data in SharedPreferences
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
     * Save Long type of data in SharedPreferences
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
     * Save Long type of data in SharedPreferences
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
