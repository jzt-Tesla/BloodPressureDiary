package de.asteromania.groehl.bloodpressurediary.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import de.asteromania.groehl.bloodpressurediary.R;
import de.asteromania.groehl.bloodpressurediary.database.DatabaseAccess;
import de.asteromania.groehl.bloodpressurediary.database.DatabaseAccessDummyImpl;
import de.asteromania.groehl.bloodpressurediary.domain.DataItem;
import de.asteromania.groehl.bloodpressurediary.domain.DataItemType;

public class AddDataItem extends AppCompatActivity {

    public static final String EXTRA = "de.asteromania.groehl.bloodpressurediary.AddDataItem.EXTRA";

    DatabaseAccess database = new DatabaseAccessDummyImpl();

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

        DataItem item = (DataItem) database.getLastNItemsByType(1, currentType).toArray()[0];

        TextView tv = (TextView) findViewById(R.id.textViewAddTitle);

        switch(currentType)
        {
            case SYSTOLE:
            case DIASTOLE:
                tv.setText("Add " + getString(R.string.dataTypeBloodPressure) + " value");
                break;
            default:
                NumberPicker npNumber = (NumberPicker) findViewById(R.id.numberPickerNumber);
                npNumber.setMaxValue(299);
                npNumber.setMinValue(1);
                if (item != null)
                    npNumber.setValue((int) item.getValue());
                else
                    npNumber.setValue(100);
                NumberPicker npDecimal = (NumberPicker) findViewById(R.id.numberPickerDecimal);
                npDecimal.setMaxValue(9);
                npDecimal.setMinValue(0);
                if (item != null)
                    npDecimal.setValue((int) ((item.getValue()*10)%10));
                else
                    npDecimal.setValue(0);

                tv.setText(getString(R.string.action_add) + getString(currentType.getText()) + " value");
                break;

        }

        TimePicker tp = (TimePicker) findViewById(R.id.timepickerBP);
        tp.setIs24HourView(true);




    }
}
