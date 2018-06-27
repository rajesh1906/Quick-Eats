package com.example.rajeshkumar.eventbusexample;

import com.squareup.otto.Bus;

/**
 * Created by Rajesh kumar on 20-10-2017.
 */

public class EventBus {
    private static Bus bus;
    public static Bus getBus(){
        if(null==bus){
            bus = new Bus();
        }
        return bus;
    }
}
