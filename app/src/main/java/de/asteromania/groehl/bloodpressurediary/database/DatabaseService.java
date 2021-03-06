package de.asteromania.groehl.bloodpressurediary.database;

import android.content.Context;

/**
 * Created by jgroehl on 26.09.16.
 */
public final class DatabaseService {


    private DataItemDatabaseAccess dataItemDatabaseAccess;
    private UserDatabaseAccess userDatabaseAccess;

    public DatabaseService(Context context)
    {
        dataItemDatabaseAccess = new DataItemSqLiteDatabase(context);
        userDatabaseAccess = new UserDatabaseFileImplementation(context);
    }

    public DataItemDatabaseAccess getDataItemDatabaseAccess()
    {
       return dataItemDatabaseAccess;
    }

    public UserDatabaseAccess getUserDataAccess()
    {
        return userDatabaseAccess;
    }
}
