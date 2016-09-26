package de.asteromania.groehl.bloodpressurediary.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import de.asteromania.groehl.bloodpressurediary.R;
import de.asteromania.groehl.bloodpressurediary.database.DatabaseService;
import de.asteromania.groehl.bloodpressurediary.database.UserDataAccess;
import de.asteromania.groehl.bloodpressurediary.database.UserDataAccessDummyImpl;

public class LaunchActivity extends AppCompatActivity {

    private static final String TAG = "LaunchActivity";
    UserDataAccess userDataAccess = DatabaseService.getUserDataAccess();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Hello World");

        if(userDataAccess.dataAvailable())
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
