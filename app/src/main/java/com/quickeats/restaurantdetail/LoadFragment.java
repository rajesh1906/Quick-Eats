package com.quickeats.restaurantdetail;

/**
 * Created by Rajesh kumar on 26-06-2018.
 */

public interface LoadFragment {
    void load(int id);
    interface ImplItems{
        void getItemPosition(int position,String item_id);
    }
    interface UpdateItem{
        void getItem();
    }


}
