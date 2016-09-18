package de.asteromania.groehl.bloodpressurediary.domain;

/**
 * Created by jgroehl on 11.09.16.
 */
public class DataItem
{

    private long date;
    private double value;
    private DataItemType itemType;

    public DataItem(DataItemType itemType, double value, long date)
    {
        this.itemType = itemType;
        this.value = value;
        this.date = date;
    }

    public long getDate() {
        return date;
    }

    public double getValue() {
        return value;
    }

    public DataItemType getItemType() {
        return itemType;
    }
}
