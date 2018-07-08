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
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.quickeats.MvpBaseActivity;
import com.quickeats.R;
import com.quickeats.checkout.CheckoutActivity;
import com.quickeats.dashboard.fragments.ProfileFragment;
import com.quickeats.restaurantdetail.LoadFragment.ImplItems;
import com.quickeats.restaurantinfo.RestaurentInfo;
import com.quickeats.utils.ViewPagerAdapter;

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

    @Override
    public int getLayout() {
        return R.layout.restaurentdetailfragment;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setupViewPager(mViewPager);
        instance = this;
        tabs.setupWithViewPager(mViewPager);
        implementBottomSheet();

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

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(this.getSupportFragmentManager());
        adapter.addFragment(new FoodBeverageFragment(), "All");
        adapter.addFragment(new FoodBeverageFragment(), "Veg/Vegan");
        adapter.addFragment(new FoodBeverageFragment(), "Drinks");
        adapter.addFragment(new FoodBeverageFragment(), "Desserts");
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
}
