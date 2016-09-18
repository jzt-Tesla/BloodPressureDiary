package de.asteromania.groehl.bloodpressurediary.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.Button;
import android.widget.RelativeLayout;

import de.asteromania.groehl.bloodpressurediary.R;
import de.asteromania.groehl.bloodpressurediary.database.UserDataAccess;
import de.asteromania.groehl.bloodpressurediary.database.UserDataAccessDummyImpl;
import de.asteromania.groehl.bloodpressurediary.domain.DataItemType;

public class AddDataItemChoser extends AppCompatActivity {

    UserDataAccess userDataAccess = new UserDataAccessDummyImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data_item_choser);

        RelativeLayout view = (RelativeLayout) findViewById(R.id.relative_layout_data_item_choser);

        for(DataItemType type : userDataAccess.getTrackedValues())
        {
            addButton(view, type);
        }

    }

    private void addButton(RelativeLayout view, DataItemType type) {
        Button button = new Button(this);
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 150, getResources().getDisplayMetrics());
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 48, getResources().getDisplayMetrics());
        button.setGravity(Gravity.CENTER);
        button.setWidth(width);
        button.setHeight(height);
        button.setText(type.getText());
        view.addView(button);
    }
}
