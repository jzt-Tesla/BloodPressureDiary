package de.asteromania.groehl.bloodpressurediary.database;

import android.provider.BaseColumns;

/**
 * Created by jgroehl on 11.09.16.
 */
public final class DataItemDatabaseContract
{
    private DataItemDatabaseContract()
    {
        // This is supposed to be an abstract class.
    }

    public static class DataItemColumns implements BaseColumns {
        public static final String TABLE_NAME = "dataItems";
        public static final String COLUMN_NAME_DATA_TYPE = "dataType";
        public static final String COLUMN_NAME_VALUE = "value";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_DAY_DATE = "dayDate";
    }
}
