package de.asteromania.groehl.bloodpressurediary.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Collection;

import de.asteromania.groehl.bloodpressurediary.R;
import de.asteromania.groehl.bloodpressurediary.database.DataItemDatabaseAccess;
import de.asteromania.groehl.bloodpressurediary.database.DatabaseService;
import de.asteromania.groehl.bloodpressurediary.domain.DataItem;
import de.asteromania.groehl.bloodpressurediary.domain.DataItemType;

public class AddDataItem extends AppCompatActivity {

    public static final String EXTRA = "de.asteromania.groehl.bloodpressurediary.AddDataItem.EXTRA";
    private static final int MAX_NUMBER_VALUE = 299;
    private static final int MIN_NUMBER_VALUE = 1;
    private static final int NUMBER_VALUE = 100;
    private static final int MAX_DECIMAL_VALUE = 9;
    private static final int MIN_DECIMAL_VALUE = 0;
    private static final int DECIMAL_VALUE = 0;
    private static final int MAX_BP_VALUE = 499;
    private static final int MIN_BP_VALUE = 0;
    private static final int BP_SYS_VALUE = 128;
    private static final int BP_DIA_VALUE = 72;

    private DatabaseService databaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseService = new DatabaseService(this);

        String extraString = getIntent().getStringExtra(EXTRA);

        DataItemType currentType = DataItemType.valueOf(extraString);

        if (currentType == null) {
            finish();
        }

        setContentView(currentType.getAddItemView());

        TextView titleView = (TextView) findViewById(R.id.textViewAddTitle);
        Button buttonAdd = (Button) findViewById(R.id.buttonAdd);

        switch(currentType)
        {
            case SYSTOLE:
            case DIASTOLE:
                DataItem systole = new DataItem(DataItemType.SYSTOLE, BP_SYS_VALUE, 0);
                Collection<? extends  DataItem> collectionSystole = databaseService.getDataItemDatabaseAccess().getLastNItemsByType(1, DataItemType.SYSTOLE);
                if(collectionSystole!=null && !collectionSystole.isEmpty())
                    systole = (DataItem) collectionSystole.toArray()[0];

                DataItem diastole = new DataItem(DataItemType.DIASTOLE, BP_DIA_VALUE, 0);
                Collection<? extends  DataItem> collectionDiastole = databaseService.getDataItemDatabaseAccess().getLastNItemsByType(1, DataItemType.DIASTOLE);
                if(collectionDiastole!=null && !collectionDiastole.isEmpty())
                    diastole = (DataItem) collectionDiastole.toArray()[0];

                titleView.setText(String.format(getString(R.string.addValue), getString(R.string.dataTypeBloodPressure)));
                final NumberPicker npSystole = (NumberPicker) findViewById(R.id.numberPickerSystole);
                npSystole.setMaxValue(MAX_BP_VALUE);
                npSystole.setMinValue(MIN_BP_VALUE);

                npSystole.setValue((int) systole.getValue());


                final NumberPicker npDiastole = (NumberPicker) findViewById(R.id.numberPickerDiastole);
                npDiastole.setMaxValue(MAX_BP_VALUE);
                npDiastole.setMinValue(MIN_BP_VALUE);

                npDiastole.setValue((int) diastole.getValue());


                TextView unit = (TextView) findViewById(R.id.textViewUnitSystole);
                if(systole !=null && systole.getItemType()!=null)
                unit.setText(systole.getItemType().getUnitString());

                buttonAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        databaseService.getDataItemDatabaseAccess().addItem(new DataItem(DataItemType.SYSTOLE, npSystole.getValue(), getCurrentTime()));
                        databaseService.getDataItemDatabaseAccess().addItem(new DataItem(DataItemType.DIASTOLE, npDiastole.getValue(), getCurrentTime()));
                        finish();
                    }
                });
                break;

            default:
                DataItem item = new DataItem(currentType, NUMBER_VALUE, 0);
                Collection<? extends  DataItem> collection = databaseService.getDataItemDatabaseAccess().getLastNItemsByType(1, currentType);
                if(collection!=null && !collection.isEmpty())
                    item = (DataItem) collection.toArray()[0];

                final NumberPicker npNumber = (NumberPicker) findViewById(R.id.numberPickerNumber);
                npNumber.setMaxValue(MAX_NUMBER_VALUE);
                npNumber.setMinValue(MIN_NUMBER_VALUE);
                npNumber.setValue((int) item.getValue());

                final NumberPicker npDecimal = (NumberPicker) findViewById(R.id.numberPickerDecimal);
                npDecimal.setMaxValue(MAX_DECIMAL_VALUE);
                npDecimal.setMinValue(MIN_DECIMAL_VALUE);
                if (item != null)
                    npDecimal.setValue((int) ((item.getValue()*10)%10));
                else
                    npDecimal.setValue(DECIMAL_VALUE);

                if(item !=null && item.getItemType()!=null)
                titleView.setText(String.format(getString(R.string.addValue), getString(item.getItemType().getText())));

                TextView unitNormal = (TextView) findViewById(R.id.textViewUnit);
                if(item !=null && item.getItemType()!=null)
                unitNormal.setText(item.getItemType().getUnitString());

                final DataItem buttonItem = item;

                buttonAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(buttonItem !=null && buttonItem.getItemType()!=null)
                            databaseService.getDataItemDatabaseAccess().addItem(new DataItem(buttonItem.getItemType(), (npNumber.getValue()+(npDecimal.getValue()/10.0)), getCurrentTime()));
                        finish();
                    }
                });

                break;

        }

        TimePicker tp = (TimePicker) findViewById(R.id.timePicker);
        tp.setIs24HourView(true);
    }

    private long getCurrentTime() {
        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);

        int month = datePicker.getMonth();
        int day = datePicker.getDayOfMonth();
        int year = datePicker.getYear();

        int hour = timePicker.getCurrentHour();
        int minute = timePicker.getCurrentMinute();

        Calendar c = Calendar.getInstance();
        c.set(year, month, day, hour, minute);
        return c.getTimeInMillis();
    }
}