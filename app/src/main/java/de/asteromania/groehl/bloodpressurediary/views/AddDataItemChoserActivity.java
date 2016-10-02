package de.asteromania.groehl.bloodpressurediary.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import de.asteromania.groehl.bloodpressurediary.R;
import de.asteromania.groehl.bloodpressurediary.database.DatabaseService;
import de.asteromania.groehl.bloodpressurediary.domain.DataItemType;

public class AddDataItemChoserActivity extends AppCompatActivity {

    private DatabaseService databaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseService = new DatabaseService(this);

        setContentView(R.layout.activity_add_data_item_choser);

        LinearLayout view = (LinearLayout) findViewById(R.id.relative_layout_data_item_choser);

        for(DataItemType type : databaseService.getUserDataAccess().getTrackedValues())
        {
            if(type == DataItemType.DIASTOLE)
                continue;

            addButton(view, type);
        }

    }

    private void addButton(LinearLayout view, final DataItemType type) {
        Button button = new Button(this);
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 150, getResources().getDisplayMetrics());
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 48, getResources().getDisplayMetrics());
        button.setGravity(Gravity.CENTER);
        button.setWidth(width);
        button.setHeight(height);
        button.setCompoundDrawablesWithIntrinsicBounds(type.getTypeIcon(), 0, 0, 0);
        button.setMaxWidth(width);

        if(type == DataItemType.SYSTOLE)
            button.setText(R.string.dataTypeBloodPressure);
        else
            button.setText(type.getText());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDataItemChoserActivity.this, AddDataItemActivity.class);
                intent.putExtra(AddDataItemActivity.EXTRA, type.toString());
                startActivity(intent);
                finish();
            }
        });
        view.addView(button);
    }
}
