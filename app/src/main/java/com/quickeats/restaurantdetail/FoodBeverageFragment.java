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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.quickeats.Network.APIResponse;
import com.quickeats.Network.APIS;
import com.quickeats.Network.RetrofitClient;
import com.quickeats.R;
import com.quickeats.dashboard.adapter.RestaurentAdapter;
import com.quickeats.restaurantdetail.adapter.FoodBeverageAdapter;
import com.quickeats.restaurantdetail.model.AllItems_Pojo;
import com.quickeats.restaurantdetail.model.ItemsData;
import com.quickeats.restaurantdetail.model.MenuItemsDetailRoot;
import com.quickeats.restaurantdetail.model.SampleResponse;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * Created by Rajesh kumar on 27-06-2018.
 */

public class FoodBeverageFragment extends Fragment implements LoadFragment.ImplItems {


    int resid,menuid;
    ArrayList<String> header_names;
    private SectionedRecyclerViewAdapter sectionAdapter;
    HashMap<String, List<String>> display_data;
    HashMap<String, ArrayList<HashMap<String, String>>> additional_data;
    Map<String, List<String>> final_list;
    int mCount=0;
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

                        sectionAdapter = new SectionedRecyclerViewAdapter();
//                        processResponse(SampleResponse.response);
                        processResponse(res);
                        final_list = new TreeMap<>(display_data);

                        Log.e("sub item size is ", "<><>" + additional_data.get(hashmapKeys(final_list).get(0)).size());

                        for (int i = 0; i < hashmapKeys(final_list).size(); i++) {
                            sectionAdapter.addSection(new ResItemsSection(hashmapKeys(final_list).get(i), additional_data.get(hashmapKeys(final_list).get(i))));

                        }
                        mrecyclerView.setAdapter(sectionAdapter);

//                        mrecyclerView.setAdapter(new FoodBeverageAdapter(FoodBeverageFragment.this, menuItemsDetailRoot.getItemsData(), getActivity()));
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


