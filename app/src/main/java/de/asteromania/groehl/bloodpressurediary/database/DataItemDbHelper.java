package de.asteromania.groehl.bloodpressurediary.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jgroehl on 11.09.16.
 */
public class DataItemDbHelper extends SQLiteOpenHelper
{
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DataItems.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String REAL_TYPE = " REAL";
    private static final String DATE_TYPE = "INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DataItemDatabaseContract.DataItemColumns.TABLE_NAME + " (" +
                    DataItemDatabaseContract.DataItemColumns._ID + " INTEGER PRIMARY KEY," +
                    DataItemDatabaseContract.DataItemColumns.COLUMN_NAME_DATA_TYPE + TEXT_TYPE + COMMA_SEP +
                    DataItemDatabaseContract.DataItemColumns.COLUMN_NAME_VALUE + REAL_TYPE + COMMA_SEP +
                    DataItemDatabaseContract.DataItemColumns.COLUMN_NAME_DATE + DATE_TYPE + " )";

    public DataItemDbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        //Nothing to do as of yet..
    }
}
