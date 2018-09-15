package com.quickeats.activities.cart;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import com.quickeats.R;
import com.quickeats.activities.cart.model.Restaurantlist;
import com.quickeats.utils.Constants;
import com.quickeats.utils.CustomDialog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rajesh Kumar on 18-04-2018.
 */
public class CartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    View root;
    private MyViewHolder holder;
    ArrayList<Restaurantlist> dataArrayList;
    DecimalFormat df = new DecimalFormat();
    Context context;
    private static final int TYPE_HEADER = 1;
    private static final int TYPE_ITEM = 2;
    PriceSettings priceSettings;
   public static BottomHolder bottom_holder;

    public CartAdapter(Context context, ArrayList<Restaurantlist> dataArrayList) {
        this.context = context;
        this.dataArrayList = dataArrayList;
         priceSettings = (PriceSettings)context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            root = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list_item, parent, false);
            MyViewHolder   holder = new MyViewHolder(root);
            return holder;
        }else{
            root = LayoutInflater.from(parent.getContext()).inflate(R.layout.subtotal_layout, parent, false);
            BottomHolder  holder = new BottomHolder(root);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder1, int position) {


        if(holder1 instanceof MyViewHolder){
            MyViewHolder holder = (MyViewHolder)holder1;
            holder.rd_item.setText(dataArrayList.get(position).getItemName());
            if (!dataArrayList.get(position).getAmount().matches("\\d*\\.?\\d+")) {
                holder.txt_price.setText(Constants.RUPEE + dataArrayList.get(position).getAmount());
                holder.txt_final_price.setText(Constants.RUPEE + dataArrayList.get(position).getAmount());
            } else {
                holder.txt_price.setText(Constants.RUPEE + dataArrayList.get(position).getAmount() + ".00");
                holder.txt_final_price.setText(Constants.RUPEE + dataArrayList.get(position).getAmount()+".00");
            }


            holder.txt_minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("position is ", "<><>" + (position));
                    String resultent = "";

                    int price = Integer.parseInt(holder.txt_value.getText().toString().replace(Constants.RUPEE, ""));
                    if (price != 0) {
                        price--;

                        holder.txt_value.setText("" + price);

                        try {
                            float resultent_price = (price * Float.parseFloat(holder.txt_price.getText().toString().replace(Constants.RUPEE, "").replace(",", "")));
                            resultent = "" + df.format(resultent_price);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (!resultent.matches("\\d*\\.?\\d+")) {
                            holder.txt_final_price.setText(Constants.RUPEE + resultent);
                        }
                        else {
                            holder.txt_final_price.setText(Constants.RUPEE + resultent + ".00");
                        }
                    }
                    priceSettings.setPrice(position,holder.txt_final_price.getText().toString());
                    priceSettings.getPrice();

                }
            });


            holder.txt_pluse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("position is ", "<><>" + (position));
                    int price = Integer.parseInt(holder.txt_value.getText().toString().replace(Constants.RUPEE, ""));
                    String resultent = "";
                    if (price >= 0) {
                        price++;
                        holder.txt_value.setText("" + price);
                        try {
                            float resultent_price = (price * Float.parseFloat(holder.txt_price.getText().toString().replace(Constants.RUPEE, "").replace(",", "")));
                            resultent = "" + df.format(resultent_price);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (!resultent.matches("\\d*\\.?\\d+")) {
                            holder.txt_final_price.setText(Constants.RUPEE + resultent);
                        }
                        else {
                            holder.txt_final_price.setText(Constants.RUPEE + resultent + ".00");
                        }

                        priceSettings.setPrice(position,holder.txt_final_price.getText().toString());
                        priceSettings.getPrice();
                    }
                }
            });
            priceSettings.setPrice(position,holder.txt_final_price.getText().toString());
        }else{
            bottom_holder = (BottomHolder)holder1;
            priceSettings.getPrice();
            bottom_holder.btn_payment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

//                    new Common_methods(context). makePayment();

                    JSONArray jsonArray = new JSONArray();
                    try {
                        for (int i = 0; i < dataArrayList.size(); i++) {
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("itemsid", dataArrayList.get(i).getItemid());
                            jsonObject.put("qty", dataArrayList.get(i).getQty());
                            jsonArray.put(jsonObject);
                        }

                        Log.e("array values is ","<><>"+jsonArray.toString());
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    CustomDialog.getInstance().showCategory_Dialog(context, (int position) -> {
                        Log.e("position is ","<><><><>"+position);
                        switch (position)
                        {

                            case 2:
                                if(dataArrayList.size()!=0) {
//                                    Intent intent = new Intent(context, PaymentView.class);
//                                    intent.putExtra("json_array", jsonArray.toString());
//                                    intent.putExtra("res_id", dataArrayList.get(0).getRes_Id());
//                                    context.startActivity(intent);
                                    Toast.makeText(context, "Under Construction", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, "Please Add At Least one Item", Toast.LENGTH_SHORT).show();
                                }
                                break;
                            case 3:
                                Toast.makeText(context, "Under Construction", Toast.LENGTH_SHORT).show();
//                                PaytmImpl.getInstance().onStartTransaction(context);
                                break;
                            case 4:
                                Toast.makeText(context, "Under Construction", Toast.LENGTH_SHORT).show();
//                                context.startActivity(new Intent( context,BrainTreeDashBoard.class));
                                break;
                        }

                    });



                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return dataArrayList.size()+1;
    }
    @Override
    public int getItemViewType(int position) {
        if (position == dataArrayList.size()) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rd_item)
        RadioButton rd_item;
        @BindView(R.id.txt_price)
        TextView txt_price;
        @BindView(R.id.txt_final_price)
        TextView txt_final_price;

        @BindView(R.id.txt_minus)
        TextView txt_minus;
        @BindView(R.id.txt_pluse)
        TextView txt_pluse;
        @BindView(R.id.txt_value)
        TextView txt_value;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    public class BottomHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_total_price)
        TextView txt_total_price;
        @BindView(R.id.txt_tax_price)
        TextView txt_tax_price;
        @BindView(R.id.txt_res_total)
        TextView txt_res_total;
        @BindView(R.id.btn_payment)
        Button btn_payment;

        public BottomHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
    public void updateResultent( int value){
        String resultent = ""+value;
        int res_total = value+80;
        if (!resultent.matches("\\d*\\.?\\d+")) {
            bottom_holder.txt_total_price.setText(Constants.RUPEE + value);
            bottom_holder.txt_res_total.setText(Constants.RUPEE + res_total);
        }else{
            bottom_holder.txt_total_price.setText(Constants.RUPEE + value+".00");
            bottom_holder.txt_res_total.setText(Constants.RUPEE + res_total+".00");
        }
    }


}
