package com.quickeats.dashboard.fragments.booking;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.quickeats.R;
import com.quickeats.dashboard.DashboardActivity;
import com.quickeats.dashboard.adapter.RestaurentAdapter;
import com.quickeats.restaurantdetail.LoadFragment;
import com.quickeats.restaurantdetail.RestaurentActivity;
import com.quickeats.shared.MvpBaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Rajesh kumar on 24-06-2018.
 */

public class EatsInFragment extends MvpBaseFragment<EatsInPresnter> implements LoadFragment {
    View mView;
    @BindView(R.id.recyclerview)
    RecyclerView mrecyclerView;
    private boolean showuser = true;


    private static String TAG = EatsInFragment.class.getName();

    public static EatsInFragment newInstance() {
        EatsInFragment fragment = new EatsInFragment();
        return fragment;
    }

    @Override
    protected int getFragmentViewId() {
        return R.layout.eatinfragment;
    }

    @Override
    protected void onFragmentViewCreated(View view, Bundle savedInstanceState) {
        super.onFragmentViewCreated(view, savedInstanceState);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (showuser) {
            showuser = false;
//            mrecyclerView.setAdapter(new RestaurentAdapter(EatsInFragment.this));
        }
        mrecyclerView.setAdapter(new RestaurentAdapter(EatsInFragment.this));
        getPresenter().loadDefalutItems();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && !showuser) {
            Toast.makeText(getActivity(), "uservisible ", Toast.LENGTH_SHORT).show();
            mrecyclerView.setAdapter(new RestaurentAdapter(EatsInFragment.this));
        }
    }

    @OnClick(R.id.imgSettings)
    void settingClick() {
        LoadFragment.UpdateItem updateItem = (LoadFragment.UpdateItem) DashboardActivity.instance;
        updateItem.getItem();

    }

    @Override
    public void load() {
        startActivity(new Intent(getActivity(), RestaurentActivity.class));
    }
}
