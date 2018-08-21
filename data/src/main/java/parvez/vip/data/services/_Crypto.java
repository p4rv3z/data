package parvez.vip.data.services;


import android.os.Build;

import java.security.InvalidKeyException;
import java.security.Key;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Project: Data
 * Created by Muhammad Harun-Or-Roshid
 * Email: md.parvez28@gmail.com
 * Web: https://parvez.vip
 * On 19 August 2018 at 1:17 AM
 */
public class _Crypto {
    private static final String ALGORITHM = "AES";

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
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                return Base64.getEncoder().encodeToString(encVal);
//            } else {
            return android.util.Base64.encodeToString(encVal, android.util.Base64.DEFAULT);
            // }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
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
            byte[] decordedValue = new byte[0];
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//                decordedValue = Base64.getDecoder().decode(data);
//            } else {
            decordedValue = android.util.Base64.decode(data, android.util.Base64.DEFAULT);
            //}
            byte[] decValue = c.doFinal(decordedValue);
            return new String(decValue);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
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
