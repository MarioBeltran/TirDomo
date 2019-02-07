package com.example.mario.tirdomo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.DhcpInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AddDevice2Activity extends AppCompatActivity {

    DhcpInfo d;
    WifiManager wifii;
    WifiInfo wifiInfo;

    public String   deviceName;
    public String   numberDevice;

    public String   s_gateway;
    public String   s_ipAddress;
    public String   s_netmask;
    public String   s_serverAddress;
    public String   s_ssid;

    public String   s_port;
    String changeIpAddres;



    @SuppressLint("WifiManagerLeak")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device2);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras!=null){
            numberDevice = extras.getString("numberDevice");
            deviceName = extras.getString("deviceName");
        }

        wifii= (WifiManager) getSystemService(WIFI_SERVICE);
        d=wifii.getDhcpInfo();
        WifiInfo wifiInfo = wifii.getConnectionInfo();

        s_ipAddress=intToIp2(d.ipAddress);
        s_gateway =intToIp(d.gateway);
        s_netmask=intToIp(d.netmask);
        //s_serverAddress=intToIp(d.serverAddress);
        s_ssid = wifiInfo.getSSID();
        s_ssid = s_ssid.substring(1);//Delete the first caracter because is this " and i dont need it
        s_ssid = s_ssid.substring(0,s_ssid.length()-1);//Delete the las character because is this " and i dont need it

        //Toast.makeText(getApplicationContext(),"ip: "+s_ipAddress+" gateway: "+s_gateway+" netmask: "+s_netmask+" ssid: "+s_ssid,Toast.LENGTH_LONG).show();

    }

    public String intToIp(int addr) {
        return ((addr & 0xFF) + "." +
                ((addr >>>= 8) & 0xFF) + "." +
                ((addr >>>= 8) & 0xFF) + "." +
                ((addr >>>= 8) & 0xFF));
    }

    public String intToIp2(int addr) {
        switch (numberDevice){
            case "1":
                s_port = "8081";
                changeIpAddres =".91";
                break;
            case "2":
                s_port = "8082";
                changeIpAddres =".92";
                break;
            case "3":
                s_port = "8083";
                changeIpAddres =".93";
                break;
            case "4":
                s_port = "8084";
                changeIpAddres =".94";
                break;
            case "5":
                s_port = "8085";
                changeIpAddres =".95";
                break;
            case "6":
                s_port = "8086";
                changeIpAddres =".96";
                break;
            case "7":
                s_port = "8087";
                changeIpAddres =".97";
                break;
            case "8":
                s_port = "8088";
                changeIpAddres =".98";
                break;
            case "9":
                s_port = "8089";
                changeIpAddres =".99";
                break;
            case "10":
                s_port = "8090";
                changeIpAddres =".100";
                break;
        }
        return  ((addr & 0xFF) + "." +
                ((addr >>>= 8) & 0xFF) + "." +
                ((addr >>>= 8) & 0xFF) + changeIpAddres);
                //((addr >>>= 8) & 0xFF) + ".100");
    }

    public void button_next2(View view) {
        //Button to go the next part of configuration the  new device is the final configuration devices
        Intent intent = new Intent(AddDevice2Activity.this,AddDevice3Activity.class);
        //Send information to the next configuration window
        intent.putExtra("s_ipAddress", s_ipAddress);
        intent.putExtra("s_gateway", s_gateway);
        intent.putExtra("s_netmask", s_netmask);
        intent.putExtra("s_ssid", s_ssid);
        intent.putExtra("s_port", s_port);
        intent.putExtra("numberDevice", numberDevice);
        intent.putExtra("deviceName", deviceName);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}
