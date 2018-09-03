package com.quickeats.dashboard.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quickeats.R;
import com.quickeats.dashboard.fragments.BookFragment;
import com.quickeats.dashboard.fragments.EatsInFragment;
import com.quickeats.dashboard.fragments.model.RestaurantData;
import com.quickeats.restaurantdetail.LoadFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Rajesh kumar on 24-06-2018.
 */

public class RestaurentAdapter extends RecyclerView.Adapter<RestaurentAdapter.MyViewHolder> {

    Fragment fragment;
    ArrayList<RestaurantData> restaurantData;
    Context context;

    public RestaurentAdapter(Fragment fragment,ArrayList<RestaurantData> restaurantData,Context context){
        this.fragment = fragment;
        this.restaurantData  = restaurantData;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout relItem;
        TextView txt_resname;
        ImageView imgItem;
        public MyViewHolder(View view) {
            super(view);
            relItem = view.findViewById(R.id.relItem);
            txt_resname = view.findViewById(R.id.txt_resname);
            imgItem  = view.findViewById(R.id.imgItem);
        }
    }




    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restaurentitem, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Picasso.with(context)
                .load(restaurantData.get(position).getRestaurantnameurl())
                .into(holder.imgItem);
            holder.txt_resname.setText(restaurantData.get(position).getRestaurantname());

            holder.relItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((EatsInFragment)fragment).load(restaurantData.get(position).getRes_id());
                }
            });
    }

    @Override
    public int getItemCount() {
        return restaurantData.size();
    }
}
