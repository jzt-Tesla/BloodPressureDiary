package de.asteromania.groehl.bloodpressurediary.database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import de.asteromania.groehl.bloodpressurediary.domain.DataItem;
import de.asteromania.groehl.bloodpressurediary.domain.DataItemTrend;
import de.asteromania.groehl.bloodpressurediary.domain.DataItemType;
import de.asteromania.groehl.bloodpressurediary.domain.ListViewItem;

/**
 * Created by jgroehl on 11.09.16.
 */
public class DatabaseAccessDummyImpl implements DatabaseAccess
{
    @Override
    public boolean addItem(DataItem item) {
        return true;
    }

    @Override
    public Collection<? extends DataItem> getAllItemsByType(DataItemType type) {
        ArrayList<DataItem> returnList = new ArrayList<>();
        switch(type) {
            case SYSTOLE:
            returnList.add(new DataItem(DataItemType.SYSTOLE, 144, new Date().getTime()));
            returnList.add(new DataItem(DataItemType.SYSTOLE, 139, new Date().getTime()));
            returnList.add(new DataItem(DataItemType.SYSTOLE, 133, new Date().getTime()));
            returnList.add(new DataItem(DataItemType.SYSTOLE, 120, new Date().getTime()));
                break;
            case DIASTOLE:
                returnList.add(new DataItem(DataItemType.DIASTOLE, 96, new Date().getTime()));
                returnList.add(new DataItem(DataItemType.DIASTOLE, 104, new Date().getTime()));
                returnList.add(new DataItem(DataItemType.DIASTOLE, 99, new Date().getTime()));
                returnList.add(new DataItem(DataItemType.DIASTOLE, 100, new Date().getTime()));
                break;
            case HEARTRATE:
                returnList.add(new DataItem(DataItemType.HEARTRATE, 86, new Date().getTime()));
                returnList.add(new DataItem(DataItemType.HEARTRATE, 84, new Date().getTime()));
                break;
            case WEIGHT:
                returnList.add(new DataItem(DataItemType.WEIGHT, 120, new Date().getTime()));
                returnList.add(new DataItem(DataItemType.WEIGHT, 123, new Date().getTime()));
                break;
        }
        return returnList;
    }

    @Override
    public Collection<? extends DataItem> getLastNItemsByType(int n, DataItemType type) {
        ArrayList<DataItem> returnList = new ArrayList<>();
        switch(type) {
            case SYSTOLE:
                returnList.add(new DataItem(DataItemType.SYSTOLE, 144, new Date().getTime()));
                returnList.add(new DataItem(DataItemType.SYSTOLE, 139, new Date().getTime()));
                returnList.add(new DataItem(DataItemType.SYSTOLE, 133, new Date().getTime()));
                returnList.add(new DataItem(DataItemType.SYSTOLE, 120, new Date().getTime()));
                break;
            case DIASTOLE:
                returnList.add(new DataItem(DataItemType.DIASTOLE, 96, new Date().getTime()));
                returnList.add(new DataItem(DataItemType.DIASTOLE, 104, new Date().getTime()));
                returnList.add(new DataItem(DataItemType.DIASTOLE, 99, new Date().getTime()));
                returnList.add(new DataItem(DataItemType.DIASTOLE, 100, new Date().getTime()));
                break;
            case HEARTRATE:
                returnList.add(new DataItem(DataItemType.HEARTRATE, 86, new Date().getTime()));
                returnList.add(new DataItem(DataItemType.HEARTRATE, 84, new Date().getTime()));
                break;
            case WEIGHT:
                returnList.add(new DataItem(DataItemType.WEIGHT, 120, new Date().getTime()));
                returnList.add(new DataItem(DataItemType.WEIGHT, 123, new Date().getTime()));
                break;
        }
        return returnList;
    }

    @Override
    public Collection<? extends ListViewItem> getFloatingMeansOfAllTrackedDataItems() {
        ArrayList<ListViewItem> returnList = new ArrayList<>();
        returnList.add(new ListViewItem(DataItemType.SYSTOLE, 140, DataItemTrend.POSITIVE));
        returnList.add(new ListViewItem(DataItemType.DIASTOLE, 100, DataItemTrend.NEUTRAL));
        returnList.add(new ListViewItem(DataItemType.HEARTRATE, 82, DataItemTrend.NEGATIVE));
        returnList.add(new ListViewItem(DataItemType.WEIGHT, 97, DataItemTrend.NEUTRAL));
        return returnList;
    }
}
