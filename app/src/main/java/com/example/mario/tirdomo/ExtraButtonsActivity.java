package com.example.mario.tirdomo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Vibrator;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.example.mario.tirdomo.utility.utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ExtraButtonsActivity extends AppCompatActivity {

    ListSelectIcons listSelectIcons;

    public String buttonPressed = "0";

    public Integer finalIcon;
    public String controlIrName;
    public String learnId;
    public String buttonName;
    public String buttonIcon;
    public String colorBack;
    public Integer finalColor;

    public String vibration1;
    public String vibration2 = "yes";

    public String editButton;//This variable permit edit or not the buttons
    public String editButton2 = "yes";
    public Integer editIcon = 0;//Show de edit icon in the button

    private static Socket s = null;///Variables to send data through socket
    private static PrintWriter printWriter;
    String msgSendIp ="";///variable to use the code to send through ip port socket
    String ip = "";
    String portString = "";
    int port = 0;
    String idDevice = "";


    Button btn34, btn35, btn36, btn37, btn38, btn39, btn40, btn41, btn42, btn43, btn44, btn45;

    ConnectionSQLiteHelper conn;

    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_buttons);

        conn = new ConnectionSQLiteHelper(getApplicationContext(),"bd_devices",null,1);

        ConstraintLayout viewBackground = (ConstraintLayout) findViewById(R.id.extraButtons);

        ////This code create a popup window
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int heigth = dm.heightPixels;

        getWindow().setLayout((int)(width*.9),(int)(heigth*.75));
        getWindow().setGravity(Gravity.BOTTOM );

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras!=null){
            learnId = extras.getString("learnId");
            controlIrName = extras.getString("controlIrName");
            editButton = extras.getString("editButton");
        }

        //Declare vibration
        vibrator = (Vibrator)this.getSystemService(Context.VIBRATOR_SERVICE);

        //Read shared preferences vibration when a button is pressed
        SharedPreferences sharedPrefer = getSharedPreferences("General", Context.MODE_PRIVATE);
        vibration1 = sharedPrefer.getString("vibration","Not data");

        if(editButton2.equals(editButton)){
            editIcon = R.drawable.ic_edit_black_24dp;
        }else {
            editIcon = 0;
        }

        //We are going to put principal data in the buttons
        for (int i = 34; i<46; i++) {
            String locationId = "";// To sabe the number to search in the pre_location_button
            SQLiteDatabase db = conn.getReadableDatabase();
            locationId = Integer.toString(i);//Integer to string the location_button
            //String [] parameters = {codeControl , locationId};
            String[] parameters = {learnId, locationId};
            //String[] column = {utility.COLUMN_LEARN_BUTTON_NAME, utility.COLUMN_LEARN_BUTTON_ICON};
            String[] column = {utility.COLUMN_LEARN_BUTTON_NAME, utility.COLUMN_LEARN_BUTTON_ICON, utility.COLUMN_LEARN_COLORBACK};
            Cursor cursor = db.query(utility.TABLE_LEARN, column, utility.COLUMN_LEARN_ID + "=? AND " + utility.COLUMN_LEARN_LOCATION_BUTTON + "=?", parameters, null, null, null);
            cursor.moveToFirst();
            buttonName = (cursor.getString(0));
            buttonIcon = (cursor.getString(1));
            colorBack = (cursor.getString(2));
            cursor.close();
            db.close();

            //Search wich icon use to the button
            listSelectIcons = new ListSelectIcons();
            finalIcon =0;
            //finalIcon = Integer.parseInt(listSelectIcons.listIcons(buttonIcon));
            if(editButton2.equals(editButton)){
                //finalColor = R.color.EditColor;
                viewBackground.setBackgroundResource(R.color.EditColor);
            }else {
                //finalColor = Integer.parseInt(listSelectIcons.listColors(colorBack));
            }


            switch (i){
                case 34:
                    btn34 = (Button)findViewById(R.id.btn34);
                    btn34.setText(buttonName);
                    if(finalIcon == 0){
                        btn34.setTextSize(15);
                    }
                    btn34.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    //btn22.setBackgroundColor(getResources().getColor(R.color.EditColor));
                    break;

                case 35:
                    btn35 = (Button)findViewById(R.id.btn35);
                    btn35.setText(buttonName);
                    if(finalIcon == 0){
                        btn35.setTextSize(15);
                    }
                    btn35.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    break;

                case 36:
                    btn36 = (Button)findViewById(R.id.btn36);
                    btn36.setText(buttonName);
                    if(finalIcon == 0){
                        btn36.setTextSize(15);
                    }
                    btn36.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    break;

                case 37:
                    btn37 = (Button)findViewById(R.id.btn37);
                    btn37.setText(buttonName);
                    if(finalIcon == 0){
                        btn37.setTextSize(15);
                    }
                    btn37.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    break;

                case 38:
                    btn38 = (Button)findViewById(R.id.btn38);
                    btn38.setText(buttonName);
                    if(finalIcon == 0){
                        btn38.setTextSize(15);
                    }
                    btn38.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    break;

                case 39:
                    btn39 = (Button)findViewById(R.id.btn39);
                    btn39.setText(buttonName);
                    if(finalIcon == 0){
                        btn39.setTextSize(15);
                    }
                    btn39.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    break;

                case 40:
                    btn40 = (Button)findViewById(R.id.btn40);
                    btn40.setText(buttonName);
                    if(finalIcon == 0){
                        btn40.setTextSize(15);
                    }
                    btn40.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    break;

                case 41:
                    btn41 = (Button)findViewById(R.id.btn41);
                    btn41.setText(buttonName);
                    if(finalIcon == 0){
                        btn41.setTextSize(15);
                    }
                    btn41.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    break;

                case 42:
                    btn42 = (Button)findViewById(R.id.btn42);
                    btn42.setText(buttonName);
                    if(finalIcon == 0){
                        btn42.setTextSize(15);
                    }
                    btn42.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    break;

                case 43:
                    btn43 = (Button)findViewById(R.id.btn43);
                    btn43.setText(buttonName);
                    if(finalIcon == 0){
                        btn43.setTextSize(15);
                    }
                    btn43.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    break;

                case 44:
                    btn44 = (Button)findViewById(R.id.btn44);
                    btn44.setText(buttonName);
                    if(finalIcon == 0){
                        btn44.setTextSize(15);
                    }
                    btn44.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    break;

                case 45:
                    btn45 = (Button)findViewById(R.id.btn45);
                    btn45.setText(buttonName);
                    if(finalIcon == 0){
                        btn45.setTextSize(15);
                    }
                    btn45.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    break;

                default:
                    break;
            }//finish the switch case
        }

    }


    //Button pressed action
    public void buttonPressed2(View view) {
        switch (view.getId()) {
            case R.id.btn34:
                buttonPressed = "34";
                buttonAction();
                break;
            case R.id.btn35:
                buttonPressed = "35";
                buttonAction();
                break;
            case R.id.btn36:
                buttonPressed = "36";
                buttonAction();
                break;
            case R.id.btn37:
                buttonPressed = "37";
                buttonAction();
                break;
            case R.id.btn38:
                buttonPressed = "38";
                buttonAction();
                break;
            case R.id.btn39:
                buttonPressed = "39";
                buttonAction();
                break;
            case R.id.btn40:
                buttonPressed = "40";
                buttonAction();
                break;
            case R.id.btn41:
                buttonPressed = "41";
                buttonAction();
                break;
            case R.id.btn42:
                buttonPressed = "42";
                buttonAction();
                break;
            case R.id.btn43:
                buttonPressed = "43";
                buttonAction();
                break;
            case R.id.btn44:
                buttonPressed = "44";
                buttonAction();
                break;
            case R.id.btn45:
                buttonPressed = "45";
                buttonAction();
                break;

            default:
                break;
        }
    }

    private void buttonAction() {
        if (editIcon != 0){
            if (vibration1.equals(vibration2)){
                vibrator.vibrate(400);
            }
            editButtonActivity();
        }else{
            if (vibration1.equals(vibration2)){
                vibrator.vibrate(400);
            }
            deviceToSend();
            sendSocket mt = new sendSocket();
            mt.execute();
        }
    }

    private void editButtonActivity() {//Go to the activity editbutton1
        Intent intent = new Intent(ExtraButtonsActivity.this,EditButton1Activity.class);
        //Get the name of the new control to send the next window
        intent.putExtra("controlIrName", controlIrName);
        intent.putExtra("learnId", learnId);// Send to the next activity the number of contro
        intent.putExtra("buttonPressed", buttonPressed);// Send to the next activity the number of control
        startActivity(intent);
    }

    private void deviceToSend() {///search in the tables the information of the ip,port and ircode

        ip ="";
        portString ="";
        port = 0;

        //First search de learn_device_number and the learn_ircode in the table learn
        try {
            SQLiteDatabase db = conn.getReadableDatabase();
            String[] parameters = {learnId, buttonPressed};
            String[] column = {utility.COLUMN_LEARN_IRCODE, utility.COLUMN_LEARN_DEVICE_NUMBER};
            Cursor cursor = db.query(utility.TABLE_LEARN, column, utility.COLUMN_LEARN_ID + "=? AND " + utility.COLUMN_LEARN_LOCATION_BUTTON + "=?", parameters, null, null, null);
            cursor.moveToFirst();
            msgSendIp = (cursor.getString(0));
            idDevice = (cursor.getString(1));
            cursor.close();
            db.close();
        }catch (Exception e){}

        //second search the ip and port in the table device with idDevice
        try {
            SQLiteDatabase db = conn.getReadableDatabase();
            String [] parameters = {idDevice};
            String [] column = {utility.COLUMN_IP_LAN, utility.COLUMN_IP_PORT};
            Cursor cursor = db.query(utility.TABLE_DEVICES, column, utility.COLUMN_ID_DEVICE+"=?", parameters, null, null, null);
            cursor.moveToFirst();
            ip = cursor.getString(0);
            portString = cursor.getString(1);
            port = Integer.parseInt(portString);
            cursor.close();
            db.close();
        }catch (Exception e){}

        //Toast.makeText(getApplicationContext(),msgSendIp,Toast.LENGTH_LONG).show();

    }

    class sendSocket extends AsyncTask<Void,Void,Void> {


        @Override
        protected Void doInBackground(Void... params) {
            try{
                //msgSendIp = "Hola mario";
                s=new Socket(ip, port);
                printWriter = new PrintWriter(s.getOutputStream());
                printWriter.write(msgSendIp);
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
