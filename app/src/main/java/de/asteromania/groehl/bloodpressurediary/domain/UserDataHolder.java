package de.asteromania.groehl.bloodpressurediary.domain;

import android.app.ActivityManager;

import java.util.List;

import de.asteromania.groehl.bloodpressurediary.database.UserDataAccess;
import de.asteromania.groehl.bloodpressurediary.database.UserDataAccessDummyImpl;

/**
 * Created by jgroehl on 11.09.16.
 */
public class UserDataHolder
{

    private double height;

    private List<DataItemType> trackedData;

    private UserDataAccess userDataAccess = new UserDataAccessDummyImpl();

    public UserDataHolder()
    {
        if(!userDataAccess.dataAvailable())
        {
            //Start welcome activity.
        }
    }
}
