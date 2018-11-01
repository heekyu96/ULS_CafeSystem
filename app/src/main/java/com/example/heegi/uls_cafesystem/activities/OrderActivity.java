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
    Button kanu;
    Button maxim;
    Button nescafe;
    Button greentea;
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
            OrderQuery orderQuery = new OrderQuery();

            switch (v.getId()){
                case R.id.kanu:
                    orderQuery.execute("kanu","aaaa");
                    break;
                case R.id.maxim:
                    orderQuery.execute("maxim","aaaa");
                    break;
                case R.id.nescafe:
                    orderQuery.execute("nescafe","aaaa");
                    break;
                case R.id.greentea:
                    orderQuery.execute("greentea","aaaa");
                    break;
            }
        }
    }
    private class OrderQuery extends AsyncTask<String , Void, String >{

        @Override
        protected String doInBackground(String... strings) {
            String url = NetworkConnector.getInstance().getDefaultUrl()+"UserOrder.php?menu="+strings[0]+"&ordername="+strings[1];
            Log.d("CCLAB_URL",url);
            String result = NetworkConnector.getInstance().get(url);
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
