package com.quickeats.restaurantdetail;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.quickeats.MvpBaseActivity;
import com.quickeats.Network.APIResponse;
import com.quickeats.Network.APIS;
import com.quickeats.Network.RetrofitClient;
import com.quickeats.R;
import com.quickeats.checkout.CheckoutActivity;
import com.quickeats.dashboard.fragments.ProfileFragment;
import com.quickeats.restaurantdetail.LoadFragment.ImplItems;
import com.quickeats.restaurantdetail.model.MenuItemsRoot;
import com.quickeats.restaurantdetail.model.Menudetail;
import com.quickeats.restaurantinfo.RestaurentInfo;
import com.quickeats.utils.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Rajesh kumar on 28-06-2018.
 */

public class RestaurentActivity extends MvpBaseActivity implements ImplItems {

    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    public static Activity instance;
    @BindView(R.id.lladdeditems)
    LinearLayout lladdeditems;
    @BindView(R.id.txtnumberitems)
    TextView txtnumberitems;
    BottomSheetDialog mBottomSheetDialog;
    int res_id;
    @Override
    public int getLayout() {
        return R.layout.restaurentdetailfragment;
    }

    @Override
    protected Object setupActivityComponent() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        instance = this;

         res_id = getIntent().getExtras().getInt("restaurant_id");
        Log.e("res_id","<><>"+res_id);
        callApi();


    }

    private void implementBottomSheet() {
        mBottomSheetDialog = new BottomSheetDialog(this);
        View sheetView = getLayoutInflater().inflate(R.layout.menufilter, null);
        ImageView imgcancel = sheetView.findViewById(R.id.imgcancel);
        LinearLayout linearLayout = sheetView.findViewById(R.id.llitem);
        mBottomSheetDialog.setContentView(sheetView);
//        mBottomSheetDialog.getWindow().setBackgroundDrawable( new ColorDrawable(android.graphics.Color.TRANSPARENT));

        String[] mCheckoutItems = getResources().getStringArray(R.array.filternames);

        for (String x : mCheckoutItems) {
            View child = getLayoutInflater().inflate(R.layout.filteritem, null);
            TextView txtItemName = child.findViewById(R.id.txtitem);
            txtItemName.setText(x);
            linearLayout.addView(child);
        }
        imgcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomSheetDialog.dismiss();
            }
        });
    }

    private void setupViewPager(ViewPager viewPager, ArrayList<Menudetail> items) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(this.getSupportFragmentManager());
        for(int i=0;i<items.size();i++) {
            adapter.addFragment(FoodBeverageFragment.newInstance(res_id,Integer.parseInt(items.get(i).getMenuid())), items.get(i).getMenuname());
        }
        viewPager.setAdapter(adapter);
    }

    @OnClick(R.id.lladdeditems)
    void checkOutItems() {
        startActivity(new Intent(this, CheckoutActivity.class));
    }


    @OnClick(R.id.floatmenu)
    void menuItems() {
        mBottomSheetDialog.show();
    }

    @OnClick(R.id.txtordertype)
    void clickOrderType(){

    }

    @OnClick(R.id.txtmap)
    void clickMap(){
        startActivity(new Intent(RestaurentActivity.this, RestaurentInfo.class));
    }


    @Override
    public void getItemPosition(int position) {
        lladdeditems.setVisibility(View.VISIBLE);
        txtnumberitems.setText(position + " Items Added");
//        Toast.makeText(this,"Item Position"+position,Toast.LENGTH_LONG).show();
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


    private void callApi(){
        RetrofitClient.getInstance().getEndPoint(this,"Show").getResult(getParams(), new APIResponse() {
            @Override
            public void onSuccess(String res) {
                Log.e("success","<><>"+res);
                MenuItemsRoot root = new Gson().fromJson(res,MenuItemsRoot.class);
                if(root.getStatus().equalsIgnoreCase("successfully")){
                    setupViewPager(mViewPager,root.getMenudetails());
                    tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
                    tabs.setupWithViewPager(mViewPager);
                    implementBottomSheet();
                }
            }

            @Override
            public void onFailure(String res) {
                Log.e("failure","<><>"+res);

            }
        });
    }


    private HashMap<String,String > getParams(){
        HashMap<String,String > params= new HashMap<>();
        params.put("RestaurantId",""+res_id);
        params.put("action", APIS.MENU_DETAILS);
        return params;

    }
}
