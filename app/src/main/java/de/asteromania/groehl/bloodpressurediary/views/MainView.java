package de.asteromania.groehl.bloodpressurediary.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

import de.asteromania.groehl.bloodpressurediary.R;
import de.asteromania.groehl.bloodpressurediary.database.DatabaseService;
import de.asteromania.groehl.bloodpressurediary.domain.DataItemType;
import de.asteromania.groehl.bloodpressurediary.domain.InformationType;
import de.asteromania.groehl.bloodpressurediary.layout.DataItemListAdapter;

public class MainView extends AppCompatActivity {

    private DatabaseService databaseService;
    private List<DataItemType> trackedDataItemTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseService = new DatabaseService(this);

        trackedDataItemTypes = databaseService.getUserDataAccess().getTrackedValues();

        setContentView(R.layout.activity_main_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView lv=(ListView) findViewById(R.id.listView);
        lv.setAdapter(new DataItemListAdapter(this, databaseService.getDataItemDatabaseAccess().getFloatingMeansOfDataItems(trackedDataItemTypes)));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainView.this, AddDataItemChoser.class));
            }
        });
    }

    @Override
    protected void onResume() {
        ListView lv=(ListView) findViewById(R.id.listView);
        lv.setAdapter(new DataItemListAdapter(this, databaseService.getDataItemDatabaseAccess().getFloatingMeansOfDataItems(trackedDataItemTypes)));

        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch(id)
        {
        case R.id.action_add:
            startActivity(new Intent(this, AddDataItemChoser.class));
            return true;
        case R.id.action_export:
            startActivity(new Intent(this, ExportActivity.class));
            return true;
        case R.id.action_info:
            Intent infoIntent = new Intent(this, InfoActivity.class);
            infoIntent.putExtra(InfoActivity.EXTRA, InformationType.GENERAL_INFORMATION.toString());
            this.startActivity(infoIntent);
            return true;
        case R.id.action_settings:
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
