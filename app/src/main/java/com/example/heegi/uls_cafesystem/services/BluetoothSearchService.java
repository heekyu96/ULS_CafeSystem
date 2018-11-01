package com.example.heegi.uls_cafesystem.services;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.recognition.packets.Beacon;
import com.estimote.coresdk.service.BeaconManager;
import com.example.heegi.uls_cafesystem.global.NetworkConnector;

import java.util.List;
import java.util.UUID;

public class BluetoothSearchService extends Service {

    private BeaconManager beaconManager;
    private BeaconRangingListener beaconRangingListener;
    private BeaconRegion beaconRegion;

    private String recentBeaconMinor;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        recentBeaconMinor = "";
        Log.d("BluetoothService", "OnCreate");
        beaconManager = new BeaconManager(getApplicationContext());
        beaconRangingListener = new BeaconRangingListener();
        beaconRegion = new BeaconRegion("monitored region", UUID.fromString("e2c56db5-dffb-48d2-b060-d0f5a71096e0"), null, null);
        beaconManager.setRangingListener(beaconRangingListener);
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(beaconRegion);
            }
        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("BluetoothService", "OnStartConmmand");
        beaconManager.startRanging(beaconRegion);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        beaconManager.stopRanging(beaconRegion);
        super.onDestroy();
    }

    private class BeaconRangingListener implements BeaconManager.BeaconRangingListener {

        @Override
        public void onBeaconsDiscovered(BeaconRegion beaconRegion, List<Beacon> beacons) {
            beaconManager.stopRanging(beaconRegion);
            for (int i = 1; i < beacons.size(); i++) {
                Beacon result = beacons.get(i);
                Log.d("ScanResultLog", (i + 1) + ". SSID : " + result.getMinor()
                        + " RSSI : " + result.getRssi() + " dBm\n");

            }
            if(beacons.size()!=0){
                beaconVerifing(beacons.get(0).getMinor() + "");
            }else{
                beaconVerifing(recentBeaconMinor);
            }


        }

        private void beaconVerifing(String beaconMinor) {

            if (!recentBeaconMinor.equals(beaconMinor)) {
                UserLeveQuery userLeveQuery = new UserLeveQuery();
                userLeveQuery.execute(beaconMinor);

                recentBeaconMinor = beaconMinor;
            }

            beaconManager.startRanging(beaconRegion);

        }


    }

    @SuppressLint("StaticFieldLeak")
    private class UserLeveQuery extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String url = NetworkConnector.getInstance().getDefaultUrl() + "getUserLevel.php?minor=" + strings[0];
            Log.d("CCLAB_URL", url);
            String result = NetworkConnector.getInstance().get(url);
            Log.d("CCLAB_RSLT", result);

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("QueryPost", s);
            if (!s.equals("failed")) {
                sendUserLevelResult(s);
            }
        }
    }

    void sendUserLevelResult(String result) {
        Log.d("sendUserLevelResult", result);
        Intent intent = new Intent("UserLevel");
        intent.putExtra("UserLevel", result);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }


}
