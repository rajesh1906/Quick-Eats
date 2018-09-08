package com.quickeats.utils;

import android.content.Context;

import com.quickeats.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Rajesh Kumar on 13-07-2017.
 */

public class Dashboard_items {


    protected  String[] titles;
    ArrayList<HashMap<String ,Object >> data = new ArrayList<>();
    Integer mhomeIcons[] = {R.drawable.details,R.drawable.locationblack,R.drawable.paymentoptions,R.drawable.foodorderblack,R.drawable.sharedark,R.drawable.favirates,R.drawable.support,R.drawable.logout};
    public Dashboard_items(Context context, String coming_from){
//        if(null!=((String ) StoredDB.getInstance(context).getStorageValue("id"))) {
//            if (((String) StoredDB.getInstance(context).getStorageValue("id")).length() != 0) {
//                titles = context.getResources().getStringArray(R.array.dashboard_items_menu);
//
//            } else {
//                titles = context.getResources().getStringArray(R.array.dashboard_items_menu_without_signout);
//
//            }
//        }else{
//            titles = context.getResources().getStringArray(R.array.dashboard_items_menu_without_signout);
//        }
        titles = context.getResources().getStringArray(R.array.dashboard_items_menu);

    }
    public ArrayList<HashMap<String ,Object >> getDashBoardItems(){
       for (int i = 0;i<titles.length;i++){
           HashMap<String,Object > items = new HashMap<>();
           items.put("title",titles[i]);
           items.put("image",mhomeIcons[i]);
           data.add(items);
       }
        return data;
    }
}
