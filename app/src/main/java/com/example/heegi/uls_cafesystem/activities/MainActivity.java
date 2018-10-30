package com.example.heegi.uls_cafesystem.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.example.heegi.uls_cafesystem.fragments.DefaultFragment;
import com.example.heegi.uls_cafesystem.fragments.Level1Fragment;
import com.example.heegi.uls_cafesystem.fragments.Level2Fragment;
import com.example.heegi.uls_cafesystem.fragments.Level3Fragment;
import com.example.heegi.uls_cafesystem.services.BluetoothSearchService;
import com.example.heegi.uls_cafesystem.R;

public class MainActivity extends AppCompatActivity {

    private UserLevelChangeReceiver userLevelChangeReceiver;
    private final static String iFilterName = "UserLevel";

    private FrameLayout frameLayout;
    private FragmentManager fragmentManager;

    private DefaultFragment defaultFragment;
    private Level1Fragment level1Fragment;
    private Level2Fragment level2Fragment;
    private Level3Fragment level3Fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.main_frame);

        defaultFragment = new DefaultFragment();
        level1Fragment = new Level1Fragment();
        level2Fragment = new Level2Fragment();
        level3Fragment = new Level3Fragment();

        fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.main_frame,defaultFragment);
        fragmentTransaction.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        userLevelChangeReceiver = new UserLevelChangeReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(userLevelChangeReceiver, new IntentFilter(iFilterName));

        Intent intent = new Intent(getApplicationContext(), BluetoothSearchService.class);
        startService(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();

        LocalBroadcastManager.getInstance(this).unregisterReceiver(userLevelChangeReceiver);
    }

    private class UserLevelChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String result = intent.getStringExtra("UserLevel");
            Log.d("Main_receiver", "Got message: " + result+"/"+Integer.valueOf(result));

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            switch (Integer.valueOf(result)) {
                case 1:
                    fragmentTransaction.replace(R.id.main_frame,level1Fragment);
                    fragmentTransaction.commit();
                    break;
                case 2:
                    fragmentTransaction.replace(R.id.main_frame,level2Fragment);
                    fragmentTransaction.commit();
                    break;
                case 3:
                    fragmentTransaction.replace(R.id.main_frame,level3Fragment);
                    fragmentTransaction.commit();
                    break;

            }



        }
    }


}
