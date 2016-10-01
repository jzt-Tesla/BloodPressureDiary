package de.asteromania.groehl.bloodpressurediary.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import de.asteromania.groehl.bloodpressurediary.database.DatabaseService;
import de.asteromania.groehl.bloodpressurediary.database.UserDatabaseAccess;

public class LaunchActivity extends AppCompatActivity {

    private static final String TAG = "LaunchActivity";

    private DatabaseService databaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Hello World");

        databaseService = new DatabaseService(this);

        if(databaseService.getUserDataAccess().dataAvailable())
        {
            Log.i(TAG, "Starting main view.");
            startActivity(new Intent(this, MainView.class));
        }
        else
        {
            Log.i(TAG, "Starting welcome screen.");
            startActivity(new Intent(this, WelcomeScreen.class));
        }

        finish();
    }
}
