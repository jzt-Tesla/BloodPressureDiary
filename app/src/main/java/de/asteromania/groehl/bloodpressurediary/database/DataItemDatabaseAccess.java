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

    /**
     *
     * @return
     */
    Collection<? extends ListViewItem> getFloatingMeansOfDataItems(Collection<? extends DataItemType> trackedDataItemTypes);

    /**
     *
     * @param type
     * @return
     */
    double getMaximumValue(DataItemType type);

    /**
     *
     * @param type
     * @return
     */
    double getMinimumDate(DataItemType type);

    /**
     *
     * @param type
     * @return
     */
    double getMaximumDate(DataItemType type);
}
