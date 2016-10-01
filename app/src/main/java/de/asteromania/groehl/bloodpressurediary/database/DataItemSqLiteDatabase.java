package de.asteromania.groehl.bloodpressurediary.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.asteromania.groehl.bloodpressurediary.domain.DataItem;
import de.asteromania.groehl.bloodpressurediary.domain.DataItemTrend;
import de.asteromania.groehl.bloodpressurediary.domain.DataItemType;
import de.asteromania.groehl.bloodpressurediary.domain.ListViewItem;

/**
 * Created by jgroehl on 01.10.16.
 */

public class DataItemSqLiteDatabase implements DataItemDatabaseAccess {

    private static final int NUMBER_OF_FLOATING_ITEMS = 20;
    private static final long DAY_FACTOR = 1000*60*60*24;
    private DataItemDbHelper dataItemDbHelper;

    public DataItemSqLiteDatabase(Context context)
    {
        dataItemDbHelper = new DataItemDbHelper(context);
    }

    @Override
    public boolean addItem(DataItem item)
    {

        SQLiteDatabase db = dataItemDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataItemDatabaseContract.DataItemColumns.COLUMN_NAME_DATA_TYPE, item.getItemType().toString());
        contentValues.put(DataItemDatabaseContract.DataItemColumns.COLUMN_NAME_DATE, item.getDate());
        long dayDate = (item.getDate()/DAY_FACTOR)*DAY_FACTOR;
        contentValues.put(DataItemDatabaseContract.DataItemColumns.COLUMN_NAME_DAY_DATE, dayDate);
        contentValues.put(DataItemDatabaseContract.DataItemColumns.COLUMN_NAME_VALUE, item.getValue());
        long row = db.insert(DataItemDatabaseContract.DataItemColumns.TABLE_NAME, null, contentValues);
        return row != -1;
    }

    @Override
    public Collection<? extends DataItem> getAllItemsByType(DataItemType type)
    {
        return getLastNItemsByType(Integer.MAX_VALUE, type);
    }

    @Override
    public Collection<? extends DataItem> getLastNItemsByType(int n, DataItemType type)
    {
        SQLiteDatabase db = dataItemDbHelper.getReadableDatabase();

        String nameOfFirstRow = "AVG("+DataItemDatabaseContract.DataItemColumns.COLUMN_NAME_VALUE+")";

        String[] projection = {
                nameOfFirstRow,
                DataItemDatabaseContract.DataItemColumns.COLUMN_NAME_DATE,
                DataItemDatabaseContract.DataItemColumns.COLUMN_NAME_DATA_TYPE
        };

        String selection = DataItemDatabaseContract.DataItemColumns.COLUMN_NAME_DATA_TYPE + " = ?";
        String[] selectionArgs = { type.toString() };
        String groupByArgs = DataItemDatabaseContract.DataItemColumns.COLUMN_NAME_DAY_DATE;

        String sortOrder =
                DataItemDatabaseContract.DataItemColumns.COLUMN_NAME_DATE + " ASC";

        Cursor c = db.query(
                DataItemDatabaseContract.DataItemColumns.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                groupByArgs,                              // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        ArrayList<DataItem> dataItems = new ArrayList<>(c.getCount());

        int index = 0;
        while (c.moveToNext() && index < n)
        {
            dataItems.add(new DataItem(DataItemType.valueOf(c.getString(c.getColumnIndexOrThrow(DataItemDatabaseContract.DataItemColumns.COLUMN_NAME_DATA_TYPE))),
                    c.getDouble(c.getColumnIndexOrThrow(nameOfFirstRow)) ,
                    c.getLong(c.getColumnIndexOrThrow(DataItemDatabaseContract.DataItemColumns.COLUMN_NAME_DATE)) ));
            index++;
        }

        return dataItems;
    }

    @Override
    public Collection<? extends ListViewItem> getFloatingMeansOfDataItems(Collection<? extends DataItemType> trackedDataItemTypes)
    {
        ArrayList<ListViewItem> returnList = new ArrayList<>();

        if(trackedDataItemTypes==null || trackedDataItemTypes.isEmpty())
            return returnList;

        for(DataItemType type : trackedDataItemTypes)
        {
            ListViewItem item = calculateListViewItem(((List<DataItem>) getLastNItemsByType(NUMBER_OF_FLOATING_ITEMS, type)));
            if(item != null)
                returnList.add(item);
        }

        return returnList;
    }

    private ListViewItem calculateListViewItem(List<DataItem> dataItems)
    {
        double value = 0;
        int n = 0;
        double sumOfDifferences = 0;

        if (dataItems==null || dataItems.isEmpty())
        {
            return null;
        }

        for(DataItem item : dataItems)
        {
            value += item.getValue();
            n++;
        }

        double mean = value / n;

        List<DataItem> subsetItems = dataItems.subList(0, dataItems.size()/2);

        for(DataItem item : subsetItems)
        {
            sumOfDifferences += (mean - item.getValue());
        }

        DataItemTrend trend = DataItemTrend.NEUTRAL;

        if(sumOfDifferences/mean>0.05)
        {
            trend = DataItemTrend.NEGATIVE;
        }
        else if(sumOfDifferences/mean < -0.05)
        {
            trend = DataItemTrend.POSITIVE;
        }

        return new ListViewItem(dataItems.get(0).getItemType(), ((int) (mean*10))/10.0, trend);
    }

    @Override
    public double getMaximumValue(DataItemType type)
    {
        SQLiteDatabase db = dataItemDbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT MAX("+ DataItemDatabaseContract.DataItemColumns.COLUMN_NAME_VALUE+") " +
                "FROM "+DataItemDatabaseContract.DataItemColumns.TABLE_NAME, null);
        if(c.moveToFirst())
        {
            return c.getDouble(0);
        } else {
            return 0;
        }
    }

    @Override
    public double getMinimumDate(DataItemType type)
    {
        SQLiteDatabase db = dataItemDbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT MIN("+ DataItemDatabaseContract.DataItemColumns.COLUMN_NAME_DATE+") " +
                "FROM "+DataItemDatabaseContract.DataItemColumns.TABLE_NAME, null);
        if(c.moveToFirst())
        {
            return c.getDouble(0);
        } else {
            return 0;
        }
    }

    @Override
    public double getMaximumDate(DataItemType type)
    {
        SQLiteDatabase db = dataItemDbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT MAX("+ DataItemDatabaseContract.DataItemColumns.COLUMN_NAME_DATE+") " +
                "FROM "+DataItemDatabaseContract.DataItemColumns.TABLE_NAME, null);
        if(c.moveToFirst())
        {
            return c.getDouble(0);
        } else {
            return 0;
        }
    }
}
