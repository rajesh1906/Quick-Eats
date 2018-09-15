package com.quickeats.activities.cart.model;

import java.util.ArrayList;

/**
 * Created by Rajesh kumar on 15-09-2018.
 */

public class GetingCartData {
    private String Name;

    private ArrayList<Restaurantlist> restaurantlist;

    public String getName ()
    {
        return Name;
    }

    public void setName (String Name)
    {
        this.Name = Name;
    }

    public ArrayList<Restaurantlist> getRestaurantlist ()
    {
        return restaurantlist;
    }

    public void setRestaurantlist (ArrayList<Restaurantlist> restaurantlist)
    {
        this.restaurantlist = restaurantlist;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Name = "+Name+", restaurantlist = "+restaurantlist+"]";
    }
}
