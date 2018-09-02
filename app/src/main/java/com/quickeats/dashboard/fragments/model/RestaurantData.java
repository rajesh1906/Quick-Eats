package com.quickeats.dashboard.fragments.model;

/**
 * Created by Rajesh kumar on 02-09-2018.
 */

public class RestaurantData {
    private String Res_id;

    private String restaurantname;

    private String mobilenumber;

    private String PreparationTimeDuration;

    private String sl_no;

    private String restaurantnameurl;

    private String Currency;

    public String getRes_id ()
    {
        return Res_id;
    }

    public void setRes_id (String Res_id)
    {
        this.Res_id = Res_id;
    }

    public String getRestaurantname ()
    {
        return restaurantname;
    }

    public void setRestaurantname (String restaurantname)
    {
        this.restaurantname = restaurantname;
    }

    public String getMobilenumber ()
    {
        return mobilenumber;
    }

    public void setMobilenumber (String mobilenumber)
    {
        this.mobilenumber = mobilenumber;
    }

    public String getPreparationTimeDuration ()
    {
        return PreparationTimeDuration;
    }

    public void setPreparationTimeDuration (String PreparationTimeDuration)
    {
        this.PreparationTimeDuration = PreparationTimeDuration;
    }

    public String getSl_no ()
    {
        return sl_no;
    }

    public void setSl_no (String sl_no)
    {
        this.sl_no = sl_no;
    }

    public String getRestaurantnameurl ()
    {
        return restaurantnameurl;
    }

    public void setRestaurantnameurl (String restaurantnameurl)
    {
        this.restaurantnameurl = restaurantnameurl;
    }

    public String getCurrency ()
    {
        return Currency;
    }

    public void setCurrency (String Currency)
    {
        this.Currency = Currency;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Res_id = "+Res_id+", restaurantname = "+restaurantname+", mobilenumber = "+mobilenumber+", PreparationTimeDuration = "+PreparationTimeDuration+", sl_no = "+sl_no+", restaurantnameurl = "+restaurantnameurl+", Currency = "+Currency+"]";
    }
}
