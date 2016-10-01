package de.asteromania.groehl.bloodpressurediary.database;

import android.content.Context;

import java.util.HashMap;

import de.asteromania.groehl.bloodpressurediary.domain.DataItem;

/**
 * Created by jgroehl on 26.09.16.
 */
public final class DatabaseService {


    private DataItemDatabaseAccess dataItemDatabaseAccess;
    private UserDatabaseAccess userDatabaseAccess;

    public DatabaseService(Context context)
    {
        dataItemDatabaseAccess = new DataItemSqLiteDatabase(context);
        userDatabaseAccess = new UserDatabaseAccessDummyImpl();
    }

    public DataItemDatabaseAccess getDataItemDatabaseAccess()
    {
       return dataItemDatabaseAccess;
    }

    public  UserDatabaseAccess getUserDataAccess()
    {
        return userDatabaseAccess;
    }
}
