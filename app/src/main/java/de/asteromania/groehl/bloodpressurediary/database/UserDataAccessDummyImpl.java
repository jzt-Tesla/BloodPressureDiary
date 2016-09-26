package de.asteromania.groehl.bloodpressurediary.database;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.asteromania.groehl.bloodpressurediary.domain.DataItemType;

/**
 * Created by jgroehl on 11.09.16.
 */
public class UserDataAccessDummyImpl implements UserDataAccess
{
    UserDataAccessDummyImpl()
    {

    }

    @Override
    public boolean dataAvailable() {
        return true;
    }

    @Override
    public double getHeight() {
        return 1.87;
    }

    @Override
    public void setHeight(double height) {

    }

    @Override
    public Date getBirthdate() {
        return new Date();
    }

    @Override
    public void setBirthdate(Date birthdate) {

    }

    @Override
    public List<DataItemType> getTrackedValues() {
        ArrayList<DataItemType> trackedTypes = new ArrayList<>();
        trackedTypes.add(DataItemType.SYSTOLE);
        trackedTypes.add(DataItemType.HEARTRATE);
        trackedTypes.add(DataItemType.WEIGHT);
        return trackedTypes;
    }

    @Override
    public void setTrackedValues(List<DataItemType> trackedValues) {

    }

    @Override
    public double getCurrentFloatingMean(DataItemType dataItemType) {
        return 130;
    }

    @Override
    public void setCurrentFloatingMean(DataItemType dataItemType, double floatingMean) {

    }
}
