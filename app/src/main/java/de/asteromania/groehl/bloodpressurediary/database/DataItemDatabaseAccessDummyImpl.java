package de.asteromania.groehl.bloodpressurediary.database;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import de.asteromania.groehl.bloodpressurediary.domain.DataItem;
import de.asteromania.groehl.bloodpressurediary.domain.DataItemTrend;
import de.asteromania.groehl.bloodpressurediary.domain.DataItemType;
import de.asteromania.groehl.bloodpressurediary.domain.ListViewItem;

/**
 * Created by jgroehl on 11.09.16.
 */
public class DataItemDatabaseAccessDummyImpl implements DataItemDatabaseAccess
{
    private static final String TAG = "DatabaseDummyImpl" ;
    private HashMap<DataItemType, ArrayList<DataItem>> dataItems;

    DataItemDatabaseAccessDummyImpl()
    {
        dataItems = new HashMap<>();
        for(DataItemType type : DataItemType.values())
        {
            dataItems.put(type, new ArrayList<DataItem>());
        }
        dataItems.get(DataItemType.SYSTOLE).add(new DataItem(DataItemType.SYSTOLE, 144, getDate(2016, 11, 1, 13, 0)));
        dataItems.get(DataItemType.SYSTOLE).add(new DataItem(DataItemType.SYSTOLE, 139, getDate(2016, 11, 2, 13, 0)));
        dataItems.get(DataItemType.SYSTOLE).add(new DataItem(DataItemType.SYSTOLE, 133, getDate(2016, 11, 3, 13, 0)));
        dataItems.get(DataItemType.SYSTOLE).add(new DataItem(DataItemType.SYSTOLE, 120, getDate(2016, 11, 4, 13, 0)));
        dataItems.get(DataItemType.DIASTOLE).add(new DataItem(DataItemType.DIASTOLE, 96, getDate(2016, 11, 1, 13, 0)));
        dataItems.get(DataItemType.DIASTOLE).add(new DataItem(DataItemType.DIASTOLE, 104, getDate(2016, 11, 2, 13, 0)));
        dataItems.get(DataItemType.DIASTOLE).add(new DataItem(DataItemType.DIASTOLE, 99, getDate(2016, 11, 3, 13, 0)));
        dataItems.get(DataItemType.DIASTOLE).add(new DataItem(DataItemType.DIASTOLE, 100, getDate(2016, 11, 4, 13, 0)));
        dataItems.get(DataItemType.HEARTRATE).add(new DataItem(DataItemType.HEARTRATE, 86, getDate(2016, 11, 1, 13, 0)));
        dataItems.get(DataItemType.HEARTRATE).add(new DataItem(DataItemType.HEARTRATE, 84, getDate(2016, 11, 3, 13, 0)));
        dataItems.get(DataItemType.WEIGHT).add(new DataItem(DataItemType.WEIGHT, 120, getDate(2016, 11, 1, 13, 0)));
        dataItems.get(DataItemType.WEIGHT).add(new DataItem(DataItemType.WEIGHT, 123, getDate(2016, 11, 3, 13, 0)));
    }

    @Override
    public boolean addItem(DataItem item) {
        dataItems.get(item.getItemType()).add(item);
        Collections.sort(dataItems.get(item.getItemType()), new Comparator<DataItem>() {
            @Override
            public int compare(DataItem dataItem, DataItem t1) {
                if(dataItem.getDate()>t1.getDate())
                    return 1;
                else if(dataItem.getDate()<t1.getDate())
                    return -1;
                else
                    return 0;
            }
        });
        return true;
    }

    @Override
    public Collection<? extends DataItem> getAllItemsByType(DataItemType type) {
        return dataItems.get(type);
    }

    @Override
    public Collection<? extends DataItem> getLastNItemsByType(int n, DataItemType type) {
        ArrayList<DataItem> returnList = dataItems.get(type);
        int size = returnList.size();
        return returnList.subList((size-n) < 0 ? 0 : (size-n), size);
    }

    @Override
    public Collection<? extends ListViewItem> getFloatingMeansOfDataItems(Collection<? extends DataItemType> trackedDataItemTypes)
    {
        ArrayList<ListViewItem> returnList = new ArrayList<>();
        for(DataItemType type : DataItemType.values())
        {
            double mean = 0;
            ArrayList<DataItem> typeList = dataItems.get(type);
            for(DataItem item : typeList)
            {
                mean += item.getValue();
            }
            mean /= typeList.size();
            mean = Math.round(mean*10);
            mean /= 10;
            DataItemTrend trend = DataItemTrend.NEUTRAL;
            if(typeList.size()>1) {
                double ratio = typeList.get(typeList.size()-1).getValue() / typeList.get(typeList.size()-2).getValue();
                Log.i(TAG, ""+ratio);
                if (ratio > 1)
                    trend = DataItemTrend.INCREASING;
                else if(ratio < 1)
                    trend  = DataItemTrend.DECREASING;
            }
            returnList.add(new ListViewItem(type, mean, trend));
        }
        return returnList;
    }

    @Override
    public double getMaximumValue(DataItemType type) {
        double max = 0;
        Collection<? extends DataItem> items = getAllItemsByType(type);
        for(DataItem d : items)
        {
            if(d.getValue()>max)
                max = d.getValue();
        }
        return max;
    }

    @Override
    public double getMinimumDate(DataItemType type) {
        double min = Double.MAX_VALUE;
        Collection<? extends DataItem> items = getAllItemsByType(type);
        for(DataItem d : items)
        {
            if(d.getDate()<min)
                min = d.getDate();
        }
        return min;
    }

    @Override
    public double getMaximumDate(DataItemType type) {
        double max = 0;
        Collection<? extends DataItem> items = getAllItemsByType(type);
        for(DataItem d : items)
        {
            if(d.getDate()>max)
                max = d.getDate();
        }
        return max;
    }

    @Override
    public void deleteDataItem(DataItem item) {
        dataItems.get(item.getItemType()).remove(item);
    }

    private long getDate(int year, int month, int day, int hour, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day, hour, minute);
        return c.getTimeInMillis();
    }
}
