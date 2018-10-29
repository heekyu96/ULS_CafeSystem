package com.example.heegi.uls_cafesystem.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heegi.uls_cafesystem.R;

import java.util.ArrayList;

public class Level2Fragment extends Fragment {

    private RecyclerView recyclerView;
    private RecylerViewAdapter recylerViewAdapter;

    private ArrayList<Level2CardData> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.lev2_fragment,container,false);


        recyclerView = rootView.findViewById(R.id.lev2_recyclerview);
        list = new ArrayList<>();
        list.add(new Level2CardData("1","KANU"));
        list.add(new Level2CardData("2","MAXIM"));
        list.add(new Level2CardData("3","GREEN TEA"));
        list.add(new Level2CardData("4","KANU"));
        list.add(new Level2CardData("5","KANU"));
        list.add(new Level2CardData("6","KANU"));
        list.add(new Level2CardData("7","KANU"));
        recylerViewAdapter  = new RecylerViewAdapter(list);
        recyclerView.setAdapter(recylerViewAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        return rootView;
    }

    private class RecylerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{
        private ArrayList<Level2CardData> item;
        public RecylerViewAdapter(ArrayList<Level2CardData> list) {
            if (list==null) throw new IllegalArgumentException("Error No data exist");
            this.item = list;
        }

        @NonNull
        @Override
        public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lev2_cardview,null);
            return new RecyclerViewHolder(v);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {
            final Level2CardData level2CardData = item.get(i);

            recyclerViewHolder.number.setText("No. " + level2CardData.getOrderNo());
            recyclerViewHolder.menu.setText(level2CardData.getMenu());
            recyclerViewHolder.complete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "완료", Toast.LENGTH_SHORT).show();
                }
            });
            recyclerViewHolder.cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "삭제", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return item.size();
        }
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView number;
        private TextView menu;
        private Button complete;
        private Button cancel;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.lev2_card_number);
            menu = itemView.findViewById(R.id.lev2_card_menu);
            complete = itemView.findViewById(R.id.lev2_card_complete);
            cancel = itemView.findViewById(R.id.lev2_card_cancle);

        }
    }
}
