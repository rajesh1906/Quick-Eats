package com.quickeats.restaurantdetail.model;

/**
 * Created by Rajesh kumar on 04-09-2018.
 */

public class ItemsData {
    private String Description;

    private String Amount;

    private String RestaurantID;

    private String MenuName;

    private String NumberofQtys;

    private String SubmenuId;

    private String ItemName;

    private String menuid;

    private String SubMenuName;

    private String Item_Id;

    private String ItemUrl;

    public String getDescription ()
    {
        return Description;
    }

    public void setDescription (String Description)
    {
        this.Description = Description;
    }

    public String getAmount ()
    {
        return Amount;
    }

    public void setAmount (String Amount)
    {
        this.Amount = Amount;
    }

    public String getRestaurantID ()
    {
        return RestaurantID;
    }

    public void setRestaurantID (String RestaurantID)
    {
        this.RestaurantID = RestaurantID;
    }

    public String getMenuName ()
    {
        return MenuName;
    }

    public void setMenuName (String MenuName)
    {
        this.MenuName = MenuName;
    }

    public String getNumberofQtys ()
    {
        return NumberofQtys;
    }

    public void setNumberofQtys (String NumberofQtys)
    {
        this.NumberofQtys = NumberofQtys;
    }

    public String getSubmenuId ()
    {
        return SubmenuId;
    }

    public void setSubmenuId (String SubmenuId)
    {
        this.SubmenuId = SubmenuId;
    }

    public String getItemName ()
    {
        return ItemName;
    }

    public void setItemName (String ItemName)
    {
        this.ItemName = ItemName;
    }

    public String getMenuid ()
    {
        return menuid;
    }

    public void setMenuid (String menuid)
    {
        this.menuid = menuid;
    }

    public String getSubMenuName ()
    {
        return SubMenuName;
    }

    public void setSubMenuName (String SubMenuName)
    {
        this.SubMenuName = SubMenuName;
    }

    public String getItem_Id ()
    {
        return Item_Id;
    }

    public void setItem_Id (String Item_Id)
    {
        this.Item_Id = Item_Id;
    }

    public String getItemUrl ()
    {
        return ItemUrl;
    }

    public void setItemUrl (String ItemUrl)
    {
        this.ItemUrl = ItemUrl;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Description = "+Description+", Amount = "+Amount+", RestaurantID = "+RestaurantID+", MenuName = "+MenuName+", NumberofQtys = "+NumberofQtys+", SubmenuId = "+SubmenuId+", ItemName = "+ItemName+", menuid = "+menuid+", SubMenuName = "+SubMenuName+", Item_Id = "+Item_Id+", ItemUrl = "+ItemUrl+"]";
    }
}
