package de.asteromania.groehl.bloodpressurediary.database;

import java.util.Collection;

import de.asteromania.groehl.bloodpressurediary.domain.DataItem;
import de.asteromania.groehl.bloodpressurediary.domain.DataItemType;
import de.asteromania.groehl.bloodpressurediary.domain.ListViewItem;

/**
 * Created by jgroehl on 11.09.16.
 */
public interface DataItemDatabaseAccess
{
    /**
     *
     * @param item
     * @return
     */
    boolean addItem(DataItem item);

    /**
     *
     * @param type
     * @return
     */
    Collection<? extends DataItem> getAllItemsByType(DataItemType type);

    /**
     *
     * @param n
     * @param type
     * @return
     */
    Collection<? extends DataItem> getLastNItemsByType(int n, DataItemType type);

    Collection<? extends ListViewItem> getFloatingMeansOfAllTrackedDataItems();

    double getMaximumValue(DataItemType type);

    double getMinimumDate(DataItemType type);

    double getMaximumDate(DataItemType type);
}
