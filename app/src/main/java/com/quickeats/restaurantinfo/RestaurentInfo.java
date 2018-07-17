package com.quickeats.restaurantinfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quickeats.MvpBaseActivity;
import com.quickeats.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rajesh kumar on 08-07-2018.
 */

public class RestaurentInfo extends MvpBaseActivity {

    @BindView(R.id.lltiming)
    LinearLayout lltiming;
    @Override
    public int getLayout() {
        return R.layout.restaurentinfo;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setLltiming();
    }

    private void setLltiming(){
        String[] mCheckoutItems = getResources().getStringArray(R.array.infotimings);
        for(String x:mCheckoutItems){
            View child = getLayoutInflater().inflate(R.layout.timingitem, null);
            TextView txtItemName = child.findViewById(R.id.txtweekday);
            txtItemName.setText(x);
            lltiming.addView(child);
        }
    }

//    private void initMap() {
//
//        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//    }
//
//    @Override
//    public void onMapReady(GoogleMap map) {
//
//        LatLng latLng = new LatLng(13.05241, 80.25082);
//        map.animateCamera(CameraUpdateFactory.newLatLng(latLng));
//        map.addMarker(new MarkerOptions().position(latLng).title("Raj Amal"));
//        map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
//    }

}
