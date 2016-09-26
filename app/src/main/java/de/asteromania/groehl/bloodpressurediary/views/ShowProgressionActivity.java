package de.asteromania.groehl.bloodpressurediary.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;

import de.asteromania.groehl.bloodpressurediary.R;
import de.asteromania.groehl.bloodpressurediary.database.DatabaseAccess;
import de.asteromania.groehl.bloodpressurediary.database.DatabaseService;
import de.asteromania.groehl.bloodpressurediary.domain.DataItemType;

public class ShowProgressionActivity extends AppCompatActivity {

    public static final String EXTRA = "de.asteromania.groehl.bloodpressurediary.ShowProgressionActivity.EXTRA";

    DatabaseAccess database = DatabaseService.getDatabaseAccess();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_progression);

        String extraString = getIntent().getStringExtra(EXTRA);

        DataItemType currentType = DataItemType.valueOf(extraString);

        if (currentType == null) {
            finish();
        }

        GraphView graph = (GraphView) findViewById(R.id.graphProgression);

    }
}
