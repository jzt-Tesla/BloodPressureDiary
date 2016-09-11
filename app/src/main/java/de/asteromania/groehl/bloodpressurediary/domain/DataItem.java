package de.asteromania.groehl.bloodpressurediary.domain;

/**
 * Created by jgroehl on 11.09.16.
 */
public class DataItem
{

    private int date;
    private double value;
    private DataItemType itemType;

    public DataItem(DataItemType itemType, double value, int date)
    {
        this.itemType = itemType;
        this.value = value;
        this.date = date;
    }
}
