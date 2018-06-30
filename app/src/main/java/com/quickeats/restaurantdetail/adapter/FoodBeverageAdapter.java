package com.quickeats.restaurantdetail.adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.quickeats.R;
import com.quickeats.dashboard.adapter.RestaurentAdapter;
import com.quickeats.dashboard.fragments.EatsInFragment;
import com.quickeats.restaurantdetail.FoodBeverageFragment;

/**
 * Created by Rajesh kumar on 30-06-2018.
 */

public class FoodBeverageAdapter extends RecyclerView.Adapter<FoodBeverageAdapter.MyViewHolder> {

    Fragment fragment;

    public FoodBeverageAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout relItem;

        public MyViewHolder(View view) {
            super(view);
            relItem = view.findViewById(R.id.relItem);

        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.foodbeverateitem, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        holder.relItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ((FoodBeverageFragment) fragment).load();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
