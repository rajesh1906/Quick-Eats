package com.quickeats.utils;

import android.app.Activity;

import com.quickeats.shared.CallbackService;

import org.json.JSONObject;

public class ErrorHandler {
    private static final String TAG = ErrorHandler.class.getSimpleName();



    public static void handleRequest(String response, Activity activity){

        try{
            JSONObject jsonObject = new JSONObject(response);
            if(jsonObject.getBoolean("error")){

            }else {

            }

        }catch (Exception e){
            e.printStackTrace();
        }

        ((CallbackService) activity).callBackActivity();

    }
}
