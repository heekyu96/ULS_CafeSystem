package com.example.heegi.uls_cafesystem.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.heegi.uls_cafesystem.R;

public class OrderActivity extends AppCompatActivity {
    ImageButton kanu;
    ImageButton maxim;
    ImageButton nescafe;
    ImageButton greentea;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);
        kanu = findViewById(R.id.kanu);
        maxim = findViewById(R.id.maxim);
    }
}
