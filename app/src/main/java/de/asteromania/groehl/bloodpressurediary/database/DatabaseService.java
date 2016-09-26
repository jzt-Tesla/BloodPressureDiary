package de.asteromania.groehl.bloodpressurediary.database;

/**
 * Created by jgroehl on 26.09.16.
 */
public final class DatabaseService {

    private DatabaseService(){}

    private static DatabaseAccess databaseAccessInstance = new DatabaseAccessDummyImpl();
    private static UserDataAccess userDataAccessInstance = new UserDataAccessDummyImpl();

    public static DatabaseAccess getDatabaseAccess()
    {
       return databaseAccessInstance;
    }

    public static UserDataAccess getUserDataAccess()
    {
        return userDataAccessInstance;
    }
}
