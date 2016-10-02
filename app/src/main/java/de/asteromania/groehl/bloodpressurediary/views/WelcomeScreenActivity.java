package de.asteromania.groehl.bloodpressurediary.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import de.asteromania.groehl.bloodpressurediary.R;
import de.asteromania.groehl.bloodpressurediary.layout.ChoseDataTypeListAdapter;
import de.asteromania.groehl.bloodpressurediary.layout.DataItemListAdapter;

public class WelcomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        setupView();
    }

    private void setupView() {
        ListView lv=(ListView) findViewById(R.id.listViewChoseTrackedItems);
        final ChoseDataTypeListAdapter listAdapter = new ChoseDataTypeListAdapter(this);
        lv.setAdapter(listAdapter);

    }
}
