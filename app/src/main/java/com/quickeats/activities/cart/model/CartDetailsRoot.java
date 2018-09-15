package com.quickeats.activities.cart.model;

import java.util.ArrayList;

/**
 * Created by Rajesh kumar on 15-09-2018.
 */

public class CartDetailsRoot {
    private boolean error;

    private ArrayList<GetingCartData> GetingCartData;

    public boolean getError ()
    {
        return error;
    }

    public void setError (boolean error)
    {
        this.error = error;
    }

    public ArrayList<GetingCartData> getGetingCartData ()
    {
        return GetingCartData;
    }

    public void setGetingCartData (ArrayList<GetingCartData> GetingCartData)
    {
        this.GetingCartData = GetingCartData;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [error = "+error+", GetingCartData = "+GetingCartData+"]";
    }
}
