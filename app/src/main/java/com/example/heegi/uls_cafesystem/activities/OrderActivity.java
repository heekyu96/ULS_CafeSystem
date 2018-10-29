package com.example.heegi.uls_cafesystem.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.heegi.uls_cafesystem.R;

public class OrderActivity extends AppCompatActivity {
    Button kanu;
    Button maxim;
    Button nescafe;
    Button greentea;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);
        kanu = (Button)findViewById(R.id.kanu);
    }
}
