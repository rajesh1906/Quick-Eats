package com.quickeats.dashboard.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.quickeats.Network.APIResponse;
import com.quickeats.Network.APIS;
import com.quickeats.Network.RetrofitClient;
import com.quickeats.R;
import com.quickeats.dashboard.DashboardActivity;
import com.quickeats.dashboard.adapter.RestaurentAdapter;
import com.quickeats.dashboard.fragments.model.RootItems;
import com.quickeats.restaurantdetail.LoadFragment;
import com.quickeats.restaurantdetail.RestaurentActivity;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Rajesh kumar on 24-06-2018.
 */

public class EatsInFragment extends Fragment implements LoadFragment {
    View mView;
    @BindView(R.id.recyclerview)
    RecyclerView mrecyclerView;
    private boolean showuser=true;
    String city_id = "8";
    @BindView(R.id.txt_no_res)
    TextView txt_no_res;

    private static String TAG = EatsInFragment.class.getName();
    public static EatsInFragment newInstance(String  city_id) {
        EatsInFragment fragment = new EatsInFragment();
        Bundle bundle = new Bundle();
        bundle.putString("city_id",city_id);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.eatinfragment,container,false);
        ButterKnife.bind(this,mView);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if(showuser) {
            showuser=false;
//            mrecyclerView.setAdapter(new RestaurentAdapter(EatsInFragment.this));
        }

        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        city_id = getArguments().getString("city_id");
        fetchData();

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){


        }
    }

    @OnClick (R.id.imgSettings)
    void settingClick(){
        LoadFragment.UpdateItem updateItem = (LoadFragment.UpdateItem) DashboardActivity.instance;
        updateItem.getItem();

    }

    @Override
    public void load(int id) {
        Intent intent = new Intent(getActivity(), RestaurentActivity.class);
        intent.putExtra("restaurant_id",id);
        startActivity(intent);
    }


    private void fetchData(){
        RetrofitClient.getInstance().getEndPoint(getActivity(),"show").getResult(getparams(), new APIResponse() {
            @Override
            public void onSuccess(String res) {
                Log.e("default items","<><"+res);
                RootItems items = new Gson().fromJson(res,RootItems.class);
                if(items.getStatus().equals("successfully")){
                    if(items.getRestaurantData().size()!=0) {
                        txt_no_res.setVisibility(View.GONE);
                        mrecyclerView.setAdapter(new RestaurentAdapter(EatsInFragment.this, items.getRestaurantData(), getActivity()));
                    }else{
                        txt_no_res.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(String res) {
                Log.e("failure","<><>"+res);
            }
        });

    }


    private HashMap<String ,String> getparams(){
        HashMap<String ,String> params = new HashMap<>();
        params.put("action", APIS.DEFAULT_LOADING_ITEMS);
        params.put("CityId", city_id);
        params.put("typeofways", "Food");
        params.put("foodandbeverage", "EatIn");
        params.put("FlagSlNo", ""+0);
        params.put("LatLng", "53.000,54000");


        return params;
    }
}
