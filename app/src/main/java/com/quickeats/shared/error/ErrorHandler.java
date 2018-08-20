package com.quickeats.shared.error;

import android.app.Activity;
import android.util.Log;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quickeats.shared.CallbackService;

import org.json.JSONObject;

import java.io.IOException;
import java.util.logging.Logger;

public class ErrorHandler {
    private static final String TAG = ErrorHandler.class.getSimpleName();

    interface ErrorHandlerCallback {

        void onHttpError(RetrofitException e);

        void onNetworkError(IOException e);

        void onServerError(String errorMessage);

        void onUnrecoverableError(Throwable throwable);
    }

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

    public static void handle(Throwable throwable, ErrorHandlerCallback callback) {
        if (throwable instanceof RetrofitException) {
            RetrofitException rex = (RetrofitException) throwable;
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

                switch (rex.getKind()) {
                    case HTTP:
                            callback.onHttpError(rex);
                        break;
                    case NETWORK:
                        callback.onNetworkError((IOException) rex.getCause());
                        break;
                    case SERVER:
                        callback.onServerError("");
                        break;
                    default:
                        callback.onUnrecoverableError(throwable);
                        break;
                }

            } catch (Exception e) {
                callback.onUnrecoverableError(throwable);
                return;
            }

        } else {
            callback.onUnrecoverableError(throwable);
            return;
        }

    }
}
