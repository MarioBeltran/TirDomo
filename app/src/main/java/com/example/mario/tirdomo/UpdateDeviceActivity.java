package com.example.mario.tirdomo;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mario.tirdomo.utility.utility;

public class UpdateDeviceActivity extends AppCompatActivity {

    public String   device_id;// Variable to consult data
    EditText editText_updatedeviceId;
    EditText editText_updatedeviceName;
    EditText editText_updatedeviceIplan;
    EditText editText_updatedevicePort;

    ConnectionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_device);

        conn = new ConnectionSQLiteHelper(getApplicationContext(),"bd_devices",null,1);

        //Actived the butto to get back
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        editText_updatedeviceId = (EditText)findViewById(R.id.editText_updatedeviceId);
        editText_updatedeviceName = (EditText)findViewById(R.id.editText_updatedeviceName);
        editText_updatedeviceIplan = (EditText)findViewById(R.id.editText_updatedeviceIplan);
        editText_updatedevicePort = (EditText)findViewById(R.id.editText_updatedevicePort);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras!=null) {
            device_id = extras.getString("device_id");
        }

            SQLiteDatabase db = conn.getReadableDatabase();
            String [] parameters = {device_id};
            String [] column = {utility.COLUMN_ID_DEVICE, utility.COLUMN_DEVICE_NAME, utility.COLUMN_IP_LAN, utility.COLUMN_IP_PORT};

            try {
                Cursor cursor = db.query(utility.TABLE_DEVICES, column, utility.COLUMN_ID_DEVICE+"=?", parameters, null, null, null);
                cursor.moveToFirst();
                editText_updatedeviceId.setText(cursor.getString(0));
                editText_updatedeviceName.setText(cursor.getString(1));
                editText_updatedeviceIplan.setText(cursor.getString(2));
                editText_updatedevicePort.setText(cursor.getString(3));
                cursor.close();
                db.close();
            }catch (Exception e){}

    }


    public void button_Update(View view) {//Update the new information of device
        SQLiteDatabase db = conn.getReadableDatabase();
        String [] parametros = {editText_updatedeviceId.getText().toString()};
        ContentValues values = new ContentValues();
        values.put(utility.COLUMN_DEVICE_NAME, editText_updatedeviceName.getText().toString());
        values.put(utility.COLUMN_IP_LAN, editText_updatedeviceIplan.getText().toString());
        values.put(utility.COLUMN_IP_PORT, editText_updatedevicePort.getText().toString());

        db.update(utility.TABLE_DEVICES, values, utility.COLUMN_ID_DEVICE+"=?", parametros);
        Toast.makeText(getApplicationContext(),"Is updated", Toast.LENGTH_LONG).show();
        db.close();
    }

    public void button_Delete(View view) {//Delete the device

        //Alert dialog to confirm the delete of device
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage(getResources().getString(R.string.alertmsg1_update_device));
        builder.setPositiveButton(getResources().getString(R.string.alertmsg2_update_device), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {//if ok delete the device

                SQLiteDatabase db = conn.getWritableDatabase();
                String [] parametros = {editText_updatedeviceId.getText().toString()};

                db.delete(utility.TABLE_DEVICES, utility.COLUMN_ID_DEVICE+"=?", parametros);
                Toast.makeText(getApplicationContext(),"Is deleted", Toast.LENGTH_SHORT).show();
                db.close();

                Intent intent = new Intent(UpdateDeviceActivity.this, InfoDevicesActivity.class);
                startActivity(intent);

            }
        })
                .setNegativeButton(getResources().getString(R.string.alertmsg3_update_device), new DialogInterface.OnClickListener() { //if cancel no import and delte
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.create().show();

    }
}
