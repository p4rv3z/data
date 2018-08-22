package parvez.vip.data;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import parvez.vip.data.helper._Crypto;

/**
 * Project: Data
 * Created by Muhammad Harun-Or-Roshid
 * Email: md.parvez28@gmail.com
 * Web: https://parvez.vip
 * On 19 August 2018 at 12:47 AM
 */
public class _File {
    private static File file;
    private static PrintWriter printWriter;
    private static FileInputStream fileInputStream;
    private static BufferedReader bufferedReader;
    private static StringBuffer stringBuffer;


    /**
     * @param context
     * @param fileName
     * @param data
     * @return true if successfully save data
     */
    public static boolean saveFile(Context context, String fileName, String data) {
        try {
            file = new File(context.getFilesDir(), fileName);
            printWriter = new PrintWriter(file, "UTF-8");
            printWriter.write(data);
            printWriter.close();
            return true;
        } catch (FileNotFoundException e) {
            Log.e("_File->", e.getMessage());
        } catch (IOException e) {
            Log.e("_File->", e.getMessage());
        }
        return false;
    }

    /**
     * @param context
     * @param fileName
     * @param data
     * @param password
     * @return true if successfully save data
     */
    public static boolean saveFile(Context context, String fileName, String data, String password) {
        data = _Crypto.encrypt(data, password);
        return saveFile(context, fileName, data);
    }

    /**
     * @param context
     * @param fileName
     * @return data
     */
    public static String loadFile(Context context, String fileName) {
        try {
            file = new File(context.getFilesDir(), fileName);
            fileInputStream = new FileInputStream(file);
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
            return readData(bufferedReader);
        } catch (FileNotFoundException e) {
            Log.e("_File->", e.getMessage());
        } catch (IOException e) {
            Log.e("_File->", e.getMessage());
        }
        return null;
    }


    /**
     *
     * @param context
     * @param fileName
     * @param password
     * @return data
     */
    public static String loadFile(Context context, String fileName, String password) {
        String enData = loadFile(context, fileName);
        String deData = _Crypto.decrypt(enData, password);
        return deData;
    }

    /**
     * @param bufferedReader
     * @return
     * @throws IOException
     */
    private static String readData(BufferedReader bufferedReader) {
        try {
            stringBuffer = new StringBuffer();
            String data;
            while ((data = bufferedReader.readLine()) != null) {
                stringBuffer.append(data);
            }
            bufferedReader.close();
            fileInputStream.close();
            return stringBuffer.toString();
        } catch (IOException e) {
            Log.e("_File->", e.getMessage());
        }
        return null;
    }
}
