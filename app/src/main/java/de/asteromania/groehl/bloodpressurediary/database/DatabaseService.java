package de.asteromania.groehl.bloodpressurediary.database;

/**
 * Created by jgroehl on 26.09.16.
 */
public final class DatabaseService {

    private DatabaseService(){}

    private static DataItemDatabaseAccess dataItemDatabaseAccessInstance = new DataItemDatabaseAccessDummyImpl();
    private static UserDatabaseAccess userDatabaseAccessInstance = new UserDatabaseAccessDummyImpl();

    public static DataItemDatabaseAccess getDataItemDatabaseAccess()
    {
       return dataItemDatabaseAccessInstance;
    }

    public static UserDatabaseAccess getUserDataAccess()
    {
        return userDatabaseAccessInstance;
    }
}
