package com.example.heegi.uls_cafesystem.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.example.heegi.uls_cafesystem.R;

public class DefaultFragment extends Fragment {

    private Toolbar toolbar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.default_fragment,container,false);

        toolbar = rootView.findViewById(R.id.my_toolbar);
        //툴바 설정


        return rootView;
    }
}
