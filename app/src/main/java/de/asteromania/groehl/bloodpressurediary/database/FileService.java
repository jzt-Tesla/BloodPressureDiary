package de.asteromania.groehl.bloodpressurediary.database;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

/**
 * Created by jgroehl on 02.10.16.
 */
public class FileService {

    private static final String TAG = "FileService";

    public static String readFromFile(String filename, Context context)
    {
        String result = "";
        BufferedReader reader;
        try
        {
            reader = new BufferedReader(new InputStreamReader(context.openFileInput(filename)));
            String line;
            while((line = reader.readLine()) !=null)
            {
                result += line;
            }
            return result;
        }
        catch (Exception e)
        {
            Log.w(TAG, e.getMessage());
            return null;
        }

    }

    public static boolean writeToFile(String filename, String content, Context context)
    {
        FileOutputStream outputStream;
        try {
            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(content.getBytes());
            outputStream.close();
            return true;
        } catch (Exception e) {
            Log.w(TAG, e.getMessage());
            return false;
        }
    }
}
