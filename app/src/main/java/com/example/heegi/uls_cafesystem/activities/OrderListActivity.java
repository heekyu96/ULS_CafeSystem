package com.example.heegi.uls_cafesystem.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.heegi.uls_cafesystem.DataForm.Level2CardData;
import com.example.heegi.uls_cafesystem.DataForm.OrderListCardData;
import com.example.heegi.uls_cafesystem.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

public class OrderListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    private ArrayList<OrderListCardData> list;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        recyclerView = findViewById(R.id.order_list_recyclerview);
        list = new ArrayList<>();
        list.add(new OrderListCardData("2018.10.30 17:28","KANU"));
        list.add(new OrderListCardData("2018.10.30 17:28","MAXIM"));
        list.add(new OrderListCardData("2018.10.30 17:28","GREEN TEA"));
        list.add(new OrderListCardData("2018.10.30 17:28","KANU"));
        list.add(new OrderListCardData("2018.10.30 17:28","KANU"));
        list.add(new OrderListCardData("2018.10.30 17:28","KANU"));
        list.add(new OrderListCardData("2018.10.30 17:28","KANU"));
        recyclerViewAdapter  = new RecyclerViewAdapter(list);
        recyclerView.setAdapter(recyclerViewAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        return recyclerView;
    }


    private class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{
        private ArrayList<OrderListCardData> item;

        public RecyclerViewAdapter(ArrayList<OrderListCardData> list) {
            if(list==null) throw new IllegalArgumentException("Error No data exist");
            this.item = list;
        }

        @android.support.annotation.NonNull
        @Override
        public RecyclerViewHolder onCreateViewHolder(@android.support.annotation.NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.orderlist_cardview,null);
            return new RecyclerViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@android.support.annotation.NonNull RecyclerViewHolder recyclerViewHolder, int i) {
            final OrderListCardData orderListCardData = item.get(i);

            recyclerViewHolder.orderTime.setText(orderListCardData.getOrderTime());
            recyclerViewHolder.menu.setText(orderListCardData.getMenu());
        }

        @Override
        public int getItemCount() {
            return item.size();
        }
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView orderTime;
        private TextView menu;

        public RecyclerViewHolder(@android.support.annotation.NonNull View itemView) {
            super(itemView);
            orderTime = itemView.findViewById(R.id.order_list_card_time);
            menu = itemView.findViewById(R.id.order_list_card_menu);
        }
    }
}
