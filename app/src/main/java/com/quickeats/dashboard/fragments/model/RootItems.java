package com.quickeats.dashboard.fragments.model;

import java.util.ArrayList;

/**
 * Created by Rajesh kumar on 02-09-2018.
 */

public class RootItems {
    private String Status;

    private String error;

    private ArrayList<RestaurantData> RestaurantData;

    public String getStatus ()
    {
        return Status;
    }

    public void setStatus (String Status)
    {
        this.Status = Status;
    }

    public String getError ()
    {
        return error;
    }

    public void setError (String error)
    {
        this.error = error;
    }

    public ArrayList<RestaurantData> getRestaurantData ()
    {
        return RestaurantData;
    }

    public void setRestaurantData (ArrayList<RestaurantData> RestaurantData)
    {
        this.RestaurantData = RestaurantData;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Status = "+Status+", error = "+error+", RestaurantData = "+RestaurantData+"]";
    }
}
