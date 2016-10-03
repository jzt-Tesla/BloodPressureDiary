package de.asteromania.groehl.bloodpressurediary.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import de.asteromania.groehl.bloodpressurediary.R;
import de.asteromania.groehl.bloodpressurediary.domain.InformationType;

public class ExportActivity extends AppCompatActivity {

    private static final String FOLDER_NAME = "BloodPressureDiary";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy_HH.mm.ss", Locale.getDefault());
    private static final String TAG = "ExportActivity";
    public static final Paint PAINT = new Paint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus) {
            takeScreenshot();
            finish();
        }
    }

    public void takeScreenshot()
    {
        LinearLayout rootView = (LinearLayout) findViewById(R.id.exportScreenshotView);
        int width = rootView.getMeasuredWidth();
        int height = rootView.getMeasuredHeight();
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        c.drawColor(Color.WHITE);
        rootView.draw(c);
        saveBitmap(b);
    }

    private void saveBitmap(Bitmap bitmap)
    {
        if(bitmap==null)
        {
            Log.w(TAG, "Bitmap was null");
            return;
        }
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();

        File rootFile = new File(root, FOLDER_NAME);

        if(!rootFile.exists())
            rootFile.mkdirs();

        String name = "Screenshot_" + DATE_FORMAT.format(new Date()) + ".png";

        File file = new File(rootFile, name);

        if (file.exists())
            file.delete();

        try {
            FileOutputStream fos = new FileOutputStream(file);
            boolean result = bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            if(result)
                Log.i(TAG, "Saved PNG in folder: " + file.getAbsolutePath());
            else
                Log.w(TAG, "Saving PNG in " + file.getAbsolutePath() + " failed.");
        } catch (FileNotFoundException e) {
            Log.e(TAG, e.getMessage(), e);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

}
