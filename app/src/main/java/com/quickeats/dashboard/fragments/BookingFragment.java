package com.quickeats.dashboard.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.quickeats.R;
import com.quickeats.dashboard.DashboardActivity;
import com.quickeats.dashboard.MenuCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookingFragment extends Fragment {



    View mView;
    @BindView(R.id.eatline)
    View eatline;
    @BindView(R.id.collectionine)
    View collectionine;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        mView = inflater.inflate(R.layout.bookingfragment,container,false);
        ButterKnife.bind(this,mView);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.container,EatsInFragment.newInstance());
        ft.commit();
        return mView;
    }

    @OnClick(R.id.releatin)
    void fetchEatin(){
        eatline.setVisibility(View.VISIBLE);
        collectionine.setVisibility(View.GONE);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.container,EatsInFragment.newInstance());
        ft.commit();
    }

    @OnClick(R.id.relcollection)
    void fetchCollection(){
        eatline.setVisibility(View.GONE);
        collectionine.setVisibility(View.VISIBLE);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.container,Collections.newInstance());
        ft.commit();
    }
    @OnClick(R.id.imgmenu)
    public void menuClick(){
        MenuCallback callback = (MenuCallback) DashboardActivity.instance;
        callback.callNavigationDrawer();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
