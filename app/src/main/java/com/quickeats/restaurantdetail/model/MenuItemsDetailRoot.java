package com.quickeats.restaurantdetail.model;

import java.util.ArrayList;

/**
 * Created by Rajesh kumar on 04-09-2018.
 */

public class MenuItemsDetailRoot {
    private String Status;

    private ArrayList<ItemsData> ItemsData;

    private String error;

    public String getStatus ()
    {
        return Status;
    }

    public void setStatus (String Status)
    {
        this.Status = Status;
    }

    public ArrayList<ItemsData> getItemsData ()
    {
        return ItemsData;
    }

    public void setItemsData (ArrayList<ItemsData> ItemsData)
    {
        this.ItemsData = ItemsData;
    }

    public String getError ()
    {
        return error;
    }

    public void setError (String error)
    {
        this.error = error;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Status = "+Status+", ItemsData = "+ItemsData+", error = "+error+"]";
    }
}
