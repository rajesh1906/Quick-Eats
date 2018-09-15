package com.quickeats.activities.cart.model;

/**
 * Created by Rajesh kumar on 15-09-2018.
 */

public class Restaurantlist {
    private String Amount;

    private String  restaurantname;

    private String ItemName;

    private String qty;

    private String itemid;

    private String date;

    private String Res_Id;

    public String getAmount ()
    {
        return Amount;
    }

    public void setAmount (String Amount)
    {
        this.Amount = Amount;
    }

    public String getRestaurantname ()
    {
        return restaurantname;
    }

    public void setRestaurantname (String restaurantname)
    {
        this.restaurantname = restaurantname;
    }

    public String getItemName ()
    {
        return ItemName;
    }

    public void setItemName (String ItemName)
    {
        this.ItemName = ItemName;
    }

    public String getQty ()
    {
        return qty;
    }

    public void setQty (String qty)
    {
        this.qty = qty;
    }

    public String getItemid ()
    {
        return itemid;
    }

    public void setItemid (String itemid)
    {
        this.itemid = itemid;
    }

    public String getDate ()
    {
        return date;
    }

    public void setDate (String date)
    {
        this.date = date;
    }

    public String getRes_Id ()
    {
        return Res_Id;
    }

    public void setRes_Id (String Res_Id)
    {
        this.Res_Id = Res_Id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Amount = "+Amount+", restaurantname = "+restaurantname+", ItemName = "+ItemName+", qty = "+qty+", itemid = "+itemid+", date = "+date+", Res_Id = "+Res_Id+"]";
    }
}
