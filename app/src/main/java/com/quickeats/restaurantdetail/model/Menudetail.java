package com.quickeats.restaurantdetail.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rajesh kumar on 03-09-2018.
 */

public class Menudetail {
    private String menuid;
    private String menuname;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
