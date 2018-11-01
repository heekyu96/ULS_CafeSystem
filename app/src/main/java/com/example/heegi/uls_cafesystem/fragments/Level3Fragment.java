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
import android.widget.ImageButton;

import com.example.heegi.uls_cafesystem.R;
import com.example.heegi.uls_cafesystem.activities.OrderActivity;

public class Level3Fragment extends Fragment {

    ImageButton light;
    ImageButton air;
    ImageButton mc;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.lev3_fragment,container,false);
        light = rootView.findViewById(R.id.lightbutton);
        air = rootView.findViewById(R.id.airbutton);
        mc = rootView.findViewById(R.id.mcbutton);



        return rootView;
    }

}
