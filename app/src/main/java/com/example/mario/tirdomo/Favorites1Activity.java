package com.example.mario.tirdomo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mario.tirdomo.utility.utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Favorites1Activity extends AppCompatActivity {

    ListSelectIcons listSelectIcons;

    public String buttonPressed = "0";

    public Integer finalIcon;
    public String controlIrName;
    public String learnId;
    public String buttonName;
    public String buttonIcon;
    public String colorBack;
    public String channelNumber;

    public String vibration1;
    public String vibration2 = "yes";

    public String editButton;//This variable permit edit or not the buttons
    public String editButton2 = "yes";
    public Integer editIcon = 0;//Show de edit icon in the button

    private static Socket s = null;///Variables to send data through socket
    private static PrintWriter printWriter;
    public String msgSendIp ="";///variable to use the code to send through ip port socket
    public String msgSendIp2 ="";///variable to know the numbe channel from the table
    public String irCodeChannel1 = "";
    public String irCodeChannel2 = "";
    public String irCodeChannel3 = "";
    public String msgSendIp3 = "";
    String ip = "";
    String portString = "";
    int port = 0;
    String idDevice = "";

    Button btn46, btn47, btn48, btn49, btn50, btn51, btn52, btn53, btn54;
    TextView textBtn46, textBtn47, textBtn48, textBtn49, textBtn50, textBtn51, textBtn52, textBtn53, textBtn54;

    ConnectionSQLiteHelper conn;

    private Vibrator vibrator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites1);

        conn = new ConnectionSQLiteHelper(getApplicationContext(),"bd_devices",null,1);

        ConstraintLayout viewBackground = (ConstraintLayout) findViewById(R.id.favorite1);

        btn46 = (Button)findViewById(R.id.btn46);
        btn47 = (Button)findViewById(R.id.btn47);
        btn48 = (Button)findViewById(R.id.btn48);
        btn49 = (Button)findViewById(R.id.btn49);
        btn50 = (Button)findViewById(R.id.btn50);
        btn51 = (Button)findViewById(R.id.btn51);
        btn52 = (Button)findViewById(R.id.btn52);
        btn53 = (Button)findViewById(R.id.btn53);
        btn54 = (Button)findViewById(R.id.btn54);
        textBtn46 = (TextView) findViewById(R.id.textBtn46);
        textBtn47 = (TextView) findViewById(R.id.textBtn47);
        textBtn48 = (TextView) findViewById(R.id.textBtn48);
        textBtn49 = (TextView) findViewById(R.id.textBtn49);
        textBtn50 = (TextView) findViewById(R.id.textBtn50);
        textBtn51 = (TextView) findViewById(R.id.textBtn51);
        textBtn52 = (TextView) findViewById(R.id.textBtn52);
        textBtn53 = (TextView) findViewById(R.id.textBtn53);
        textBtn54 = (TextView) findViewById(R.id.textBtn54);



        ////This code create a popup window
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int heigth = dm.heightPixels;

        getWindow().setLayout((int)(width*0.9),(int)(heigth*.8));
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

        for (int i = 46; i<55; i++) {
            String locationId = "";// To sabe the number to search in the pre_location_button
            SQLiteDatabase db = conn.getReadableDatabase();
            locationId = Integer.toString(i);//Integer to string the location_button
            //String [] parameters = {codeControl , locationId};
            String[] parameters = {learnId, locationId};
            //String[] column = {utility.COLUMN_LEARN_BUTTON_NAME, utility.COLUMN_LEARN_BUTTON_ICON};
            String[] column = {utility.COLUMN_LEARN_BUTTON_NAME, utility.COLUMN_LEARN_BUTTON_ICON, utility.COLUMN_LEARN_IRCODE};
            Cursor cursor = db.query(utility.TABLE_LEARN, column, utility.COLUMN_LEARN_ID + "=? AND " + utility.COLUMN_LEARN_LOCATION_BUTTON + "=?", parameters, null, null, null);
            cursor.moveToFirst();
            buttonName = (cursor.getString(0));
            buttonIcon = (cursor.getString(1));
            channelNumber = (cursor.getString(2));
            cursor.close();
            db.close();

            //Search wich icon use to the button
            listSelectIcons = new ListSelectIcons();

            finalIcon = Integer.parseInt(listSelectIcons.listIcons(buttonIcon));
            if(editButton2.equals(editButton)){
                //finalColor = R.color.EditColor;
                viewBackground.setBackgroundResource(R.color.EditColor);
            }else {
                //finalColor = Integer.parseInt(listSelectIcons.listColors(colorBack));
            }

            switch (i) {
                case 46:
                    btn46.setText(channelNumber);//Channel number
                    textBtn46.setText(buttonName);//Channel name
                    if (finalIcon == 0) {
                        btn46.setTextSize(18);
                    }
                    btn46.setCompoundDrawablesWithIntrinsicBounds(0, 0, editIcon, 0);
                    break;

                case 47:
                    btn47.setText(channelNumber);//Channel number
                    textBtn47.setText(buttonName);//Channel name
                    if (finalIcon == 0) {
                        btn47.setTextSize(18);
                    }
                    btn47.setCompoundDrawablesWithIntrinsicBounds(0, 0, editIcon, 0);
                    break;
                case 48:
                    btn48.setText(channelNumber);//Channel number
                    textBtn48.setText(buttonName);//Channel name
                    if (finalIcon == 0) {
                        btn48.setTextSize(18);
                    }
                    btn48.setCompoundDrawablesWithIntrinsicBounds(0, 0, editIcon, 0);
                    break;
                case 49:
                    btn49.setText(channelNumber);//Channel number
                    textBtn49.setText(buttonName);//Channel name
                    if (finalIcon == 0) {
                        btn49.setTextSize(18);
                    }
                    btn49.setCompoundDrawablesWithIntrinsicBounds(0, 0, editIcon, 0);
                    break;
                case 50:
                    btn50.setText(channelNumber);//Channel number
                    textBtn50.setText(buttonName);//Channel name
                    if (finalIcon == 0) {
                        btn50.setTextSize(18);
                    }
                    btn50.setCompoundDrawablesWithIntrinsicBounds(0, 0, editIcon, 0);
                    break;
                case 51:
                    btn51.setText(channelNumber);//Channel number
                    textBtn51.setText(buttonName);//Channel name
                    if (finalIcon == 0) {
                        btn51.setTextSize(18);
                    }
                    btn51.setCompoundDrawablesWithIntrinsicBounds(0, 0, editIcon, 0);
                    break;
                case 52:
                    btn52.setText(channelNumber);//Channel number
                    textBtn52.setText(buttonName);//Channel name
                    if (finalIcon == 0) {
                        btn52.setTextSize(18);
                    }
                    btn52.setCompoundDrawablesWithIntrinsicBounds(0, 0, editIcon, 0);
                    break;
                case 53:
                    btn53.setText(channelNumber);//Channel number
                    textBtn53.setText(buttonName);//Channel name
                    if (finalIcon == 0) {
                        btn53.setTextSize(18);
                    }
                    btn53.setCompoundDrawablesWithIntrinsicBounds(0, 0, editIcon, 0);
                    break;
                case 54:
                    btn54.setText(channelNumber);//Channel number
                    textBtn54.setText(buttonName);//Channel name
                    if (finalIcon == 0) {
                        btn54.setTextSize(18);
                    }
                    btn54.setCompoundDrawablesWithIntrinsicBounds(0, 0, editIcon, 0);
                    break;

                    default:
                        break;
            }
        }

    }



    public void buttonPressed3(View view) {
        switch (view.getId()) {
            case R.id.btn46:
                buttonPressed = "46";
                buttonAction();
                break;
            case R.id.btn47:
                buttonPressed = "47";
                buttonAction();
                break;
            case R.id.btn48:
                buttonPressed = "48";
                buttonAction();
                break;
            case R.id.btn49:
                buttonPressed = "49";
                buttonAction();
                break;
            case R.id.btn50:
                buttonPressed = "50";
                buttonAction();
                break;
            case R.id.btn51:
                buttonPressed = "51";
                buttonAction();
                break;
            case R.id.btn52:
                buttonPressed = "52";
                buttonAction();
                break;
            case R.id.btn53:
                buttonPressed = "53";
                buttonAction();
                break;
            case R.id.btn54:
                buttonPressed = "54";
                buttonAction();
                break;

                default:break;
        }
    }

    private void buttonAction() {
        irCodeChannel1="";
        irCodeChannel2="";
        irCodeChannel3="";

        if (editIcon != 0){
            if (vibration1.equals(vibration2)){
                vibrator.vibrate(400);
            }
            editButtonActivity();
        }else{
            if (vibration1.equals(vibration2)){
                vibrator.vibrate(400);
            }
            channelNumberfind();

            for (int i=0; i<msgSendIp2.length();i++){
                //msgSendIp3 = msgSendIp2.substring(i,i+1);
                if (i==0){
                    irCodeChannel1=msgSendIp2.substring(0,1);
                    irCodeChannel1 = channelIrCode(irCodeChannel1);
                }
                if (i==1){
                    irCodeChannel2=msgSendIp2.substring(1,2);
                    irCodeChannel2 = channelIrCode(irCodeChannel2);
                }
                if (i==2){
                    irCodeChannel3=msgSendIp2.substring(2,3);
                    irCodeChannel3 = channelIrCode(irCodeChannel3);
                }
                //Toast.makeText(getApplicationContext(),msgSendIp3,Toast.LENGTH_SHORT).show();
                //sendSocket mt = new sendSocket();
                //mt.execute();
            }
            msgSendIp3 = irCodeChannel1;
            deviceToSend();
            sendSocket mt = new sendSocket();
            mt.execute();

            new CountDownTimer(2000, 1000) {

                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    msgSendIp3 = irCodeChannel2;
                    deviceToSend();
                    sendSocket mt = new sendSocket();
                    mt.execute();
                }

            }.start();

            new CountDownTimer(3000, 1000) {

                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    msgSendIp3 = irCodeChannel3;
                    deviceToSend();
                    sendSocket mt = new sendSocket();
                    mt.execute();
                }

            }.start();


        }
    }

    private String channelIrCode(String irCodeChannel1) {
        switch (irCodeChannel1){
            case "1":
                irCodeChannel1 = "22";
                break;
            case "2":
                irCodeChannel1 = "23";
                break;
            case "3":
                irCodeChannel1 = "24";
                break;
            case "4":
                irCodeChannel1 = "25";
                break;
            case "5":
                irCodeChannel1 = "26";
                break;
            case "6":
                irCodeChannel1 = "27";
                break;
            case "7":
                irCodeChannel1 = "28";
                break;
            case "8":
                irCodeChannel1 = "29";
                break;
            case "9":
                irCodeChannel1 = "30";
                break;
            case "0":
                irCodeChannel1 = "32";
                break;
            default:
                break;
        }
        return irCodeChannel1;
    }

    private void editButtonActivity() {//Go to the activity editbutton1
        Intent intent = new Intent(Favorites1Activity.this,EditButtonFavorite1Activity.class);
        //Get the name of the new control to send the next window
        intent.putExtra("controlIrName", controlIrName);
        intent.putExtra("learnId", learnId);// Send to the next activity the number of contro
        intent.putExtra("buttonPressed", buttonPressed);// Send to the next activity the number of control
        startActivity(intent);
    }

    private void channelNumberfind() {///search in the tables the information of the ip,port and ircode

        msgSendIp2 = "";

        //First search learn_ircode is the number of the channel
        try {
            SQLiteDatabase db = conn.getReadableDatabase();
            String[] parameters = {learnId, buttonPressed};
            String[] column = {utility.COLUMN_LEARN_IRCODE};
            Cursor cursor = db.query(utility.TABLE_LEARN, column, utility.COLUMN_LEARN_ID + "=? AND " + utility.COLUMN_LEARN_LOCATION_BUTTON + "=?", parameters, null, null, null);
            cursor.moveToFirst();
            msgSendIp2 = (cursor.getString(0));
            cursor.close();
            db.close();
        }catch (Exception e){}

    }

    private void deviceToSend() {///search in the tables the information of the ip,port and ircode

        ip ="";
        portString ="";
        port = 0;
        msgSendIp = "";

        //First search de learn_device_number and the learn_ircode in the table learn
        try {
            SQLiteDatabase db = conn.getReadableDatabase();
            String[] parameters = {learnId, msgSendIp3};
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

       //Toast.makeText(getApplicationContext(),msgSendIp,Toast.LENGTH_SHORT).show();

    }

    class sendSocket extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPostExecute(Void aVoid) {
            //super.onPostExecute(aVoid);
            //Toast.makeText(getApplicationContext(),msgSendIp,Toast.LENGTH_LONG).show();
        }

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
