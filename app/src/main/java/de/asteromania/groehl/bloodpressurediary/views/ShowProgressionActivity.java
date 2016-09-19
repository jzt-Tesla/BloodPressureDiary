package de.asteromania.groehl.bloodpressurediary.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import de.asteromania.groehl.bloodpressurediary.R;

public class ShowProgressionActivity extends AppCompatActivity {

    public static final String EXTRA = "de.asteromania.groehl.bloodpressurediary.ShowProgressionActivity.EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_progression);
    }
}
