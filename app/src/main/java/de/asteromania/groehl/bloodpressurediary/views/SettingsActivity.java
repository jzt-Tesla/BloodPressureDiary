package de.asteromania.groehl.bloodpressurediary.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;

import java.util.Calendar;
import java.util.Date;

import de.asteromania.groehl.bloodpressurediary.R;
import de.asteromania.groehl.bloodpressurediary.database.DatabaseService;
import de.asteromania.groehl.bloodpressurediary.layout.ChoseDataTypeListAdapter;

public class SettingsActivity extends AppCompatActivity {

    private static final int MIN_HEIGHT = 0;
    private static final int MAX_HEIGHT = 300;
    DatabaseService databaseService = new DatabaseService(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setupView();
    }

    private void setupView() {
        ListView lv=(ListView) findViewById(R.id.listViewChoseTrackedItems);
        final ChoseDataTypeListAdapter listAdapter = new ChoseDataTypeListAdapter(this);
        lv.setAdapter(listAdapter);

        final EditText nameText = (EditText) findViewById(R.id.editTextPersonName);
        final NumberPicker height = (NumberPicker) findViewById(R.id.numberPickerCentimeters);
        height.setMinValue(MIN_HEIGHT);
        height.setMaxValue(MAX_HEIGHT);
        height.setValue((MAX_HEIGHT-MIN_HEIGHT)/2);
        final DatePicker birthday = (DatePicker) findViewById(R.id.datePickerBirthday);

        if(databaseService.getUserDataAccess().dataAvailable())
        {
            nameText.setText(databaseService.getUserDataAccess().getName());
            height.setValue((int) (databaseService.getUserDataAccess().getHeight()*100));
            Calendar birthdate = Calendar.getInstance();
            birthdate.setTime(databaseService.getUserDataAccess().getBirthdate());
            birthday.updateDate(birthdate.get(Calendar.YEAR), birthdate.get(Calendar.MONTH), birthdate.get(Calendar.DAY_OF_MONTH));
        }

        Button submitButton = (Button) findViewById(R.id.buttonSubmitData);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseService.getUserDataAccess().setHeight(height.getValue()/100.0);
                databaseService.getUserDataAccess().setName(nameText.getText().toString());
                Calendar c = Calendar.getInstance();
                c.set(birthday.getYear(), birthday.getMonth(), birthday.getDayOfMonth());
                databaseService.getUserDataAccess().setBirthdate(c.getTime());
                databaseService.getUserDataAccess().setTrackedValues(listAdapter.getSelectedDataItems());
                finish();
                startActivity(new Intent(SettingsActivity.this, MainViewActivity.class));
            }
        });
    }
}
