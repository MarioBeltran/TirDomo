package com.example.mario.tirdomo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mario.tirdomo.utility.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EditButton1Activity extends AppCompatActivity {

    Button btnTest1, btnTest2, btnSaveCode1, btnSaveCode2;

    private static Socket s = null;///Variables to send data through socket
    private static PrintWriter printWriter;
    String msgSendIp = "";///variable to use the code to send through ip port socket
    String ip = "";
    String portString = "";
    int port = 0;
    String idDevice = "";

    public String buttonName;
    public String buttonPressed;
    public String learnId;
    public String controlIrName;

    EditText editButtonName;
    TextView textViewMsgReceiver;

    BufferedReader input;
    String receiverCode1;
    String receiverCode2;

    ConnectionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_button1);

        editButtonName = (EditText) findViewById(R.id.editButtonName);
        textViewMsgReceiver = (TextView) findViewById(R.id.textViewMsgReceiver);
        textViewMsgReceiver.setText("       ");
        btnTest1 = (Button)findViewById(R.id.btnTest1);
        btnTest1.setVisibility(View.INVISIBLE);
        btnTest2 = (Button)findViewById(R.id.btnTest2);
        btnTest2.setVisibility(View.INVISIBLE);
        btnSaveCode1 = (Button)findViewById(R.id.btnSaveCode1);
        btnSaveCode1.setVisibility(View.INVISIBLE);
        btnSaveCode2 = (Button)findViewById(R.id.btnSaveCode2);
        btnSaveCode2.setVisibility(View.INVISIBLE);



        conn = new ConnectionSQLiteHelper(getApplicationContext(), "bd_devices", null, 1);

        //Actived the button to get back
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getResources().getString(R.string.title_activity_editbutton1));
        }

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            // learnId = extras.getString("learnId");
            buttonPressed = extras.getString("buttonPressed");
            learnId = extras.getString("learnId");
            controlIrName = extras.getString("controlIrName");
        }

        try {
            SQLiteDatabase db = conn.getReadableDatabase();
            String[] parameters = {learnId, buttonPressed};
            String[] column = {utility.COLUMN_LEARN_BUTTON_NAME, utility.COLUMN_LEARN_DEVICE_NUMBER};
            Cursor cursor = db.query(utility.TABLE_LEARN, column, utility.COLUMN_LEARN_ID + "=? AND " + utility.COLUMN_LEARN_LOCATION_BUTTON + "=?", parameters, null, null, null);
            cursor.moveToFirst();
            editButtonName.setText(cursor.getString(0));
            idDevice = (cursor.getString(1));
            cursor.close();
            db.close();
        } catch (Exception e) {
        }
        //second search the ip and port in the table device with idDevice
        try {
            SQLiteDatabase db = conn.getReadableDatabase();
            String[] parameters = {idDevice};
            String[] column = {utility.COLUMN_IP_LAN, utility.COLUMN_IP_PORT};
            Cursor cursor = db.query(utility.TABLE_DEVICES, column, utility.COLUMN_ID_DEVICE + "=?", parameters, null, null, null);
            cursor.moveToFirst();
            ip = cursor.getString(0);
            portString = cursor.getString(1);
            port = Integer.parseInt(portString);
            cursor.close();
            db.close();
        } catch (Exception e) {
        }


    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    ///button pressed in the activity
    public void buttonPressed(View view) {
        switch (view.getId()) {
            case R.id.btnLearIr:;
                btnTest1.setVisibility(View.INVISIBLE);
                btnTest2.setVisibility(View.INVISIBLE);
                btnSaveCode1.setVisibility(View.INVISIBLE);
                btnSaveCode2.setVisibility(View.INVISIBLE);
                textViewMsgReceiver.setText(getResources().getString(R.string.msg_waiting));
                msgSendIp = "60/";
                sendSocket mt = new sendSocket();
                mt.execute();

                listenSocket1 mt2 = new listenSocket1();
                mt2.execute();

                break;
            case R.id.btnTest1:
                msgSendIp = receiverCode1;
                mt = new sendSocket();
                mt.execute();
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.msg_sent),Toast.LENGTH_LONG).show();
                break;
            case R.id.btnTest2:
                msgSendIp = receiverCode2;
                mt = new sendSocket();
                mt.execute();
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.msg_sent),Toast.LENGTH_LONG).show();
                break;
            case R.id.btnSaveCode1:
                try{
                SQLiteDatabase db = conn.getWritableDatabase();
                String [] parametros = {learnId, buttonPressed};
                ContentValues values = new ContentValues();
                values.put(utility.COLUMN_LEARN_BUTTON_NAME, editButtonName.getText().toString());
                values.put(utility.COLUMN_LEARN_IRCODE, receiverCode1);
                db.update(utility.TABLE_LEARN, values, utility.COLUMN_LEARN_ID+"=? AND "+utility.COLUMN_LEARN_LOCATION_BUTTON+"=?", parametros);
                db.close();
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.msg_saved) ,Toast.LENGTH_LONG).show();

                    //After finish go to the template2 edit
                    Intent intent = new Intent(EditButton1Activity.this,TemplateControlir2Activity.class);
                    //Get the name of the new control to send the next window
                    intent.putExtra("controlIrName", controlIrName);
                    intent.putExtra("editButton", "yes");// Send to the next activity the number of contro
                    startActivity(intent);
                }catch (Exception e){
                }

                break;
            case R.id.btnSaveCode2:
                try{
                    SQLiteDatabase db = conn.getWritableDatabase();
                    String [] parametros = {learnId, buttonPressed};
                    ContentValues values = new ContentValues();
                    values.put(utility.COLUMN_LEARN_BUTTON_NAME, editButtonName.getText().toString());
                    values.put(utility.COLUMN_LEARN_IRCODE, receiverCode2);
                    db.update(utility.TABLE_LEARN, values, utility.COLUMN_LEARN_ID+"=? AND "+utility.COLUMN_LEARN_LOCATION_BUTTON+"=?", parametros);
                    db.close();

                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.msg_saved),Toast.LENGTH_LONG).show();

                    //After finish go to the template2 edit
                    Intent intent = new Intent(EditButton1Activity.this,TemplateControlir2Activity.class);
                    //Get the name of the new control to send the next window
                    intent.putExtra("controlIrName", controlIrName);
                    intent.putExtra("editButton", "yes");// Send to the next activity the number of contro
                    startActivity(intent);
                }catch (Exception e){
                }
                break;
            case R.id.btnCancelLearn:
                break;
        }
    }


    class sendSocket extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPostExecute(Void aVoid) {
            //super.onPostExecute(aVoid);
            //Toast.makeText(getApplicationContext(),getResources().getString(R.string.msg_sent),Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                //msgSendIp = "Hola mario";
                s = new Socket(ip, port);
                printWriter = new PrintWriter(s.getOutputStream());
                printWriter.write(msgSendIp);
                printWriter.flush();
                printWriter.close();
                s.close();


            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

    }

    //Receive the first short code
    class listenSocket1 extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String d) {
            //super.onPostExecute(s);
           Toast.makeText(getApplicationContext(),d ,Toast.LENGTH_LONG).show();
            //EditText02.setText(d);
            listenSocket2 mt3 = new listenSocket2();
            mt3.execute();
        }

        @Override
        protected String doInBackground(String... params) {
            try{
                s = new Socket(ip, port);
                input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                receiverCode1 = input.readLine();
                //Log.d("Recibi : ", recibirMsg);
                input.close();
                s.close();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            return receiverCode1;

        }

    }


    ///Recibo the long code
    class listenSocket2 extends AsyncTask<String, String, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String d) {
            Toast.makeText(getApplicationContext(),d ,Toast.LENGTH_LONG).show();
            textViewMsgReceiver.setText(getResources().getString(R.string.received_codes));
            btnTest1.setVisibility(View.VISIBLE);
            btnTest2.setVisibility(View.VISIBLE);
            btnSaveCode1.setVisibility(View.VISIBLE);
            btnSaveCode2.setVisibility(View.VISIBLE);
            //super.onPostExecute(s);
            //EditText03.setText(d);
        }

        @Override
        protected String doInBackground(String... strings) {
            try{
                s = new Socket(ip, port);
                input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                receiverCode2 = input.readLine();
                //Log.d("Recibi : ", recibirMsg);
                input.close();
                s.close();

            }catch (Exception e)
            {
                e.printStackTrace();
            }
            return receiverCode2;
        }
    }


}