    private void processResponse(String res) {
    try{
        AllItems_Pojo items_pojo = new Gson().fromJson(res, AllItems_Pojo.class);
        display_data = new HashMap<>();
        additional_data = new HashMap<>();

        if (items_pojo.getStatus().equalsIgnoreCase("successfully")) {
            if (items_pojo.getItemsData().size() != 0) {
                List<String> itemList = null;
                ArrayList<HashMap<String, String>> addidional = null;
                for (int i = 0; i < items_pojo.getItemsData().size(); i++) {
                    ItemsData itemsData = items_pojo.getItemsData().get(i);
                    if (!display_data.containsKey(itemsData.getSubMenuName())) {
                        itemList = new ArrayList<String>();
                        addidional = new ArrayList<>();
                        HashMap<String, String> internal = new HashMap<>();
                        itemList.add(itemsData.getItemName());
                        display_data.put(itemsData.getSubMenuName(), itemList);

                        internal.put("Item_Id", itemsData.getItem_Id());
                        internal.put("NumberofQtys", itemsData.getNumberofQtys());
                        internal.put("Amount", itemsData.getAmount());
                        internal.put("Description", itemsData.getDescription());
                        internal.put("ItemUrl", itemsData.getItemUrl());
                        internal.put("RestaurantID", itemsData.getRestaurantID());
                        addidional.add(internal);
                        additional_data.put(itemsData.getSubMenuName(), addidional);
                    } else {
                        itemList = display_data.get(itemsData.getSubMenuName());
                        itemList.add(itemsData.getItemName());
                        HashMap<String, String> internal = new HashMap<>();
                        addidional = additional_data.get(itemsData.getSubMenuName());
                        internal.put("Item_Id", itemsData.getItem_Id());
                        internal.put("NumberofQtys", itemsData.getNumberofQtys());
                        internal.put("Amount", itemsData.getAmount());
                        internal.put("Description", itemsData.getDescription());
                        internal.put("ItemUrl", itemsData.getItemUrl());
                        internal.put("RestaurantID", itemsData.getRestaurantID());
                        addidional.add(internal);
                        additional_data.put(itemsData.getSubMenuName(), addidional);
                    }
                }


                JSONArray jsonArray = new JSONArray();
                Map<String, List<String>> final_list = new TreeMap<>(display_data);
                ArrayList<String> header_names = hashmapKeys(final_list);
                for (int i = 0; i < hashmapKeys(display_data).size(); i++) {
                    JSONObject mainObj = new JSONObject();
                    mainObj.put("Header", header_names.get(i));
                    JSONArray sub_json = new JSONArray();
                    for (int j = 0; j < final_list.get(header_names.get(i)).size(); j++) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("subItem_name", final_list.get(header_names.get(i)).get(j));
                        jsonObject.put("amount", additional_data.get(header_names.get(i)).get(j).get("Amount"));
                        jsonObject.put("description", additional_data.get(header_names.get(i)).get(j).get("Description"));
                        jsonObject.put("Image", additional_data.get(header_names.get(i)).get(j).get("ItemUrl"));

                        jsonObject.put("Item_Id", additional_data.get(header_names.get(i)).get(j).get("Item_Id"));
                        jsonObject.put("NumberofQtys", additional_data.get(header_names.get(i)).get(j).get("NumberofQtys"));
                        jsonObject.put("RestaurantID", additional_data.get(header_names.get(i)).get(j).get("RestaurantID"));

                        sub_json.put(jsonObject);
                    }
                    mainObj.put("subItems", sub_json);
                    jsonArray.put(mainObj);

                }
                Log.e("Json array is ", "<><><>" + jsonArray.toString());
//                MainOuter data = new Gson().fromJson("{\"items\":"+jsonArray.toString()+"}",MainOuter.class);
//                Log.e("pojo valus is ","<><<>"+data.getItems().get(0).getSubItems().get(0).getSubItem_name());


            }
        }
    }catch (Exception e){
        e.printStackTrace();
    }


    }

    private ArrayList<String> hashmapKeys(Map<String, List<String>> display_data) {
        ArrayList<String> keynames = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : display_data.entrySet()) {
            String key = entry.getKey();
//             = entry.getValue();
            Log.e("keys is ", "<><" + key);
            keynames.add(key);
            // do something with key and/or tab
        }

        return keynames;
    }
    private HashMap<String, String> getParams() {
        HashMap<String, String> params = new HashMap<>();
        params.put("action", APIS.MENU_ITEMS);
        params.put("RestaurantId", ""+resid);
        params.put("menuid", ""+menuid);

        return params;

    }


    private class HeaderViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;

        HeaderViewHolder(View view) {
            super(view);

            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        }
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        private final View rootView;
        private final ImageView img_src;
        private final TextView tvItem;
        private final TextView txt_description;
        private final TextView txt_amount;
        private final Button btnadd;
       private final LinearLayout llcontrol;

        ItemViewHolder(View view) {
            super(view);

            rootView = view;
            img_src = (ImageView) view.findViewById(R.id.imgres);
            tvItem = (TextView) view.findViewById(R.id.txt_item_name);
            txt_description = (TextView) view.findViewById(R.id.txt_submenu_name);
            txt_amount = (TextView) view.findViewById(R.id.txt_price);
            btnadd = view.findViewById(R.id.btnadd);
            llcontrol = view.findViewById(R.id.llcontrol);
        }
    }


    private class ResItemsSection extends StatelessSection {

        String title;
        ArrayList<HashMap<String, String>> hashMaps;

        public ResItemsSection(String title, ArrayList<HashMap<String, String>> hashMaps) {
            super(SectionParameters.builder()
                    .itemResourceId(R.layout.foodbeverateitem)
                    .headerResourceId(R.layout.section_ex1_header)
                    .build());

            this.title = title;
            this.hashMaps = hashMaps;
        }

        @Override
        public int getContentItemsTotal() {
            return hashMaps.size();
        }

        @Override
        public RecyclerView.ViewHolder getItemViewHolder(View view) {
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
            final ItemViewHolder itemHolder = (ItemViewHolder) holder;
//            Log.e("items is ","####"+final_list.get(title).get(position));
            String name = final_list.get(title).get(position);

            itemHolder.tvItem.setText(name);
            itemHolder.txt_description.setText(additional_data.get(title).get(position).get("Description"));
            itemHolder.txt_amount.setText("₹"+additional_data.get(title).get(position).get("Amount"));
            Picasso.with(getActivity())
                    .load(additional_data.get(title).get(position).get("ItemUrl"))
                    .into(itemHolder.img_src);
//            itemHolder.img_src.setImageResource(R.drawable.restaurent);

//            itemHolder.rootView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(getContext(),
//                            String.format("Clicked on position #%s of Section %s",
//                                    sectionAdapter.getPositionInSection(itemHolder.getAdapterPosition()),
//                                    title),
//                            Toast.LENGTH_SHORT).show();
//                }
//            });
            itemHolder.btnadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                     mCount++;
                    itemHolder.btnadd.setVisibility(View.GONE);
                    itemHolder.llcontrol.setVisibility(View.VISIBLE);
                    LoadFragment.ImplItems implItems = (LoadFragment.ImplItems) RestaurentActivity.instance;
                    implItems.getItemPosition(mCount);
                }
            });
        }

        @Override
        public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
            return new HeaderViewHolder(view);
        }

        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

            headerHolder.tvTitle.setText(title);
        }
    }
}
