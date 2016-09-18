package de.asteromania.groehl.bloodpressurediary.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import de.asteromania.groehl.bloodpressurediary.R;
import de.asteromania.groehl.bloodpressurediary.domain.DataItemType;

public class AddDataItem extends AppCompatActivity {

    public static final String EXTRA = "de.asteromania.groehl.bloodpressurediary.AddDataItem.EXTRA";

    private DataItemType currentType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String extraString = getIntent().getStringExtra(EXTRA);

        currentType = DataItemType.valueOf(extraString);

        if(currentType==null)
        {
            finish();
        }

        setContentView(currentType.getContentView());
    }
}
