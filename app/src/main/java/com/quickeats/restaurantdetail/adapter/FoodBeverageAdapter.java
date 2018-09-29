package com.quickeats.restaurantdetail.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quickeats.R;
import com.quickeats.restaurantdetail.LoadFragment;
import com.quickeats.restaurantdetail.model.ItemsData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Rajesh kumar on 30-06-2018.
 */

public class FoodBeverageAdapter extends RecyclerView.Adapter<FoodBeverageAdapter.MyViewHolder> {

    Fragment fragment;
    int mCount=0;
    ArrayList<ItemsData> data;
    Context  context;

    public FoodBeverageAdapter(Fragment fragment, ArrayList<ItemsData> data,Context context) {
        this.fragment = fragment;
        this.data = data;
        this.context =context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout llcontrol;
        Button btnadd ;
        ImageView imgres;
        TextView txt_item_name,txt_submenu_name,txt_price;

        public MyViewHolder(View view) {
            super(view);
            btnadd = (Button)view.findViewById(R.id.btnadd);
            llcontrol = view.findViewById(R.id.llcontrol);
            imgres = view.findViewById(R.id.imgres);
            txt_item_name = view.findViewById(R.id.txt_item_name);
            txt_submenu_name = view.findViewById(R.id.txt_submenu_name);
            txt_price = view.findViewById(R.id.txt_price);



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

        holder.txt_item_name.setText(data.get(position).getItemName());
        holder.txt_submenu_name.setText(data.get(position).getSubMenuName());
        holder.txt_price.setText(""+data.get(position).getAmount());
        Picasso.with(context)
                .load(data.get(position).getItemUrl())
                .into(holder.imgres);

        holder.btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 mCount++;
                holder.btnadd.setVisibility(View.GONE);
                holder.llcontrol.setVisibility(View.VISIBLE);
                Log.e("item id is ","<><>"+data.get(position).getItem_Id());
                LoadFragment.ImplItems implItems = (LoadFragment.ImplItems)fragment;
                implItems.getItemPosition(mCount,data.get(position).getItem_Id());
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
