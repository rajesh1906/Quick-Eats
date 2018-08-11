package com.quickeats.NavigationItems;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.quickeats.R;
import com.quickeats.dashboard.adapter.RestaurentAdapter;
import com.quickeats.shared.MvpBaseFragment;

import butterknife.BindView;

public class OrderHistoryDetails extends MvpBaseFragment {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @Override
    protected int getFragmentViewId() {
        return R.layout.commonrecyclerview;
    }
    public static OrderHistoryDetails getinstance(){
        OrderHistoryDetails orderHistoryDetails = new OrderHistoryDetails();
        return orderHistoryDetails;
    }

    @Override
    protected void onFragmentViewCreated(View view, Bundle savedInstanceState) {
        super.onFragmentViewCreated(view, savedInstanceState);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerview.setAdapter(new OrderHistoryDetailsAdapter());
    }

    @Override
    public void setupPresenter(Object presenter) {

    }

    @Override
    public String getViewIdentity() {
        return null;
    }


    class OrderHistoryDetailsAdapter extends RecyclerView.Adapter<OrderHistoryDetailsAdapter.MyViewHolder>{

        public class MyViewHolder extends RecyclerView.ViewHolder {

            RelativeLayout relItem;
            public MyViewHolder(View view) {
                super(view);
                relItem = view.findViewById(R.id.relItem);

            }
        }
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.orderhistoryitem, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }

}
