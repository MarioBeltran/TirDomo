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

public class NumericButtonsActivity extends AppCompatActivity {

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


    Button btn22, btn23, btn24, btn25, btn26, btn27, btn28, btn29, btn30, btn31, btn32, btn33;

    ConnectionSQLiteHelper conn;

    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numeric_buttons);

        conn = new ConnectionSQLiteHelper(getApplicationContext(),"bd_devices",null,1);

        ConstraintLayout  viewBackground = (ConstraintLayout) findViewById(R.id.numericButtons);

        ////This code create a popup window
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int heigth = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(heigth*.7));
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
       for (int i = 22; i<34; i++) {
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
            //prueba3 = listSelectIcons.listIcons(buttonIcon);
            finalIcon = Integer.parseInt(listSelectIcons.listIcons(buttonIcon));
            if(editButton2.equals(editButton)){
                //finalColor = R.color.EditColor;
                viewBackground.setBackgroundResource(R.color.EditColor);
            }else {
                //finalColor = Integer.parseInt(listSelectIcons.listColors(colorBack));
            }


            switch (i){
                case 22:
                    btn22 = (Button)findViewById(R.id.btn22);
                    btn22.setText(buttonName);
                    if(finalIcon == 0){
                        btn22.setTextSize(20);
                    }
                    btn22.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    //btn22.setBackgroundColor(getResources().getColor(R.color.EditColor));
                    break;

                case 23:
                    btn23 = (Button)findViewById(R.id.btn23);
                    btn23.setText(buttonName);
                    if(finalIcon == 0){
                        btn23.setTextSize(20);
                    }
                    btn23.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    break;

                case 24:
                    btn24 = (Button)findViewById(R.id.btn24);
                    btn24.setText(buttonName);
                    if(finalIcon == 0){
                        btn24.setTextSize(20);
                    }
                    btn24.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    break;

                case 25:
                    btn25 = (Button)findViewById(R.id.btn25);
                    btn25.setText(buttonName);
                    if(finalIcon == 0){
                        btn25.setTextSize(20);
                    }
                    btn25.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    break;

                case 26:
                    btn26 = (Button)findViewById(R.id.btn26);
                    btn26.setText(buttonName);
                    if(finalIcon == 0){
                        btn26.setTextSize(20);
                    }
                    btn26.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    break;

                case 27:
                    btn27 = (Button)findViewById(R.id.btn27);
                    btn27.setText(buttonName);
                    if(finalIcon == 0){
                        btn27.setTextSize(20);
                    }
                    btn27.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    break;

                case 28:
                    btn28 = (Button)findViewById(R.id.btn28);
                    btn28.setText(buttonName);
                    if(finalIcon == 0){
                        btn28.setTextSize(20);
                    }
                    btn28.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    break;

                case 29:
                    btn29 = (Button)findViewById(R.id.btn29);
                    btn29.setText(buttonName);
                    if(finalIcon == 0){
                        btn29.setTextSize(20);
                    }
                    btn29.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    break;

                case 30:
                    btn30 = (Button)findViewById(R.id.btn30);
                    btn30.setText(buttonName);
                    if(finalIcon == 0){
                        btn30.setTextSize(20);
                    }
                    btn30.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    break;

                case 31:
                    btn31 = (Button)findViewById(R.id.btn31);
                    btn31.setText(buttonName);
                    if(finalIcon == 0){
                        btn31.setTextSize(20);
                    }
                    btn31.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    break;

                case 32:
                    btn32 = (Button)findViewById(R.id.btn32);
                    btn32.setText(buttonName);
                    if(finalIcon == 0){
                        btn32.setTextSize(20);
                    }
                    btn32.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    break;

                case 33:
                    btn33 = (Button)findViewById(R.id.btn33);
                    btn33.setText(buttonName);
                    if(finalIcon == 0){
                        btn33.setTextSize(20);
                    }
                    btn33.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    break;

                default:
                    break;
            }//finish the switch case
        }

    }///final onCreate



    ///Button pressed in the activity
    public void buttonPressed1(View view) {
        switch (view.getId()) {
            case R.id.btn22:
                buttonPressed = "22";
                buttonAction();
            break;
            case R.id.btn23:
                buttonPressed = "23";
                buttonAction();
                break;
            case R.id.btn24:
                buttonPressed = "24";
                buttonAction();
                break;
            case R.id.btn25:
                buttonPressed = "25";
                buttonAction();
                break;
            case R.id.btn26:
                buttonPressed = "26";
                buttonAction();
                break;
            case R.id.btn27:
                buttonPressed = "27";
                buttonAction();
                break;
            case R.id.btn28:
                buttonPressed = "28";
                buttonAction();
                break;
            case R.id.btn29:
                buttonPressed = "29";
                buttonAction();
                break;
            case R.id.btn30:
                buttonPressed = "30";
                buttonAction();
                break;
            case R.id.btn31:
                buttonPressed = "31";
                buttonAction();
                break;
            case R.id.btn32:
                buttonPressed = "32";
                buttonAction();
                break;
            case R.id.btn33:
                buttonPressed = "33";
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
        Intent intent = new Intent(NumericButtonsActivity.this,EditButton1Activity.class);
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
