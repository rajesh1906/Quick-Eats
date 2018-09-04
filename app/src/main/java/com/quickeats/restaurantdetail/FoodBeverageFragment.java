package com.quickeats.restaurantdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.quickeats.Network.APIResponse;
import com.quickeats.Network.APIS;
import com.quickeats.Network.RetrofitClient;
import com.quickeats.R;
import com.quickeats.dashboard.adapter.RestaurentAdapter;
import com.quickeats.restaurantdetail.adapter.FoodBeverageAdapter;
import com.quickeats.restaurantdetail.model.MenuItemsDetailRoot;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rajesh kumar on 27-06-2018.
 */

public class FoodBeverageFragment extends Fragment implements LoadFragment.ImplItems {


    int resid,menuid;
    public static FoodBeverageFragment newInstance(int resid,int menuid) {
        Bundle b = new Bundle();
        b.putInt("resid", resid);
        b.putInt("menuid", menuid);
        FoodBeverageFragment fragment = new FoodBeverageFragment();
        fragment.setArguments(b);
        return fragment;
    }

    View mView;
    @BindView(R.id.recyclerview)
    RecyclerView mrecyclerView;
    @BindView(R.id.txt_no_res)
    TextView txt_no_res;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.commonrecyclerview, container, false);
        ButterKnife.bind(this, mView);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return mView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        resid = getArguments().getInt("resid");
        menuid = getArguments().getInt("menuid");
        Log.e("resid ","<><>"+resid+"<><>"+menuid);
        if (isVisibleToUser) {
            callAPI();
        }
    }

    @Override
    public void getItemPosition(int position) {
        LoadFragment.ImplItems implItems = (LoadFragment.ImplItems) RestaurentActivity.instance;
        implItems.getItemPosition(position);
    }

    private void callAPI(){
        RetrofitClient.getInstance().getEndPoint(getActivity(),"show").getResult(getParams(), new APIResponse() {
            @Override
            public void onSuccess(String res) {
                Log.e("success","<Menu items>>"+res);
                MenuItemsDetailRoot menuItemsDetailRoot = new Gson().fromJson(res,MenuItemsDetailRoot.class);
                if(menuItemsDetailRoot.getStatus().equalsIgnoreCase("successfully")){
                    if (menuItemsDetailRoot.getItemsData().size()!=0) {
                        mrecyclerView.setAdapter(new FoodBeverageAdapter(FoodBeverageFragment.this, menuItemsDetailRoot.getItemsData(), getActivity()));
                    }else{
                        txt_no_res.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(String res) {
                Log.e("failure ","<>Menu Items<>"+res);

            }
        });
    }
    private HashMap<String, String> getParams() {
        HashMap<String, String> params = new HashMap<>();
        params.put("action", APIS.MENU_ITEMS);
        params.put("RestaurantId", ""+resid);
        params.put("menuid", ""+menuid);

        return params;

    }
}
