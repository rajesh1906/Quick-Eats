package com.quickeats.activities.dashboard;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

import com.quickeats.MvpBaseActivity;
import com.quickeats.NavigationItems.FavoretiesActivity;
import com.quickeats.NavigationItems.HelpActivity;
import com.quickeats.NavigationItems.MyOrdersActivity;
import com.quickeats.NavigationItems.PaymentOptionsActivity;
import com.quickeats.NavigationItems.SavedAddressActivity;
import com.quickeats.R;
import com.quickeats.customviews.CustomDrawerLayout;
import com.quickeats.customviews.ItemAdapter;
import com.quickeats.dashboard.MenuCallback;
import com.quickeats.dashboard.fragments.OffersFragment;
import com.quickeats.dashboard.fragments.OrderFragment;
import com.quickeats.dashboard.fragments.ProfileFragment;
import com.quickeats.dashboard.fragments.ScanFragment;
import com.quickeats.dashboard.fragments.booking.BookFragment;
import com.quickeats.dashboard.fragments.booking.BookingFragment;
import com.quickeats.di.DBComponet;
import com.quickeats.restaurantdetail.FoodBeverageFragment;
import com.quickeats.utils.Dashboard_items;

import butterknife.BindView;

public class DBActivity extends MvpBaseActivity<DBPresenter, DBComponet> implements DBView,MenuCallback {
    @BindView(R.id.navigation)
    BottomNavigationView mbottomNavigationView;
    BottomSheetDialog mBottomSheetDialog;
    Toolbar mToolbar;
    @BindView(R.id.drawer_layout)
    CustomDrawerLayout mDrawerLayout;

    ActionBarDrawerToggle drawerToggle;
    @BindView(R.id.mainlist)
    ListView mainlist;

    ItemAdapter adapter;
    public static DBActivity instance;
    @BindView(R.id.framework)
    FrameLayout framework;
//
//    @Override
//    protected int getLayoutResourceId() {
//        return R.layout.activity_main;
//    }

    @Override
    public int getLayout() {
        return R.layout.activity_base;
    }

    @Override
    protected DBComponet setupActivityComponent() {
        return getApplicationComponent().plus(new DBModule());
    }

    @Override
    protected void onCreateBeforeSetContentView(Bundle savedInstanceState) {
        super.onCreateBeforeSetContentView(savedInstanceState);
        instance = this;
    }

    @Override
    protected void onCreateAfterSetContentView(Bundle savedInstanceState) {
        super.onCreateAfterSetContentView(savedInstanceState);
        initilizeViews();
        implementBottomSheet();
        implementNavigation();
    }

    private void implementNavigation(){
        drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                mToolbar, R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                ActivityCompat.invalidateOptionsMenu(DBActivity.this);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                ActivityCompat.invalidateOptionsMenu(DBActivity.this);
            }

        };
//        RelativeLayout layout = (RelativeLayout) findViewById(R.id.lay_activity_base);
//        LayoutInflater inflater = (LayoutInflater)
//                this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
////        DrawerLayout childAt = (DrawerLayout) layout.getChildAt(0);
//        DrawerLayout childAt = (DrawerLayout) findViewById(R.id.drawer_layout);
////        childAt.addView(inflater.inflate(getLayoutResourceId(), null), 0);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
//        app_title_txt = (TextView) findViewById(R.id.btn_logo);
//        app_title = "Quick Pick";
//        app_title_txt.setText(app_title);

//        mDrawerLayout.setDrawerListener(drawerToggle);
        setUpNavDrawer();
//        drawerToggle.syncState();
        adapter = new ItemAdapter(this, new Dashboard_items(this, "menu").getDashBoardItems());
        mainlist.setAdapter(adapter);
        mainlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
                framework.setVisibility(View.GONE);

                switch (position) {
                    case 0:
//                        startActivity(new Intent(BaseActivity.this, DetailsActivity.class));
                        openDetailsDailog();

                        break;
                    case 1:
                        startActivity(new Intent(DBActivity.this, SavedAddressActivity.class));
                        break;
                    case 2:
//                        Toast.makeText(BaseActivity.this,"Payments",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(DBActivity.this, PaymentOptionsActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(DBActivity.this, MyOrdersActivity.class));
                        break;
                    case 4:
//                        startActivity(new Intent(BaseActivity.this, ReferFriendActivity.class));
                        openReferFriend();

                        break;
                    case 5:
                        startActivity(new Intent(DBActivity.this, FavoretiesActivity.class));
                        // new Common_methods(BaseActivity.this).popup(BaseActivity.this,"logout");
                        break;
                    case 6:
                        startActivity(new Intent(DBActivity.this, HelpActivity.class));
                        break;
                }
                navigationListProcess();
            }
        });
    }
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
//                                selectedFragment = FoodBeverageFragment.newInstance();
                                transaction2.replace(R.id.frame_layout, new OrderFragment());
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


    private void setUpNavDrawer() {
        if (mToolbar != null) {
            assert getSupportActionBar() != null;
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setDisplayShowTitleEnabled(false);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick(View v) {
                    hideKeyboard(mToolbar);
                    if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                    } else {
                        mDrawerLayout.openDrawer(GravityCompat.START);
                    }
                }
            });
        }
    }

    public void navigationListProcess() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
//            framework.setVisibility(View.GONE);
        } else {
//            framework.setVisibility(View.VISIBLE);
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        hideKeyboard(mToolbar);
    }

    public void hideKeyboard(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static boolean haveNetworkConnection(Context context) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    public void openKeyboard(View view) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInputFromWindow(
                view.getApplicationWindowToken(),
                InputMethodManager.SHOW_FORCED, 0);
    }

    private void openDetailsDailog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.details);
        ImageView imgcancel = dialog.findViewById(R.id.imgcancel);
        imgcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void openReferFriend() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.referfriendactivity);
        ImageView imgcancel = dialog.findViewById(R.id.imgcancel);
        imgcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void callNavigationDrawer() {
        framework.setVisibility(View.VISIBLE);
//        navigationListProcess();
        mDrawerLayout.openDrawer(GravityCompat.START);
    }
}
