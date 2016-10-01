package de.asteromania.groehl.bloodpressurediary.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collection;

import de.asteromania.groehl.bloodpressurediary.domain.DataItem;
import de.asteromania.groehl.bloodpressurediary.domain.DataItemType;
import de.asteromania.groehl.bloodpressurediary.domain.ListViewItem;

/**
 * Created by jgroehl on 01.10.16.
 */

public class DataItemSqLiteDatabase implements DataItemDatabaseAccess {

    private DataItemDbHelper dataItemDbHelper;

    public DataItemSqLiteDatabase(Context context)
    {
        dataItemDbHelper = new DataItemDbHelper(context);
    }

    @Override
    public boolean addItem(DataItem item) {

        SQLiteDatabase db = dataItemDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataItemDatabaseContract.DataItemColumns.COLUMN_NAME_DATA_TYPE, item.getItemType().toString());
        contentValues.put(DataItemDatabaseContract.DataItemColumns.COLUMN_NAME_DATE, item.getDate());
        contentValues.put(DataItemDatabaseContract.DataItemColumns.COLUMN_NAME_VALUE, item.getValue());
        long row = db.insert(DataItemDatabaseContract.DataItemColumns.TABLE_NAME, null, contentValues);
        return row != -1;
    }

    @Override
    public Collection<? extends DataItem> getAllItemsByType(DataItemType type) {



        SQLiteDatabase db = dataItemDbHelper.getReadableDatabase();

        String[] projection = {
                DataItemDatabaseContract.DataItemColumns._ID,
                DataItemDatabaseContract.DataItemColumns.COLUMN_NAME_DATA_TYPE,
                DataItemDatabaseContract.DataItemColumns.COLUMN_NAME_DATE,
                DataItemDatabaseContract.DataItemColumns.COLUMN_NAME_VALUE
        };

        String selection = DataItemDatabaseContract.DataItemColumns.COLUMN_NAME_DATA_TYPE + " = ?";
        String[] selectionArgs = { type.toString() };

        String sortOrder =
                DataItemDatabaseContract.DataItemColumns.COLUMN_NAME_DATE + " ASC";

        Cursor c = db.query(
                DataItemDatabaseContract.DataItemColumns.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        ArrayList<DataItem> dataItems = new ArrayList<>(c.getCount());

        while (c.moveToNext())
        {
            dataItems.add(new DataItem(DataItemType.valueOf(c.getString(c.getColumnIndexOrThrow(DataItemDatabaseContract.DataItemColumns.COLUMN_NAME_DATA_TYPE))),
                    c.getDouble(c.getColumnIndexOrThrow(DataItemDatabaseContract.DataItemColumns.COLUMN_NAME_VALUE)) ,
                    c.getLong(c.getColumnIndexOrThrow(DataItemDatabaseContract.DataItemColumns.COLUMN_NAME_DATE)) ));
        }

        return dataItems;
    }

    @Override
    public Collection<? extends DataItem> getLastNItemsByType(int n, DataItemType type) {
        ArrayList<DataItem> returnList = new ArrayList<>();
        return returnList;
    }

    @Override
    public Collection<? extends ListViewItem> getFloatingMeansOfAllTrackedDataItems() {
        ArrayList<ListViewItem> returnList = new ArrayList<>();
        return returnList;
    }

    @Override
    public double getMaximumValue(DataItemType type) {
        return 0;
    }

    @Override
    public double getMinimumDate(DataItemType type) {
        return 0;
    }

    @Override
    public double getMaximumDate(DataItemType type) {
        return 0;
    }
}
