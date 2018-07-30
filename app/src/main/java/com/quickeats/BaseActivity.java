package com.quickeats;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quickeats.NavigationItems.PaymentOptionsActivity;
import com.quickeats.customviews.CustomDrawerLayout;
import com.quickeats.customviews.ItemAdapter;
import com.quickeats.utils.Dashboard_items;

public abstract class BaseActivity extends MvpBaseActivity {
    protected abstract int getLayoutResourceId();

    public TextView app_title_txt;
    private String app_title;
    protected Toolbar mToolbar;
    public CustomDrawerLayout mDrawerLayout;
   protected ActionBarDrawerToggle drawerToggle;
    ListView mainlist;
    ItemAdapter adapter;
    protected ImageView search_icon, filters_icon, close_icon,img_delete;
   protected AutoCompleteTextView edtSearch;

    Button btn_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mainlist = (ListView) findViewById(R.id.mainlist);
        mDrawerLayout = (CustomDrawerLayout) findViewById(R.id.drawer_layout);
        search_icon = (ImageView) findViewById(R.id.search_icon);
        filters_icon = (ImageView) findViewById(R.id.filters_icon);
        close_icon = (ImageView) findViewById(R.id.close_icon);
        btn_date = (Button) findViewById(R.id.btn_date);


        drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                mToolbar, R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                ActivityCompat.invalidateOptionsMenu(BaseActivity.this);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                ActivityCompat.invalidateOptionsMenu(BaseActivity.this);
            }

        };
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.lay_activity_base);
        LayoutInflater inflater = (LayoutInflater)
                this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        DrawerLayout childAt = (DrawerLayout) layout.getChildAt(0);
        childAt.addView(inflater.inflate(getLayoutResourceId(), null), 0);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
//        app_title_txt = (TextView) findViewById(R.id.btn_logo);
//        app_title = "Quick Pick";
//        app_title_txt.setText(app_title);

        mDrawerLayout.setDrawerListener(drawerToggle);
        setUpNavDrawer();
        drawerToggle.syncState();
        adapter = new ItemAdapter(this, new Dashboard_items(this, "menu").getDashBoardItems());
        mainlist.setAdapter(adapter);
        mainlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position) {
                    case 0:

                        break;
                    case 1:

                        break;
                    case 2:
//                        Toast.makeText(BaseActivity.this,"Payments",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(BaseActivity.this, PaymentOptionsActivity.class));
                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:
                       // new Common_methods(BaseActivity.this).popup(BaseActivity.this,"logout");
                        break;
                }
                navigationListProcess();
            }
        });

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
        } else {
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

    public void openKeyboard(View view){
        InputMethodManager inputMethodManager =
                (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInputFromWindow(
                view.getApplicationWindowToken(),
                InputMethodManager.SHOW_FORCED, 0);
    }


}
