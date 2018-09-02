package com.quickeats.dashboard.fragments.booking;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.quickeats.Network.APIResponse;
import com.quickeats.Network.APIS;
import com.quickeats.Network.RetrofitClient;
import com.quickeats.R;
import com.quickeats.dashboard.DashboardActivity;
import com.quickeats.dashboard.MenuCallback;
import com.quickeats.dashboard.fragments.Collections;
import com.quickeats.dashboard.model.Cities;
import com.quickeats.shared.CallbackService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookingFragment extends Fragment implements CallbackService {

    View mView;
    @BindView(R.id.eatline)
    View eatline;
    @BindView(R.id.collectionine)
    View collectionine;
    @BindView(R.id.relsearching)
    RelativeLayout relsearching;
    @BindView(R.id.relmain)
    RelativeLayout relmain;
    @BindView(R.id.edtSearch)
    AutoCompleteTextView edtSearch;
    @BindView(R.id.txtlocation)
    TextView txtlocation;
    @BindView(R.id.img_delete)
    ImageView img_delete;
    ArrayList<String> city_items = new ArrayList<>();
    ArrayList<String> al_city_id = new ArrayList<>();
    String city_id="1";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        mView = inflater.inflate(R.layout.bookingfragment,container,false);
        ButterKnife.bind(this,mView);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.container,EatsInFragment.newInstance());
        ft.commit();
        return mView;
    }

    @OnClick(R.id.releatin)
    void fetchEatin(){
        eatline.setVisibility(View.VISIBLE);
        collectionine.setVisibility(View.GONE);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.container,EatsInFragment.newInstance());
        ft.commit();
    }

    @OnClick(R.id.relcollection)
    void fetchCollection(){
        eatline.setVisibility(View.GONE);
        collectionine.setVisibility(View.VISIBLE);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.container, Collections.newInstance());
        ft.commit();
    }
    @OnClick(R.id.imgmenu)


    public void menuClick(){
        MenuCallback callback = (MenuCallback) DashboardActivity.instance;
        callback.callNavigationDrawer();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fetchLisiner();
    }

    private void fetchLisiner() {
        edtSearch.addTextChangedListener(new CustomWatcher(edtSearch));
        edtSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                city_id = al_city_id.get(position);
                relsearching.setVisibility(View.GONE);
//                ((DashboardActivity)getActivity()).hideKeyboard(edtSearch);
                txtlocation.setText(city_items.get(position));
            }
        });
    }

    @OnClick(R.id.imgSearch)
    void enableSearching(){
        relsearching.setVisibility(View.VISIBLE);
        edtSearch.requestFocus();
//        ((DashboardActivity)getActivity()).openKeyboard(edtSearch);

    }
    @OnClick(R.id.relmain)
    void enableMainHeadingLayout(){

    }
    @OnClick(R.id.imgback)
    void searchBackImpl(){
        relsearching.setVisibility(View.GONE);
//        ((DashboardActivity)getActivity()).hideKeyboard(edtSearch);
    }
    @OnClick(R.id.img_delete)
    void searchDeleteImpl(){
        img_delete.setVisibility(View.GONE);
        edtSearch.setText("");
    }

    @Override
    public void callBackActivity(String response) {

    }

    private class CustomWatcher implements TextWatcher {

        private View view;

        public CustomWatcher(View view) {
            this.view = view;

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            switch (view.getId()) {

                case R.id.edtSearch:
                    if (edtSearch.getText().length() >= 1) {
                        img_delete.setVisibility(View.VISIBLE);
                        fetchData("cities", "");
                    }
                    break;

            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

    private void fetchData(final String coming_from, String progress_bar_status) {
        RetrofitClient.getInstance().getEndPoint(getActivity(), progress_bar_status).getResult(getParams(coming_from), new APIResponse() {
            @Override
            public void onSuccess(String res) {
                Log.e("resultent cities is ", "<><>" + res);

                switch (coming_from) {
                    case "cities":
                        if (city_items.size() != 0) {
                            city_items.clear();
                            al_city_id.clear();
                        }
                        Cities cities = new Gson().fromJson(res, Cities.class);
                        if (cities.getStatus().equalsIgnoreCase("successfully")) {
                            for (int i = 0; i < cities.getRestaurantData().size(); i++) {
                                city_items.add(cities.getRestaurantData().get(i).getCityName());
                                al_city_id.add(cities.getRestaurantData().get(i).getCityId());

                                Log.e("city data is ", "<><>" + cities.getRestaurantData().get(i).getCityName());
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.search_item, R.id.txt_item, city_items.toArray(new String[city_items.size()]));
                            edtSearch.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            if( cities.getRestaurantData().size()==0){
                                Toast.makeText(getActivity(), "No Search Found", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                        break;



                }
            }

            @Override
            public void onFailure(String res) {

            }
        });


    }

    private Map<String, String> getParams(String coming_from)   {
        Map<String, String> params = new HashMap<>();
        switch (coming_from) {
            case "cities":
                params.put("Text", edtSearch.getText().toString());
                params.put("FlagSlNo", "0");
//                params.put("action", getResources().getString(R.string.getCities));
                params.put("action", APIS.CITIES);

                break;
            case "category":
//                params.put("action", getResources().getString(R.string.getCategory));
                params.put("action", APIS.Category);

                break;
        }

        return params;
    }
}
