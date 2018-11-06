package com.example.heegi.uls_cafesystem.services;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.recognition.packets.Beacon;
import com.estimote.coresdk.service.BeaconManager;
import com.example.heegi.uls_cafesystem.global.NetworkConnector;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.NetworkInterface;
import java.util.Collections;
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
            boolean flag=false;
            for (int i = 0; i < beacons.size(); i++) {
                Beacon result = beacons.get(i);
                if(result.getMinor()==13089){
                    flag=true;
                    break;
                }
                Log.d("ScanResultLog", (i + 1) + ". SSID : " + result.getMinor()
                        + " RSSI : " + result.getRssi() + " dBm\n");

            }
//            if(beacons.size()!=0){
//                beaconVerifing(beacons.get(0).getMinor() + "");
//            }else{
//                beaconVerifing(recentBeaconMinor);
//            }
            if(flag){

                WifiManager wifiMan = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);

                UserLeveQuery userLeveQuery = new UserLeveQuery();
                userLeveQuery.execute(recupAdresseMAC(wifiMan));

            }else{
                beaconManager.startRanging(beaconRegion);
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

    private static final String marshmallowMacAddress = "02:00:00:00:00:00";
    private static final String fileAddressMac = "/sys/class/net/wlan0/address";

    public static String recupAdresseMAC(WifiManager wifiMan) {
        WifiInfo wifiInf = wifiMan.getConnectionInfo();

        if(wifiInf.getMacAddress().equals(marshmallowMacAddress)){
            String ret = null;
            try {
                ret= getAdressMacByInterface();
                if (ret != null){
                    return ret;
                } else {
                    ret = getAddressMacByFile(wifiMan);
                    return ret;
                }
            } catch (IOException e) {
                Log.e("MobileAccess", "Erreur lecture propriete Adresse MAC");
            } catch (Exception e) {
                Log.e("MobileAcces", "Erreur lecture propriete Adresse MAC ");
            }
        } else{
            return wifiInf.getMacAddress();
        }
        return marshmallowMacAddress;
    }

    private static String getAdressMacByInterface(){
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (nif.getName().equalsIgnoreCase("wlan0")) {
                    byte[] macBytes = nif.getHardwareAddress();
                    if (macBytes == null) {
                        return "";
                    }

                    StringBuilder res1 = new StringBuilder();
                    for (byte b : macBytes) {
                        res1.append(String.format("%02X:",b));
                    }

                    if (res1.length() > 0) {
                        res1.deleteCharAt(res1.length() - 1);
                    }
                    return res1.toString();
                }
            }

        } catch (Exception e) {
            Log.e("MobileAcces", "Erreur lecture propriete Adresse MAC ");
        }
        return null;
    }

    private static String getAddressMacByFile(WifiManager wifiMan) throws Exception {
        String ret;
        int wifiState = wifiMan.getWifiState();

        wifiMan.setWifiEnabled(true);
        File fl = new File(fileAddressMac);
        FileInputStream fin = new FileInputStream(fl);
        StringBuilder builder = new StringBuilder();
        int ch;
        while((ch = fin.read()) != -1){
            builder.append((char)ch);
        }

        ret = builder.toString();
        fin.close();

        boolean enabled = WifiManager.WIFI_STATE_ENABLED == wifiState;
        wifiMan.setWifiEnabled(enabled);
        return ret;
    }

    @SuppressLint("StaticFieldLeak")
    private class UserLeveQuery extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String url = NetworkConnector.getInstance().getDefaultUrl() + "getUserLevel.php?mac=" + strings[0];
            Log.d("CCLAB_URL", url);
            String result = NetworkConnector.getInstance().get(url);
            Log.d("CCLAB_RSLT", result);

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("QueryPost", s);
            if (s.equals("failed")) {
                sendUserLevelResult("0");
            }else{
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
