package com.quickeats.restaurantdetail.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rajesh kumar on 03-09-2018.
 */

public class MenuItemsRoot {
    private Boolean error;
    private String Status;
    private ArrayList<Menudetail> menudetails = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public ArrayList<Menudetail> getMenudetails() {
        return menudetails;
    }

    public void setMenudetails(ArrayList<Menudetail> menudetails) {
        this.menudetails = menudetails;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
