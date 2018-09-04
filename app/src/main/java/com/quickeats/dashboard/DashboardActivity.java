package com.quickeats.dashboard;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.quickeats.BaseActivity;
import com.quickeats.MvpBaseActivity;
import com.quickeats.R;
import com.quickeats.dashboard.fragments.BookFragment;
import com.quickeats.dashboard.fragments.BookingFragment;
import com.quickeats.dashboard.fragments.OffersFragment;
import com.quickeats.dashboard.fragments.OrderFragment;
import com.quickeats.dashboard.fragments.ProfileFragment;
import com.quickeats.dashboard.fragments.ScanFragment;
import com.quickeats.restaurantdetail.FoodBeverageFragment;
import com.quickeats.restaurantdetail.LoadFragment;
import com.quickeats.utils.DialogManage;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Rajesh kumar on 22-06-2018.
 */

public class DashboardActivity extends BaseActivity implements DialogManage,LoadFragment.UpdateItem,MenuCallback {

    DashboardPresenter presenter;
    @BindView(R.id.navigation)
    BottomNavigationView mbottomNavigationView;
    BottomSheetDialog mBottomSheetDialog;
    public static Activity instance;
    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected Object setupActivityComponent() {
        return null;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        presenter = new DashboardImp(this);
        presenter.initialSetUp();
        instance = this;
        implementBottomSheet();
    }

    //implement bottom sheet
    private void implementBottomSheet(){
        mBottomSheetDialog = new BottomSheetDialog(this);
        View sheetView = getLayoutInflater().inflate(R.layout.settingslayout, null);
        ImageView imgcancel = sheetView.findViewById(R.id.imgcancel);
        mBottomSheetDialog.setContentView(sheetView);
        imgcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomSheetDialog.dismiss();
            }
        });
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

    public void initilizeViews(){
        Fragment selectedFragment = null;
        selectedFragment =   BookFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, new BookingFragment());
        transaction.addToBackStack("bookFragment");
        transaction.commit();
        mbottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_item1:
//                                if (getSupportFragmentManager().findFragmentByTag("bookFragment") != null) {
//                                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,getSupportFragmentManager().findFragmentByTag("bookFragment")).commit();
//                                }else {
//                                    FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
//                                    selectedFragment = BookFragment.newInstance();
//                                    transaction1.replace(R.id.frame_layout, selectedFragment);
//                                    transaction1.addToBackStack("bookFragment");
//                                    transaction1.commit();
//                                }
                                break;
                            case R.id.action_item2:
//                                Toast.makeText(DashboardActivity.this,"Under constuction",Toast.LENGTH_LONG).show();
                                FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
                                selectedFragment = FoodBeverageFragment.newInstance(1,0);
                                transaction2.replace(R.id.frame_layout, selectedFragment);
                                transaction2.addToBackStack("foodFragment");
                                transaction2.commit();
                                break;
                            case R.id.action_item3:
//                                Toast.makeText(DashboardActivity.this,"Under constuction",Toast.LENGTH_LONG).show();
                                FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();
                                selectedFragment = ScanFragment.newInstance();
                                transaction3.replace(R.id.frame_layout, selectedFragment);
                                transaction3.addToBackStack("scanFragment");
                                transaction3.commit();
                                break;
                            case R.id.action_item4:
//                                Toast.makeText(DashboardActivity.this,"Under constuction",Toast.LENGTH_LONG).show();
                                FragmentTransaction transaction4 = getSupportFragmentManager().beginTransaction();
                                selectedFragment = OffersFragment.newInstance();
                                transaction4.replace(R.id.frame_layout, selectedFragment);
                                transaction4.addToBackStack("offerFragment");
                                transaction4.commit();
                                break;
                            case R.id.action_item5:
//                                Toast.makeText(DashboardActivity.this,"Under constuction",Toast.LENGTH_LONG).show();
                                FragmentTransaction transaction5 = getSupportFragmentManager().beginTransaction();
                                selectedFragment = ProfileFragment.newInstance();
                                transaction5.replace(R.id.frame_layout, selectedFragment);
                                transaction5.addToBackStack("profileFragment");
                                transaction5.commit();
                                break;
                        }

                        return true;
                    }
                });

        //Manually displaying the first fragment - one time only
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.frame_layout, BookFragment.newInstance());
//        transaction.commit();
    }

    @OnClick(R.id.ll_booking)
     void bookingImpl(){
//        mbottomNavigationView.setSelectedItemId(R.id.action_item1);
        FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
//        selectedFragment = BookFragment.newInstance();
        transaction1.replace(R.id.frame_layout,  new BookingFragment());
        transaction1.addToBackStack(null);
        transaction1.commit();
//        Toast.makeText(DashboardActivity.this,"Booking",Toast.LENGTH_SHORT).show();
//        initilizeViews();
    }

    @SuppressLint("ResourceType")
    @OnClick(R.id.ll_order)
     void orderImpl(){
        mbottomNavigationView.setSelectedItemId(R.id.action_item2);
    }
    @SuppressLint("ResourceType")
    @OnClick(R.id.ll_offers)
     void offerImpl(){
        mbottomNavigationView.setSelectedItemId(R.id.action_item4);
    }
    @SuppressLint("ResourceType")
    @OnClick(R.id.rel_profile)
     void proflieImpl(){
        mbottomNavigationView.setSelectedItemId(R.id.action_item5);
    }
    @SuppressLint("ResourceType")
    @OnClick(R.id.rel_scan)
     void scaningImpl(){
        mbottomNavigationView.setSelectedItemId(R.id.action_item3);
    }

    @Override
    public void getItem() {
        mBottomSheetDialog.show();
    }


    @Override
    public void callNavigationDrawer() {
        navigationListProcess();
    }
}
