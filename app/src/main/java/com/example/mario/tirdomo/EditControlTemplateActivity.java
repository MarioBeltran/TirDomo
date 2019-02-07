package com.example.mario.tirdomo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mario.tirdomo.entity.devices;
import com.example.mario.tirdomo.utility.utility;

import java.util.ArrayList;

public class EditControlTemplateActivity extends AppCompatActivity {

    ListView listViewInfoDevices;
    ArrayList <String> InformationList;
    ArrayList <devices> DevicesList;

    Button btn_saveEditControlName, btn_saveDeviControl;
    EditText editControlName;
    Spinner selectDevices;

    ConnectionSQLiteHelper conn;

    public String learnId;
    public String controlIrName;
    public String device_id;
    public Integer tableLines;//To save how many lines have the table
    public String idDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_control_template);

        btn_saveEditControlName = (Button) findViewById(R.id.btn_saveEditControlName);
        btn_saveDeviControl = (Button) findViewById(R.id.btn_saveDeviceControl);
        editControlName = (EditText) findViewById(R.id.editControlName);
        selectDevices = (Spinner) findViewById(R.id.selectDevices);

        conn = new ConnectionSQLiteHelper(getApplicationContext(),"bd_devices",null,1);

        //Actived the button to get back
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getResources().getString(R.string.title_editcontroltemplate));
        }

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            learnId = extras.getString("learnId");
            controlIrName = extras.getString("controlIrName");
        }

        editControlName.setText(controlIrName);

        //selectDevices.setVisibility(View.INVISIBLE);

        consultListDevices();

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, InformationList);
        selectDevices.setAdapter(adaptador);

        selectDevices.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent , View view, int position, long id) {
                device_id = DevicesList.get(position).getId_Device().toString();
                //Toast.makeText(getApplicationContext(),"Posicion "+device_id, Toast.LENGTH_LONG).show();
                //device_id = DevicesList.get(position).getId_Device().toString();
                //numberDevice = ""+parent.getItemIdAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    /*    try {
            SQLiteDatabase db = conn.getReadableDatabase();
            String[] parameters = {learnId, "45"};
            String[] column = {utility.COLUMN_LEARN_DEVICE_NUMBER};
            Cursor cursor = db.query(utility.TABLE_LEARN, column, utility.COLUMN_LEARN_ID + "=? AND " + utility.COLUMN_LEARN_LOCATION_BUTTON + "=?", parameters, null, null, null);
            cursor.moveToFirst();
            idDevice = (cursor.getString(0));
            cursor.close();
            db.close();
            Toast.makeText(getApplicationContext(),"Dispositivonumero "+idDevice, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"No muestra nada", Toast.LENGTH_LONG).show();
        }*/

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
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

        //InformationList.add(getResources().getString(R.string.editcontroltemplate_msg1));
        for (int i = 0; i<DevicesList.size(); i++){
            //InformationList.add(DevicesList.get(i).getId_Device()+" - "+DevicesList.get(i).getDevice_Name()+" - Port: "+DevicesList.get(i).getIp_port());
            InformationList.add(DevicesList.get(i).getId_Device()+" - "+DevicesList.get(i).getDevice_Name());
        }

    }

    public void updateControl(View view) {
        SQLiteDatabase db = conn.getReadableDatabase();
        String [] parametros = {learnId};
        ContentValues values = new ContentValues();
        values.put(utility.COLUMN_NAME_CONTROLSIR, editControlName.getText().toString());

        db.update(utility.TABLE_CONTROLSIR, values, utility.COLUMN_ID_CONTROLSIR+"=?", parametros);
        //Toast.makeText(getApplicationContext(),getResources().getString(R.string.msg_update), Toast.LENGTH_LONG).show();
        db.close();

        Intent intent = new Intent(EditControlTemplateActivity.this,IrControlsActivity.class);
        startActivity(intent);
    }


    public void updateDevice(View view) {
        //to know how many lines change
        try {
            SQLiteDatabase db = conn.getReadableDatabase();
            String [] parameters = {learnId};
            String [] column = {utility.COLUMN_LEARN_DEVICE_NUMBER};
            Cursor cursor = db.rawQuery("SELECT * FROM " + utility.TABLE_LEARN, null);
            //With this count how many lines get
            tableLines = cursor.getCount();
            //tableLines = Integer.toString(cursor.getCount());
            cursor.close();
            db.close();
            //Toast.makeText(getApplicationContext(), countLines , Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
        }

        //change the line COLUN_LARN_DEVICE_NUMBER in all the lines
        String locationId = "";// To sabe the number to search in the pre_location_button
        try{

            for (int i = 1; i<tableLines+1; i++){
                locationId = Integer.toString(i);//Integer to string the location_button
                SQLiteDatabase db = conn.getWritableDatabase();
                String [] parametros = {learnId, locationId};
                ContentValues values = new ContentValues();
                values.put(utility.COLUMN_LEARN_DEVICE_NUMBER, device_id);
                db.update(utility.TABLE_LEARN, values, utility.COLUMN_LEARN_ID+"=? AND "+utility.COLUMN_LEARN_LOCATION_BUTTON+"=?", parametros);
                db.close();
            }
            Toast.makeText(getApplicationContext(),"update device", Toast.LENGTH_LONG).show();

        }catch (Exception e){

            Toast.makeText(getApplicationContext(),"no guardo", Toast.LENGTH_LONG).show();
        }

        Intent intent = new Intent(EditControlTemplateActivity.this,IrControlsActivity.class);
        startActivity(intent);

    }




}
