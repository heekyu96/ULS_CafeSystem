package com.example.heegi.uls_cafesystem.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.heegi.uls_cafesystem.R;
import com.example.heegi.uls_cafesystem.activities.OrderActivity;

public class Level1Fragment extends Fragment {

    Button order;
    Button membership;
    Button order_list;
    Button event;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.lev1_fragment,container,false);
        order = (Button)rootView.findViewById(R.id.order1);
        membership = (Button)rootView.findViewById(R.id.membership);
        order_list = (Button)rootView.findViewById(R.id.lev1_order_list);
        event = (Button)rootView.findViewById(R.id.event);
        ClickListener CL = new ClickListener();
        order.setOnClickListener(CL);
        membership.setOnClickListener(CL);
        order_list.setOnClickListener(CL);
        event.setOnClickListener(CL);
        return rootView;
    }
    private class ClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.order1:
                    Intent intent = new Intent(getActivity(), OrderActivity.class);
                    getActivity().startActivity(intent);
                    break;
                case R.id.membership:
                    break;
                case R.id.lev1_order_list:
                    break;
                case R.id.event:
                    break;
            }
        }
    }
}
