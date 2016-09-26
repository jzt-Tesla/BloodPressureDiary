package de.asteromania.groehl.bloodpressurediary.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;

import de.asteromania.groehl.bloodpressurediary.R;
import de.asteromania.groehl.bloodpressurediary.database.DatabaseAccess;
import de.asteromania.groehl.bloodpressurediary.database.DatabaseService;

public class ShowProgressionActivity extends AppCompatActivity {

    public static final String EXTRA = "de.asteromania.groehl.bloodpressurediary.ShowProgressionActivity.EXTRA";

    DatabaseAccess database = DatabaseService.getDatabaseAccess();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_progression);

        GraphView graph = (GraphView) findViewById(R.id.graphProgression);

    }
}
