package de.asteromania.groehl.bloodpressurediary.database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import de.asteromania.groehl.bloodpressurediary.domain.DataItem;
import de.asteromania.groehl.bloodpressurediary.domain.DataItemType;

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
}
