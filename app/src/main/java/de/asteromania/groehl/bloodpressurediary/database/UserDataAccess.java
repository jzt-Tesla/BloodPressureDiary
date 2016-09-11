package de.asteromania.groehl.bloodpressurediary.database;

import java.util.Date;
import java.util.List;

import de.asteromania.groehl.bloodpressurediary.domain.DataItemType;

/**
 * Created by jgroehl on 11.09.16.
 */
public interface UserDataAccess
{
    /**
     *
     * @return
     */
    boolean dataAvailable();

    /**
     *
     * @return
     */
    double getHeight();

    /**
     *
     * @param height
     */
    void setHeight(double height);

    /**
     *
     * @return
     */
    Date getBirthdate();

    /**
     *
     * @param birthdate
     */
    void setBirthdate(Date birthdate);

    /**
     *
     * @return
     */
    List<DataItemType> getTrackedValues();

    /**
     *
     * @param trackedValues
     */
    void setTrackedValues(List<DataItemType> trackedValues);

    /**
     *
     * @param dataItemType
     * @return
     */
    double getCurrentFloatingMean(DataItemType dataItemType);

    /**
     *
     * @param dataItemType
     * @param floatingMean
     */
    void setCurrentFloatingMean(DataItemType dataItemType, double floatingMean);
}
