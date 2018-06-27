package com.quickeats.dashboard.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quickeats.R;
import com.quickeats.dashboard.adapter.RestaurentAdapter;
import com.quickeats.restaurantdetail.LoadFragment;
import com.quickeats.restaurantdetail.RestaurentDetailFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rajesh kumar on 24-06-2018.
 */

public class EatsInFragment extends Fragment implements LoadFragment {
    View mView;
    @BindView(R.id.recyclerview)
    RecyclerView mrecyclerView;
    private static String TAG = EatsInFragment.class.getName();
    public static OffersFragment newInstance() {
        OffersFragment fragment = new OffersFragment();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.eatinfragment,container,false);
        ButterKnife.bind(this,mView);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mrecyclerView.setAdapter(new RestaurentAdapter(EatsInFragment.this));
        return mView;
    }

    @Override
    public void load() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.beginTransaction().add(R.id.bookfragmentcontianer,new RestaurentDetailFragment())
                .addToBackStack(TAG).commit();
    }
}
