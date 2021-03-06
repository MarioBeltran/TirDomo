package com.example.mario.tirdomo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mario.tirdomo.entity.devices;
import com.example.mario.tirdomo.utility.utility;

import java.util.ArrayList;

public class InfoDevicesActivity extends AppCompatActivity {

    ListView listViewInfoDevices;
    ArrayList <String> InformationList;
    ArrayList <devices> DevicesList;

    public String   device_id;//To send the next window  for update, eliminate

    ConnectionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_devices);

        //Actived the button to get back
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            //actionBar.setTitle(getResources().getString(R.string.title_infodevices));
        }

        conn = new ConnectionSQLiteHelper(getApplicationContext(),"bd_devices",null,1);
        listViewInfoDevices = (ListView)findViewById(R.id.listViewInfoDevices);

        consultListDevices();

        ArrayAdapter adaptader = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, InformationList);
        listViewInfoDevices.setAdapter(adaptader);

        listViewInfoDevices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                device_id = DevicesList.get(position).getId_Device().toString();
                Intent intent = new Intent(InfoDevicesActivity.this,UpdateDeviceActivity.class);
                intent.putExtra("device_id", device_id);
                startActivity(intent);

                //Toast.makeText(getApplicationContext(), device_id, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void consultListDevices() {
        SQLiteDatabase db = conn.getReadableDatabase();

        devices Devices = null;
        DevicesList = new ArrayList<devices>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + utility.TABLE_DEVICES, null);
        while (cursor.moveToNext()){
            Devices = new devices();
            Devices.setId_Device(cursor.getInt(0));
            Devices.setDevice_Name(cursor.getString(1));
            Devices.setIp_Lan(cursor.getString(2));
            Devices.setIp_port(cursor.getString(3));
            DevicesList.add(Devices);
        }
        cursor.close();
        GetListdevices();
    }

    private void GetListdevices() {
        InformationList = new ArrayList<String>();

        for (int i = 0; i<DevicesList.size(); i++){
            InformationList.add(DevicesList.get(i).getId_Device()+" - "+DevicesList.get(i).getDevice_Name()+" - Port: "+DevicesList.get(i).getIp_port());
        }

    }

  /*  @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }*/
}
