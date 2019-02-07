package com.example.mario.tirdomo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mario.tirdomo.utility.utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class AddDevice3Activity extends AppCompatActivity {

    EditText editTextIpLan;
    EditText editTextGateway;
    EditText editTextPort;
    EditText editTextNetmask;
    EditText editTextWifiSsid;
    EditText editTextWifiPassword;

    public String   deviceName;
    public String   numberDevice;

    public String   s_gateway;
    public String   s_ipAddress;
    public String   s_netmask;
    public String   s_serverAddress;
    public String   s_ssid;
    public String   s_port;

    private static Socket s;
    private static PrintWriter printWriter;
    String msgSocket ="";
    String msgSocket2 ="";
    String msgSocket3 ="";
    String ipSocket = "192.168.4.1";
    //String ipSocket = "192.168.0.17"; //POR EL MOMENTO DE PRUEBA PARA CON EL COMPUTADOR
    int portSocket =8091;

    ConnectionSQLiteHelper conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device3);

        conn = new ConnectionSQLiteHelper(getApplicationContext(),"bd_devices",null,1);

        editTextIpLan = (EditText)findViewById(R.id.editTextIpLan);
        editTextGateway = (EditText)findViewById(R.id.editTexteGateway);
        editTextPort = (EditText)findViewById(R.id.editTextPort);
        editTextNetmask = (EditText)findViewById(R.id.editTextNetmask);
        editTextWifiSsid = (EditText)findViewById(R.id.editTextWifiSsid);
        editTextWifiPassword = (EditText)findViewById(R.id.editTextWifiPassword);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras!=null){
            s_ipAddress = extras.getString("s_ipAddress");
            editTextIpLan.setText(s_ipAddress);
            s_gateway = extras.getString("s_gateway");
            editTextGateway.setText(s_gateway);
            s_netmask = extras.getString("s_netmask");
            editTextNetmask.setText(s_netmask);
            s_port = extras.getString("s_port");
            editTextPort.setText(s_port);
            s_ssid = extras.getString("s_ssid");
            editTextWifiSsid.setText(s_ssid);

            deviceName = extras.getString("deviceName");
            numberDevice = extras.getString("numberDevice");
            //Toast.makeText(getApplicationContext(), numberDevice+";"+deviceName, Toast.LENGTH_LONG).show();

        }


    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    public void buttonSetUp(View view) {
        //Button to send parameters to configure the device
        consult();
    }

    private void consult() {

        try {//Consult if the device exist
            SQLiteDatabase db = conn.getReadableDatabase();
            String [] parameters = {numberDevice};
            String [] column = {utility.COLUMN_DEVICE_NAME};
            Cursor cursor = db.query(utility.TABLE_DEVICES, column, utility.COLUMN_ID_DEVICE+"=?", parameters, null, null, null);
            cursor.moveToFirst();
            deviceName = (cursor.getString(0));
            Toast.makeText(getApplicationContext(),getResources().getString(R.string.msg5_add_device3)+deviceName, Toast.LENGTH_LONG).show();
            cursor.close();
            db.close();
        }catch (Exception e){//If device no exist insert the new data and send configuration to device

            //sending data to configure ip network in the device
            msgSocket = "AT+CIPSTA_DEF=\""+editTextIpLan.getText().toString()+"\",\""+editTextGateway.getText().toString()+"\",\""+editTextNetmask.getText().toString()+"\";";
            msgSocket2 ="AT+CWJAP=\""+editTextWifiSsid.getText().toString()+"\",\""+editTextWifiPassword.getText().toString()+"\"]";
            msgSocket3 = editTextPort.getText().toString()+";";
            //Toast.makeText(getApplicationContext(), msgSocket, Toast.LENGTH_LONG).show();
            myTask mt = new myTask();
            mt.execute();


            //sending data to configure wifi network in the device
           // msgSocket="";
           // msgSocket="AT+CWJAP=\""+editTextWifiSsid.getText().toString()+"\",\""+editTextWifiPassword.getText().toString()+"\";";
           // Toast.makeText(getApplicationContext(), msgSocket, Toast.LENGTH_LONG).show();;

            //inserting new data to the sqlite
            SQLiteDatabase db = conn.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(utility.COLUMN_ID_DEVICE, numberDevice);
            values.put(utility.COLUMN_DEVICE_NAME, deviceName);
            values.put(utility.COLUMN_IP_LAN, editTextIpLan.getText().toString());
            values.put(utility.COLUMN_IP_PORT, editTextPort.getText().toString());
            Long result = db.insert(utility.TABLE_DEVICES, null, values);
            db.close();

            if (result==-1){
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.msg6_add_device3), Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.msg7_add_device3)+deviceName, Toast.LENGTH_LONG).show();

            }

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(AddDevice3Activity.this, MainActivity.class);
                    startActivity(intent);
                }
            },3000);

        }

    }

    class myTask extends AsyncTask<Void,Void,Void> {


        @Override
        protected Void doInBackground(Void... params) {
            try{
                s=new Socket(ipSocket, portSocket);
                printWriter = new PrintWriter(s.getOutputStream());
                printWriter.write(msgSocket3);
                printWriter.flush();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                printWriter = new PrintWriter(s.getOutputStream());
                printWriter.write(msgSocket);
                printWriter.flush();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                printWriter = new PrintWriter(s.getOutputStream());
                printWriter.write(msgSocket2);
                printWriter.flush();
                printWriter.close();
                s.close();


            }catch (IOException e)
            {
                e.printStackTrace();
            }

            return null;
        }
    }
}
