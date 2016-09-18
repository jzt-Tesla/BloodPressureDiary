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
        returnList.add(new DataItem(DataItemType.SYSTOLE, 144, new Date().getTime()));
        returnList.add(new DataItem(DataItemType.SYSTOLE, 139, new Date().getTime()));
        returnList.add(new DataItem(DataItemType.SYSTOLE, 133, new Date().getTime()));
        returnList.add(new DataItem(DataItemType.SYSTOLE, 120, new Date().getTime()));
        return returnList;
    }

    @Override
    public Collection<? extends DataItem> getLastNItemsByType(int n, DataItemType type) {
        ArrayList<DataItem> returnList = new ArrayList<>();
        returnList.add(new DataItem(DataItemType.SYSTOLE, 144, new Date().getTime()));
        returnList.add(new DataItem(DataItemType.SYSTOLE, 139, new Date().getTime()));
        returnList.add(new DataItem(DataItemType.SYSTOLE, 133, new Date().getTime()));
        returnList.add(new DataItem(DataItemType.SYSTOLE, 120, new Date().getTime()));
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
