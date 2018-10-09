package parvez.vip.data.helper;


import android.util.Base64;
import android.util.Log;

import java.security.InvalidKeyException;
import java.security.Key;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import java.security.NoSuchAlgorithmException;

/**
 * Project: Data
 * Created by Muhammad Harun-Or-Roshid
 * Email: md.parvez28@gmail.com
 * Web: https://parvez.vip
 * On 19 August 2018 at 1:17 AM
 */
public class _Crypto {
    private static final String ALGORITHM = "AES";
    public static final String TAG = "_Data_Crypto__";

    /**
     * constructor
     */
    private _Crypto(){

    }
    /**
     * @param data
     * @param password
     * @return encrypted value
     * @password length 16 character or less then 16 character
     */
    public static String encrypt(String data, String password) {
        try {
            Key key = generateKey(password);
            Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.ENCRYPT_MODE, key);
            byte[] encVal = c.doFinal(data.getBytes());
            return Base64.encodeToString(encVal, Base64.DEFAULT);
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG,e.getMessage());
        } catch (NoSuchPaddingException e) {
            Log.e(TAG,e.getMessage());
        } catch (BadPaddingException e) {
            Log.e(TAG,e.getMessage());
        } catch (IllegalBlockSizeException e) {
            Log.e(TAG,e.getMessage());
        } catch (InvalidKeyException e) {
            Log.e(TAG,e.getMessage());
        }
        return null;
    }


    /**
     * @param data
     * @param password
     * @return decrypted value
     * @password length 16 character or less then 16 character
     */
    public static String decrypt(String data, String password) {
        try {
            Key key = generateKey(password);
            Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.DECRYPT_MODE, key);
            byte[] decodedValue = Base64.decode(data, Base64.DEFAULT);
            byte[] decValue = c.doFinal(decodedValue);
            return new String(decValue);
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG,e.getMessage());
        } catch (NoSuchPaddingException e) {
            Log.e(TAG,e.getMessage());
        } catch (BadPaddingException e) {
            Log.e(TAG,e.getMessage());
        } catch (IllegalBlockSizeException e) {
            Log.e(TAG,e.getMessage());
        } catch (InvalidKeyException e) {
            Log.e(TAG,e.getMessage());
        }
        return null;
    }


    /**
     * Generate a new encryption key.
     */
    private static Key generateKey(String key) {
        byte[] keyValue = new byte[]{'E', 'n', 'c', 'R', 'y', 'P', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y'};
        if (key != null) {
            int length = 0;
            if (key.length() >= keyValue.length) {
                length = 16;
            } else if (key.length() < keyValue.length) {
                length = key.length();
            }
            for (int i = 0; i < length; i++) {
                keyValue[i] = (byte) key.charAt(i);
            }
        }
        return new SecretKeySpec(keyValue, ALGORITHM);
    }
}
