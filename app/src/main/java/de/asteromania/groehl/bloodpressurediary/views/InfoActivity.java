package de.asteromania.groehl.bloodpressurediary.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import de.asteromania.groehl.bloodpressurediary.R;
import de.asteromania.groehl.bloodpressurediary.domain.DataItemType;
import de.asteromania.groehl.bloodpressurediary.domain.InformationType;

public class InfoActivity extends AppCompatActivity {

    public static final String EXTRA = "de.asteromania.groehl.bloodpressurediary.views.InfoActivity.EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String extraString = getIntent().getStringExtra(EXTRA);

        InformationType currentType = InformationType.valueOf(extraString);

        if (currentType == null) {
            finish();
        }

        setContentView(R.layout.activity_info);
    }
}
