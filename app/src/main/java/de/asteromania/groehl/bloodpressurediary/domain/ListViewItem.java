package de.asteromania.groehl.bloodpressurediary.domain;

/**
 * Created by jgroehl on 18.09.16.
 */
public class ListViewItem {

    DataItemType dataItemType;
    double mean;
    DataItemTrend dataItemTrend;

    /**
     *
     * @param dataItemType
     * @param mean
     * @param dataItemTrend
     */
    public ListViewItem(DataItemType dataItemType, double mean, DataItemTrend dataItemTrend)
    {
        this.dataItemType = dataItemType;
        this.dataItemTrend = dataItemTrend;
        this.mean = mean;
    }

    public DataItemTrend getDataItemTrend() {
        return dataItemTrend;
    }

    public DataItemType getDataItemType() {
        return dataItemType;
    }

    public double getMean() {
        return mean;
    }
}
