package com.quickeats.restaurantdetail.adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.quickeats.R;
import com.quickeats.restaurantdetail.LoadFragment;

/**
 * Created by Rajesh kumar on 30-06-2018.
 */

public class FoodBeverageAdapter extends RecyclerView.Adapter<FoodBeverageAdapter.MyViewHolder> {

    Fragment fragment;
    int mCount=0;

    public FoodBeverageAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout llcontrol;
        Button btnadd ;

        public MyViewHolder(View view) {
            super(view);
            btnadd = (Button)view.findViewById(R.id.btnadd);
            llcontrol = view.findViewById(R.id.llcontrol);

        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.foodbeverateitem, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 mCount++;
                holder.btnadd.setVisibility(View.GONE);
                holder.llcontrol.setVisibility(View.VISIBLE);
                LoadFragment.ImplItems implItems = (LoadFragment.ImplItems)fragment;
                implItems.getItemPosition(mCount);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
