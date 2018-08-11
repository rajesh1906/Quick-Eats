package com.quickeats.restaurantinfo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.quickeats.MvpBaseActivity;
import com.quickeats.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Rajesh kumar on 08-07-2018.
 */

public class RestaurentInfo extends MvpBaseActivity implements OnMapReadyCallback {

    @BindView(R.id.lltiming)
    LinearLayout lltiming;
    static final LatLng HAMBURG = new LatLng(53.558, 9.927);
    static final LatLng KIEL = new LatLng(53.551, 9.993);
    private GoogleMap mMap;
    @Override
    public int getLayout() {
        return R.layout.restaurentinfo;
    }

    @Override
    protected Object setupActivityComponent() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setLltiming();
        mapImpementation();
    }


    private void mapImpementation(){
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (mMap!=null){
            Marker hamburg = mMap.addMarker(new MarkerOptions().position(HAMBURG)
                    .title("Hamburg"));
            Marker kiel = mMap.addMarker(new MarkerOptions()
                    .position(KIEL)
                    .title("Kiel")
                    .snippet("Kiel is cool")
                    .icon(BitmapDescriptorFactory
                            .fromResource(R.drawable.location)));
        }
    }
    @OnClick(R.id.imgback)
     void backBtnImpl(){
        finish();
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
    @Override
    public Activity getActivityFromView() {
        return null;
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void setupPresenter(Object presenter) {

    }

    @Override
    public String getViewIdentity() {
        return null;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
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
