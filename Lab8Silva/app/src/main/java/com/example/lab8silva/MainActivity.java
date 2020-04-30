package com.example.lab8silva;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.net.wifi.WifiManager;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.util.Log;
import android.widget.TextView;
import android.bluetooth.BluetoothAdapter;
import android.telephony.TelephonyManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView wifiTxt = this.findViewById(R.id.txtViewWifi);
        TextView blueTxt = this.findViewById(R.id.txtViewBlue);
        TextView cellTxt = this.findViewById(R.id.txtViewCell);

        try {
            TelephonyManager tm = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
            //Get the phone type
            String strphoneType="";
            int phoneType=tm.getPhoneType();

            switch (phoneType)
            {
                case (TelephonyManager.PHONE_TYPE_CDMA):
                    strphoneType="CDMA";
                    break;
                case (TelephonyManager.PHONE_TYPE_GSM):
                    strphoneType="GSM";
                    break;
                case (TelephonyManager.PHONE_TYPE_NONE):
                    strphoneType="NONE";
                    break;
            }

            boolean isRoaming=tm.isNetworkRoaming();

            String info="Phone Details:\n";
            info+="\n Phone Network Type:"+strphoneType;
            info+="\n In Roaming? :"+isRoaming;
            cellTxt.setText(info);

            //Bluetooth
            BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (mBluetoothAdapter == null) {
                blueTxt.setText("Device does not support Bluetooth");
            } else {
                if (!mBluetoothAdapter.isEnabled()) {
                    blueTxt.setText("Bluetooth is not enabled");
                }
                else {
                    blueTxt.setText("Bluetooth is enabled");
                }
            }

            //WiFi
            if (checkWifiOnAndConnected()) {
                wifiTxt.setText("Wifi is Connected");
            }
            else {
                wifiTxt.setText("Wifi is NOT Connected");
            }
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }
    }

    private boolean checkWifiOnAndConnected() {
        WifiManager wifiMgr = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        if (wifiMgr.isWifiEnabled()) { // Wi-Fit adapter is ON
            WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
            return wifiInfo.getNetworkId() != -1; // Not connected to an access point
// Connected to an access point
        }
        else {
            return false; //Wi-Fi adapter is OFF
        }
    }
}

