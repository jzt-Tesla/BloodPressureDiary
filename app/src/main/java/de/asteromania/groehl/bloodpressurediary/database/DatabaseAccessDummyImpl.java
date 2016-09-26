package de.asteromania.groehl.bloodpressurediary.database;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import de.asteromania.groehl.bloodpressurediary.domain.DataItem;
import de.asteromania.groehl.bloodpressurediary.domain.DataItemTrend;
import de.asteromania.groehl.bloodpressurediary.domain.DataItemType;
import de.asteromania.groehl.bloodpressurediary.domain.ListViewItem;

/**
 * Created by jgroehl on 11.09.16.
 */
public class DatabaseAccessDummyImpl implements DatabaseAccess
{
    private static final String TAG = "DatabaseAccessDummyImpl" ;
    private HashMap<DataItemType, ArrayList<DataItem>> dataItems;

    DatabaseAccessDummyImpl()
    {
        dataItems = new HashMap<>();
        for(DataItemType type : DataItemType.values())
        {
            dataItems.put(type, new ArrayList<DataItem>());
        }
        dataItems.get(DataItemType.SYSTOLE).add(0, new DataItem(DataItemType.SYSTOLE, 144, new Date().getTime()));
        dataItems.get(DataItemType.SYSTOLE).add(0, new DataItem(DataItemType.SYSTOLE, 139, new Date().getTime()));
        dataItems.get(DataItemType.SYSTOLE).add(0, new DataItem(DataItemType.SYSTOLE, 133, new Date().getTime()));
        dataItems.get(DataItemType.SYSTOLE).add(0, new DataItem(DataItemType.SYSTOLE, 120, new Date().getTime()));
        dataItems.get(DataItemType.DIASTOLE).add(0, new DataItem(DataItemType.DIASTOLE, 96, new Date().getTime()));
        dataItems.get(DataItemType.DIASTOLE).add(0, new DataItem(DataItemType.DIASTOLE, 104, new Date().getTime()));
        dataItems.get(DataItemType.DIASTOLE).add(0, new DataItem(DataItemType.DIASTOLE, 99, new Date().getTime()));
        dataItems.get(DataItemType.DIASTOLE).add(0, new DataItem(DataItemType.DIASTOLE, 100, new Date().getTime()));
        dataItems.get(DataItemType.HEARTRATE).add(0, new DataItem(DataItemType.HEARTRATE, 86, new Date().getTime()));
        dataItems.get(DataItemType.HEARTRATE).add(0, new DataItem(DataItemType.HEARTRATE, 84, new Date().getTime()));
        dataItems.get(DataItemType.WEIGHT).add(0, new DataItem(DataItemType.WEIGHT, 120, new Date().getTime()));
        dataItems.get(DataItemType.WEIGHT).add(0, new DataItem(DataItemType.WEIGHT, 123, new Date().getTime()));
    }

    @Override
    public boolean addItem(DataItem item) {
        dataItems.get(item.getItemType()).add(0, item);
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
        return returnList.subList(0, n > size ? n : size);
    }

    @Override
    public Collection<? extends ListViewItem> getFloatingMeansOfAllTrackedDataItems() {

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
                double ratio = typeList.get(0).getValue() / typeList.get(1).getValue();
                Log.i(TAG, ""+ratio);
                if (ratio > 1)
                    trend = DataItemTrend.NEGATIVE;
                else if(ratio < 1)
                    trend  = DataItemTrend.POSITIVE;
            }
            returnList.add(new ListViewItem(type, mean, trend));
        }
        return returnList;
    }
}
