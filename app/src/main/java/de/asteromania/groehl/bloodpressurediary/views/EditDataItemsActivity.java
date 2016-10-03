package de.asteromania.groehl.bloodpressurediary.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Collection;

import de.asteromania.groehl.bloodpressurediary.R;
import de.asteromania.groehl.bloodpressurediary.database.DatabaseService;
import de.asteromania.groehl.bloodpressurediary.domain.DataItem;
import de.asteromania.groehl.bloodpressurediary.domain.DataItemType;
import de.asteromania.groehl.bloodpressurediary.layout.EditDataItemListAdapter;

public class EditDataItemsActivity extends AppCompatActivity {

    public static final String EXTRA = "de.asteromania.groehl.bloodpressurediary.views.EditDataItemsActivity";

    private DatabaseService databaseService;
    private DataItemType currentType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String extraString = getIntent().getStringExtra(EXTRA);

        currentType = DataItemType.valueOf(extraString);

        if (currentType == null) {
            finish();
        }

        databaseService = new DatabaseService(this);

        setupView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setupView();
    }

    public void setupView() {
        setContentView(R.layout.activity_edit_data_items);

        ListView listView = (ListView) findViewById(R.id.listViewEditDataItems);
        if(currentType==DataItemType.SYSTOLE || currentType == DataItemType.DIASTOLE)
            listView.setAdapter(new EditDataItemListAdapter(this, databaseService.getDataItemDatabaseAccess().getAllItemsByType(DataItemType.SYSTOLE),
                    databaseService.getDataItemDatabaseAccess().getAllItemsByType(DataItemType.DIASTOLE)));
        else
            listView.setAdapter(new EditDataItemListAdapter(this, databaseService.getDataItemDatabaseAccess().getAllItemsByType(currentType)));
    }

    public DatabaseService getDatabaseService() {
        return databaseService;
    }
}