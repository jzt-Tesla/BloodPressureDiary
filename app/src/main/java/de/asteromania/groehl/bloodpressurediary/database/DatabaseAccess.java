package de.asteromania.groehl.bloodpressurediary.database;

import java.util.Collection;

import de.asteromania.groehl.bloodpressurediary.domain.DataItem;
import de.asteromania.groehl.bloodpressurediary.domain.DataItemType;

/**
 * Created by jgroehl on 11.09.16.
 */
public interface DatabaseAccess
{
    boolean addItem(DataItem item);

    Collection<? extends DataItem> getAllItemsByType(DataItemType type);

    Collection<? extends DataItem> getLastNItemsByType(int n, DataItemType type);
}
