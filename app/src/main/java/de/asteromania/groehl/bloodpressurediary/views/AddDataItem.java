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

import de.asteromania.groehl.bloodpressurediary.R;
import de.asteromania.groehl.bloodpressurediary.database.DatabaseAccess;
import de.asteromania.groehl.bloodpressurediary.database.DatabaseAccessDummyImpl;
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
    private static final int BP_VALUE = 140;

    DatabaseAccess database = DatabaseAccessDummyImpl.getInstance();

    private DataItemType currentType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String extraString = getIntent().getStringExtra(EXTRA);

        currentType = DataItemType.valueOf(extraString);

        if (currentType == null) {
            finish();
        }

        setContentView(currentType.getAddItemView());

        final DataItem item = (DataItem) database.getLastNItemsByType(1, currentType).toArray()[0];

        TextView titleView = (TextView) findViewById(R.id.textViewAddTitle);
        Button buttonAdd = (Button) findViewById(R.id.buttonAdd);

        switch(currentType)
        {
            case SYSTOLE:
            case DIASTOLE:
                DataItem systole = (DataItem) database.getLastNItemsByType(1, DataItemType.SYSTOLE).toArray()[0];
                DataItem diastole = (DataItem) database.getLastNItemsByType(1, DataItemType.DIASTOLE).toArray()[0];

                titleView.setText(String.format(getString(R.string.addValue), getString(R.string.dataTypeBloodPressure)));
                final NumberPicker npSystole = (NumberPicker) findViewById(R.id.numberPickerSystole);
                npSystole.setMaxValue(MAX_BP_VALUE);
                npSystole.setMinValue(MIN_BP_VALUE);
                if (item != null)
                    npSystole.setValue((int) systole.getValue());
                else
                    npSystole.setValue(BP_VALUE);

                NumberPicker npDiastole = (NumberPicker) findViewById(R.id.numberPickerDiastole);
                npDiastole.setMaxValue(MAX_BP_VALUE);
                npDiastole.setMinValue(MIN_BP_VALUE);
                if (item != null)
                    npDiastole.setValue((int) diastole.getValue());
                else
                    npDiastole.setValue(BP_VALUE);

                TextView unit = (TextView) findViewById(R.id.textViewUnitSystole);
                unit.setText(item.getItemType().getUnitString());

                buttonAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        database.addItem(new DataItem(DataItemType.SYSTOLE, npSystole.getValue(), getCurrentTime()));
                        database.addItem(new DataItem(DataItemType.DIASTOLE, npSystole.getValue(), getCurrentTime()));
                        finish();
                    }
                });
                break;

            default:
                final NumberPicker npNumber = (NumberPicker) findViewById(R.id.numberPickerNumber);
                npNumber.setMaxValue(MAX_NUMBER_VALUE);
                npNumber.setMinValue(MIN_NUMBER_VALUE);
                if (item != null)
                    npNumber.setValue((int) item.getValue());
                else
                    npNumber.setValue(NUMBER_VALUE);
                final NumberPicker npDecimal = (NumberPicker) findViewById(R.id.numberPickerDecimal);
                npDecimal.setMaxValue(MAX_DECIMAL_VALUE);
                npDecimal.setMinValue(MIN_DECIMAL_VALUE);
                if (item != null)
                    npDecimal.setValue((int) ((item.getValue()*10)%10));
                else
                    npDecimal.setValue(DECIMAL_VALUE);

                titleView.setText(String.format(getString(R.string.addValue), getString(item.getItemType().getText())));

                TextView unitNormal = (TextView) findViewById(R.id.textViewUnit);
                unitNormal.setText(item.getItemType().getUnitString());

                buttonAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        database.addItem(new DataItem(item.getItemType(), (npNumber.getValue()+(npDecimal.getValue()/10.0)), getCurrentTime()));
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
