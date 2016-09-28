package de.asteromania.groehl.bloodpressurediary.domain;

import de.asteromania.groehl.bloodpressurediary.R;

/**
 * Created by jgroehl on 11.09.16.
 */
public enum DataItemType
{
    SYSTOLE(R.string.dataTypeSystole, R.layout.activity_add_data_item_blood_pressure, R.string.unit_bloodPressure, InformationType.BLOOD_PRESSURE),
    DIASTOLE(R.string.dataTypeDiastole, R.layout.activity_add_data_item_blood_pressure, R.string.unit_bloodPressure, InformationType.BLOOD_PRESSURE),
    HEARTRATE(R.string.dataTypeHeartRate, R.layout.activity_add_data_item, R.string.unit_heartrate, InformationType.HEARTRATE),
    WEIGHT(R.string.dataTypeWeight, R.layout.activity_add_data_item, R.string.unit_weight, InformationType.WEIGHT);

    DataItemType(int text, int contentView, int unitString, InformationType informationType)
    {
        this.text = text;
        this.addItemView = contentView;
        this.unitString = unitString;
        this.informationType = informationType;
    }

    int text;
    int addItemView;
    int unitString;
    InformationType informationType;

    public int getText() {
        return text;
    }

    public int getAddItemView() {
        return addItemView;
    }

    public int getUnitString() {
        return unitString;
    }

    public InformationType getInformationType() {
        return informationType;
    }
}