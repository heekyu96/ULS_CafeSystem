package com.example.heegi.uls_cafesystem.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;

import com.example.heegi.uls_cafesystem.R;
import com.example.heegi.uls_cafesystem.global.NetworkConnector;

public class OrderActivity extends AppCompatActivity {
    CheckBox kanu;
    CheckBox maxim;
    CheckBox nescafe;
    CheckBox greentea;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);
        kanu = findViewById(R.id.kanu);
        maxim = findViewById(R.id.maxim);
        nescafe = findViewById(R.id.nescafe);
        greentea = findViewById(R.id.greentea);

        ClickListener CL = new ClickListener();

        kanu.setOnClickListener(CL);
        maxim.setOnClickListener(CL);
        nescafe.setOnClickListener(CL);
        greentea.setOnClickListener(CL);

    }

    private class ClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.kanu:
                    break;
                case R.id.maxim:
                    break;
                case R.id.nescafe:
                    break;
                case R.id.greentea:
                    break;
            }
        }
    }
    private class OrderQuery extends AsyncTask<String , Void, String >{

        @Override
        protected String doInBackground(String... strings) {
            String url = NetworkConnector.getInstance().getDefaultUrl()+"postUserOrder.php?minor="+strings[0];
            Log.d("CCLAB_URL",url);
            String result = NetworkConnector.getInstance().post(url,strings[1]);
            Log.d("CCLAB_RSLT",result);

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("QueryPost",s);

        }
    }
}
