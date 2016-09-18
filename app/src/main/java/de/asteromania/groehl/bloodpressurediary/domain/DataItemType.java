package de.asteromania.groehl.bloodpressurediary.domain;

import de.asteromania.groehl.bloodpressurediary.R;

/**
 * Created by jgroehl on 11.09.16.
 */
public enum DataItemType
{
    SYSTOLE(R.string.dataTypeSystole), DIASTOLE(R.string.dataTypeDiastole),
    HEARTRATE(R.string.dataTypeHeartRate), WEIGHT(R.string.dataTypeWeight);

    DataItemType(int text)
    {
        this.text = text;
    }

    int text;

    public int getText() {
        return text;
    }
}