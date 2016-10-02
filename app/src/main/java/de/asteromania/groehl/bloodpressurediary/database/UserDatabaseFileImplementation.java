package de.asteromania.groehl.bloodpressurediary.database;

import android.content.Context;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.asteromania.groehl.bloodpressurediary.domain.DataItemType;

/**
 * Created by jgroehl on 02.10.16.
 */

public class UserDatabaseFileImplementation implements UserDatabaseAccess {

    private static String FILE_HEIGHT = "height";
    private static String FILE_BIRTHDATE = "birthdate";
    private static String FILE_NAME = "name";
    private static String FILE_TRACKED_VALUES = "trackedValues";
    private static String SEPARATOR_TRACKED_VALUE = ",";

    private Context context;

    public UserDatabaseFileImplementation(Context context)
    {
        this.context = context;
    }


    @Override
    public boolean dataAvailable() {
        String result = FileService.readFromFile(FILE_TRACKED_VALUES, context);
        return result != null;
    }

    @Override
    public double getHeight() {
        String result = FileService.readFromFile(FILE_HEIGHT, context);
        if(result!=null)
            return Double.valueOf(result);
        else
            return -1;
    }

    @Override
    public void setHeight(double height) {
        FileService.writeToFile(FILE_HEIGHT, String.valueOf(height), context);
    }

    @Override
    public Date getBirthdate() {
        String result = FileService.readFromFile(FILE_BIRTHDATE, context);
        if(result!=null)
            return new Date(Long.valueOf(result));
        else
            return new Date();
    }

    @Override
    public void setBirthdate(Date birthdate)
    {
        FileService.writeToFile(FILE_BIRTHDATE, String.valueOf(birthdate.getTime()), context);
    }

    @Override
    public List<DataItemType> getTrackedValues()
    {
        String result = FileService.readFromFile(FILE_TRACKED_VALUES, context);
        if(result!=null)
        {
            ArrayList<DataItemType> resultList = new ArrayList<>();
            for(String s : result.split(SEPARATOR_TRACKED_VALUE))
            {
                if(!s.isEmpty())
                resultList.add(DataItemType.valueOf(s));
            }
            return resultList;
        }
        else
            return new ArrayList<>();
    }

    @Override
    public void setTrackedValues(List<DataItemType> trackedValues)
    {
        String trackedValuesFileContent = "";
        for(DataItemType type : trackedValues)
            trackedValuesFileContent+=type.toString()+SEPARATOR_TRACKED_VALUE;

        FileService.writeToFile(FILE_TRACKED_VALUES, trackedValuesFileContent, context);
    }

    @Override
    public String getName()
    {
        String result = FileService.readFromFile(FILE_NAME, context);
        if(result!=null)
            return result;
        else
            return "";
    }

    @Override
    public void setName(String name)
    {
        FileService.writeToFile(FILE_NAME, name, context);
    }
}
