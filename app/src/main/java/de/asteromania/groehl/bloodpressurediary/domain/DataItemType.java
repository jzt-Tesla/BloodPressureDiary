package de.asteromania.groehl.bloodpressurediary.domain;

import de.asteromania.groehl.bloodpressurediary.R;

/**
 * Created by jgroehl on 11.09.16.
 */
public enum DataItemType
{
    SYSTOLE(R.string.dataTypeSystole, R.layout.activity_add_data_item),
    DIASTOLE(R.string.dataTypeDiastole, R.layout.activity_add_data_item),
    HEARTRATE(R.string.dataTypeHeartRate, R.layout.activity_add_data_item),
    WEIGHT(R.string.dataTypeWeight, R.layout.activity_add_data_item);

    DataItemType(int text, int contentView)
    {
        this.text = text;
        this.addItemView = contentView;
    }

    int text;
    int addItemView;

    public int getText() {
        return text;
    }

    public int getAddItemView() {
        return addItemView;
    }
}