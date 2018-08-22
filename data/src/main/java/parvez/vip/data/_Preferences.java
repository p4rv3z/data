package parvez.vip.data;

import android.content.Context;
import android.content.SharedPreferences;
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
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor sharedPreferenceEditor;

    private static void create(String key, Context context) {
        sharedPreferences = context.getSharedPreferences(key, context.MODE_PRIVATE);
        sharedPreferenceEditor = sharedPreferences.edit();
    }

    /**
     * Save Integer type of data in SharedPreferences
     *
     * @param context passing your current activity or context
     * @param data    your Integer value
     * @param key     The name of your data
     */
    public static void saveInt(Context context, int data, String key) {
        create(key, context);
        sharedPreferenceEditor.putInt(key, data);
        sharedPreferenceEditor.commit();
    }

    /**
     * @param context
     * @param data
     * @param key
     * @param password
     * @password encrypted your data with your password
     */
    public static void saveInt(Context context, int data, String key, String password) {
        String encData = _Crypto.encrypt(String.valueOf(data), password);
        saveString(context, encData, key);
    }

    /**
     * Save String type of data in SharedPreferences
     *
     * @param context passing your current activity or context
     * @param data    your String value
     * @param key     The name of your data
     */
    public static void saveString(Context context, String data, String key) {
        create(key, context);
        sharedPreferenceEditor.putString(key, data);
        sharedPreferenceEditor.commit();
    }

    /**
     * @param context
     * @param data
     * @param key
     * @param password
     * @password encrypted your data with your password
     */
    public static void saveString(Context context, String data, String key, String password) {
        String encData = _Crypto.encrypt(data, password);
        saveString(context, encData, key);
    }


    /**
     * Save Boolean type of data in SharedPreferences
     *
     * @param context passing your current activity or context
     * @param data    your Boolean value
     * @param key     The name of your data
     */
    public static void saveBoolean(Context context, boolean data, String key) {
        create(key, context);
        sharedPreferenceEditor.putBoolean(key, data);
        sharedPreferenceEditor.commit();
    }

    /**
     * @param context
     * @param data
     * @param key
     * @param password
     * @password encrypted your data with your password
     */
    public static void saveBoolean(Context context, boolean data, String key, String password) {
        String encData = _Crypto.encrypt(String.valueOf(data), password);
        saveString(context, encData, key);
    }

    /**
     * Save Float type of data in SharedPreferences
     *
     * @param context passing your current activity or context
     * @param data    your Float value
     * @param key     The name of your data
     */
    public static void saveFloat(Context context, float data, String key) {
        create(key, context);
        sharedPreferenceEditor.putFloat(key, data);
        sharedPreferenceEditor.commit();
    }

    /**
     * @param context
     * @param data
     * @param key
     * @param password
     * @password encrypted your data with your password
     */
    public static void saveFloat(Context context, float data, String key, String password) {
        String encData = _Crypto.encrypt(String.valueOf(data), password);
        saveString(context, encData, key);
    }

    /**
     * Save Long type of data in SharedPreferences
     *
     * @param context passing your current activity or context
     * @param data    your Float value
     * @param key     The name of your data
     */
    public static void saveLong(Context context, long data, String key) {
        create(key, context);
        sharedPreferenceEditor.putLong(key, data);
        sharedPreferenceEditor.commit();
    }

    /**
     * @param context
     * @param data
     * @param key
     * @param password
     * @password encrypted your data with your password
     */
    public static void saveLong(Context context, long data, String key, String password) {
        String encData = _Crypto.encrypt(String.valueOf(data), password);
        saveString(context, encData, key);
    }


    /**
     * @param context passing your current activity or context
     * @param key     The name of your data
     * @return Integer value
     */
    public static int loadInt(Context context, String key) {
        create(key, context);
        return sharedPreferences.getInt(key, 0);
    }


    /**
     * @param context
     * @param key
     * @param password
     * @return Decrypted Integer Value
     */
    public static int loadInt(Context context, String key, String password) {
        try {
            String encData = loadString(context, key);
            if (encData != null) {
                String decData = _Crypto.decrypt(encData, password);
                if (decData != null) {
                    return Integer.valueOf(decData);
                }
            }
        } catch (Exception e) {
            Log.e("_Preferences->", e.getMessage());
        }

        return 0;
    }

    /**
     * @param context passing your current activity or context
     * @param key     The name of your data
     * @return String value
     */
    public static String loadString(Context context, String key) {
        create(key, context);
        return sharedPreferences.getString(key, null);
    }

    /**
     * @param context
     * @param key
     * @param password
     * @return Decrypted String value
     */
    public static String loadString(Context context, String key, String password) {
        try {
            String encData = loadString(context, key);
            if (encData != null) {
                String decData = _Crypto.decrypt(encData, password);
                if (decData != null) {
                    return decData;
                }
            }
        } catch (Exception e) {
            Log.e("_Preferences->", e.getMessage());
        }
        return null;
    }

    /**
     * @param context passing your current activity or context
     * @param key     The name of your data
     * @return Boolean value
     */
    public static boolean loadBoolean(Context context, String key) {
        create(key, context);
        return sharedPreferences.getBoolean(key, false);
    }

    /**
     * @param context
     * @param key
     * @param password
     * @return Decrypted Boolean value
     */
    public static boolean loadBoolean(Context context, String key, String password) {
        try {
            String encData = loadString(context, key);
            if (encData != null) {
                String decData = _Crypto.decrypt(encData, password);
                if (decData != null) {
                    return Boolean.valueOf(decData);
                }
            }
        } catch (Exception e) {
            Log.e("_Preferences->", e.getMessage());
        }
        return false;
    }

    /**
     * @param context passing your current activity or context
     * @param key     The name of your data
     * @return Float value
     */
    public static float loadFloat(Context context, String key) {
        create(key, context);
        return sharedPreferences.getFloat(key, 0);
    }

    public static float loadFloat(Context context, String key, String password) {
        try {
            String encData = loadString(context, key);
            if (encData != null) {
                String decData = _Crypto.decrypt(encData, password);
                if (decData != null) {
                    return Float.valueOf(decData);
                }
            }
        } catch (Exception e) {
            Log.e("_Preferences->", e.getMessage());
        }
        return 0;
    }

    /**
     * @param context passing your current activity or context
     * @param key     The name of your data
     * @return Float value
     */
    public static long loadLong(Context context, String key) {
        create(key, context);
        return sharedPreferences.getLong(key, 0);
    }

    /**
     * @param context
     * @param key
     * @param password
     * @return Decrypted Float value
     */
    public static long loadLong(Context context, String key, String password) {
        try {
            String encData = loadString(context, key);
            if (encData != null) {
                String decData = _Crypto.decrypt(encData, password);
                if (decData != null) {
                    return Long.valueOf(decData);
                }
            }
        } catch (Exception e) {
            Log.e("_Preferences->", e.getMessage());
        }
        return 0;
    }
}
