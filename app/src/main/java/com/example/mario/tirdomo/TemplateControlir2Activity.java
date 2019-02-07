package com.example.mario.tirdomo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mario.tirdomo.utility.utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class TemplateControlir2Activity extends AppCompatActivity {

    ListSelectIcons listSelectIcons;


    /// SON DE PRUEBA ELIMINAR CUANDO TERMINE DE CODIFICAR
    public Integer prueba1 = 0;
    public Integer prueba2;
    public String prueba3;
    //public Integer prueba2 = R.drawable.ic_ac_black_24dp;

    public String vibration1;
    public String vibration2 = "yes";

    public Integer finalIcon;
    public String controlIrName;
    public String learnId;
    public String buttonName;
    public String buttonIcon;
    public String colorBack;
    public Integer finalColor;

    public String buttonPressed = "0";

    public String editButton;//This variable permit edit or not the buttons
    public String editButton2 = "yes";//this variable confirm if select ir learnd codes
    public String editButton3 = "yes2";//this variable confirm if select edit button configuration
    public String editButton4 = "no";
    public Integer editIcon = 0;//Show de edit icon in the button

    private static Socket s = null;///Variables to send data through socket
    private static PrintWriter printWriter;
    String msgSendIp ="";///variable to use the code to send through ip port socket
    String ip = "";
    String portString = "";
    int port = 0;
    String idDevice = "";

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11;
    Button btn12, btn13, btn14, btn15, btn16, btn17, btn18, btn19, btn20, btn21;

    int btn19Active=0;
    int btn20Active=0;

    ConnectionSQLiteHelper conn;

    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template_controlir2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        //Read Sahred preferences to activade or not the vibration
        conn = new ConnectionSQLiteHelper(getApplicationContext(),"bd_devices",null,1);

        //Declare vibration
        vibrator = (Vibrator)this.getSystemService(Context.VIBRATOR_SERVICE);

       //Read shared preferences vibration when a button is pressed
        SharedPreferences sharedPrefer = getSharedPreferences("General", Context.MODE_PRIVATE);
        vibration1 = sharedPrefer.getString("vibration","Not data");

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras!=null){
            // learnId = extras.getString("learnId");
            controlIrName = extras.getString("controlIrName");
            editButton = extras.getString("editButton");
        }

      if(editButton2.equals(editButton)){
          editIcon = R.drawable.ic_edit_black_24dp;
      }

      if(editButton3.equals(editButton)){
            editIcon = R.drawable.ic_edit_black_24dp;
        }
      //else {
        //editIcon = 0;
      //}

        //Search in the table sqlite the name of the control-Ir
        try {//Consult if the control name exist
            SQLiteDatabase db = conn.getReadableDatabase();
            String [] parameters = {controlIrName};
            String [] column = {utility.COLUMN_ID_CONTROLSIR};
            Cursor cursor = db.query(utility.TABLE_CONTROLSIR, column, utility.COLUMN_NAME_CONTROLSIR+"=?", parameters, null, null, null);
            cursor.moveToFirst();
            learnId = (cursor.getString(0));
            //Toast.makeText(getApplicationContext(),getResources().getString(R.string.msg1_edit_controlname)+controlIrName, Toast.LENGTH_LONG).show();
            cursor.close();
            db.close();
        }catch (Exception e){
            //controlIrName = "Error";
        }

        //Actived the button to get back
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(controlIrName);
        }


        //We are going to put principal data in the buttons
        for (int i = 1; i<22; i++) {
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

           finalIcon = Integer.parseInt(listSelectIcons.listIcons(buttonIcon));

            if(editButton3.equals(editButton)){
                finalColor = R.color.Green1;
            }

            if(editButton4.equals(editButton)){
                finalColor = Integer.parseInt(listSelectIcons.listColors(colorBack));
            }

            if(editButton2.equals(editButton)){
                finalColor = R.color.EditColor;
            }//else {
               // finalColor = Integer.parseInt(listSelectIcons.listColors(colorBack));
            //}

            switch (i){
                case 1:
                    btn1 = (Button)findViewById(R.id.btn1);
                    btn1.setText(buttonName);
                    if(finalIcon == 0){
                        btn1.setTextSize(15);
                    }
                    btn1.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    btn1.setBackgroundColor(getResources().getColor(finalColor));
                    break;

                case 2:
                    btn2 = (Button)findViewById(R.id.btn2);
                    btn2.setText(buttonName);
                    if(finalIcon == 0){
                        btn2.setTextSize(15);
                    }
                    btn2.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    btn2.setBackgroundColor(getResources().getColor(finalColor));
                    break;

                case 3:
                    btn3 = (Button)findViewById(R.id.btn3);
                    btn3.setText(buttonName);
                    if(finalIcon == 0){
                        btn3.setTextSize(15);
                    }
                    btn3.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    btn3.setBackgroundColor(getResources().getColor(finalColor));
                    break;

                case 4:
                    btn4 = (Button)findViewById(R.id.btn4);
                    btn4.setText(buttonName);
                    if(finalIcon == 0){
                        btn4.setTextSize(15);
                    }
                    btn4.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    btn4.setBackgroundColor(getResources().getColor(finalColor));
                    break;

                case 5:
                    btn5 = (Button)findViewById(R.id.btn5);
                    btn5.setText(buttonName);
                    if(finalIcon == 0){
                        btn5.setTextSize(15);
                    }
                    btn5.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    btn5.setBackgroundColor(getResources().getColor(finalColor));
                    break;

                case 6:
                    btn6 = (Button)findViewById(R.id.btn6);
                    btn6.setText(buttonName);
                    if(finalIcon == 0){
                        btn6.setTextSize(15);
                    }
                    btn6.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    btn6.setBackgroundColor(getResources().getColor(finalColor));
                    break;

                case 7:
                    btn7 = (Button)findViewById(R.id.btn7);
                    btn7.setText(buttonName);
                    if(finalIcon == 0){
                        btn7.setTextSize(15);
                    }
                    btn7.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    btn7.setBackgroundColor(getResources().getColor(finalColor));
                    break;

                case 8:
                    btn8 = (Button)findViewById(R.id.btn8);
                    btn8.setText(buttonName);
                    if(finalIcon == 0){
                        btn8.setTextSize(15);
                    }

                    btn8.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    btn8.setBackgroundColor(getResources().getColor(finalColor));
                    break;

                case 9:
                    btn9 = (Button)findViewById(R.id.btn9);
                    btn9.setText(buttonName);
                    if(finalIcon == 0){
                        btn9.setTextSize(15);
                    }
                    btn9.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    btn9.setBackgroundColor(getResources().getColor(finalColor));
                    break;

                case 10:
                    btn10 = (Button)findViewById(R.id.btn10);
                    btn10.setText(buttonName);
                    if(finalIcon == 0){
                        btn10.setTextSize(15);
                    }
                    btn10.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    btn10.setBackgroundColor(getResources().getColor(finalColor));
                    break;

                case 11:
                    btn11 = (Button)findViewById(R.id.btn11);
                    btn11.setText(buttonName);
                    if(finalIcon == 0){
                        btn11.setTextSize(15);
                    }
                    btn11.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    btn11.setBackgroundColor(getResources().getColor(finalColor));
                    break;

                case 12:
                    btn12 = (Button)findViewById(R.id.btn12);
                    btn12.setText(buttonName);
                    if(finalIcon == 0){
                        btn12.setTextSize(15);
                    }
                    btn12.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    btn12.setBackgroundColor(getResources().getColor(finalColor));
                    break;

                case 13:
                    btn13 = (Button)findViewById(R.id.btn13);
                    btn13.setText(buttonName);
                    if(finalIcon == 0){
                        btn13.setTextSize(15);
                    }
                    btn13.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    btn13.setBackgroundColor(getResources().getColor(finalColor));
                    break;

                case 14:
                    btn14 = (Button)findViewById(R.id.btn14);
                    btn14.setText(buttonName);
                    if(finalIcon == 0){
                        btn14.setTextSize(15);
                    }
                    btn14.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    btn14.setBackgroundColor(getResources().getColor(finalColor));
                    break;

                case 15:
                    btn15 = (Button)findViewById(R.id.btn15);
                    btn15.setText(buttonName);
                    if(finalIcon == 0){
                        btn15.setTextSize(15);
                    }
                    btn15.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    btn15.setBackgroundColor(getResources().getColor(finalColor));
                    break;

                case 16:
                    btn16 = (Button)findViewById(R.id.btn16);
                    btn16.setText(buttonName);
                    if(finalIcon == 0){
                        btn16.setTextSize(15);
                    }
                    btn16.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    btn16.setBackgroundColor(getResources().getColor(finalColor));
                    break;

                case 17:
                    btn17 = (Button)findViewById(R.id.btn17);
                    btn17.setText(buttonName);
                    if(finalIcon == 0){
                        btn17.setTextSize(15);
                    }
                    btn17.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    btn17.setBackgroundColor(getResources().getColor(finalColor));
                    break;

                case 18:
                    btn18 = (Button)findViewById(R.id.btn18);
                    btn18.setText(buttonName);
                    btn18.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    btn18.setBackgroundColor(getResources().getColor(finalColor));
                    if(finalIcon == 0){
                        btn18.setTextSize(15);
                    }
                    break;

                case 19:
                    btn19 = (Button)findViewById(R.id.btn19);
                    btn19.setText(buttonName);
                    if(finalIcon == 0){
                        btn19.setTextSize(15);
                    }
                    btn19.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    btn19.setBackgroundColor(getResources().getColor(finalColor));
                    if (buttonIcon.equals("123")){
                        btn19Active=0;//button numeric
                    }else {
                        btn19Active=1;//normal butto to send ir code
                    }
                    break;

                case 20:
                    btn20 = (Button)findViewById(R.id.btn20);
                    btn20.setText(buttonName);
                    if(finalIcon == 0){
                        btn20.setTextSize(15);
                    }
                    btn20.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    btn20.setBackgroundColor(getResources().getColor(finalColor));
                    //Select is button favorite or normal button to send  ir code
                    if (buttonIcon.equals("Star1")){
                        btn20Active=0;//button favorite
                    }else {
                        btn20Active=1;//normal butto to send ir code
                    }
                    break;

                case 21:
                    btn21 = (Button)findViewById(R.id.btn21);
                    btn21.setText(buttonName);
                    if(finalIcon == 0){
                        btn21.setTextSize(15);
                    }
                    btn21.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, editIcon, 0);
                    btn21.setBackgroundColor(getResources().getColor(finalColor));
                    break;

                default:
                    break;
            }//finish the switch case
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_template_controlir2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.item_edit_button:
                Intent intent = new Intent(TemplateControlir2Activity.this,TemplateControlir2Activity.class);
                //Recharge the window but with editbutton
                intent.putExtra("editButton", "yes2");
                intent.putExtra("controlIrName", controlIrName);
                intent.putExtra("learnId", learnId);// Send to the next activity the number of control
                startActivity(intent);
                return true;
            case R.id.item_edit_controlname:
                intent = new Intent(TemplateControlir2Activity.this,EditControlTemplateActivity.class);
                //Recharge the window but with editbutton
                intent.putExtra("controlIrName", controlIrName);
                intent.putExtra("learnId", learnId);// Send to the next activity the number of control
                startActivity(intent);
                return true;
            case R.id.item_delete_control:///To delete in the tables the controlir and learn

                //Alert dialog to confirm the delete control ir
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(false);
                builder.setMessage(getResources().getString(R.string.alertmsg4_update_device));
                builder.setPositiveButton(getResources().getString(R.string.alertmsg2_update_device), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//if ok delete the device

                        //Delete de control in the table learn
                        SQLiteDatabase db = conn.getWritableDatabase();
                        String [] parametros = {learnId};

                        db.delete(utility.TABLE_LEARN, utility.COLUMN_LEARN_ID+"=?", parametros);
                        Toast.makeText(getApplicationContext(),getResources().getString(R.string.alertmsg5_delete), Toast.LENGTH_SHORT).show();
                        db.close();

                        //Delete de control in the control ir
                        SQLiteDatabase db2 = conn.getWritableDatabase();
                        String [] parametros2 = {controlIrName};

                        db2.delete(utility.TABLE_CONTROLSIR, utility.COLUMN_NAME_CONTROLSIR+"=?", parametros2);
                        db2.close();


                        Intent intent = new Intent(TemplateControlir2Activity.this, IrControlsActivity.class);
                        startActivity(intent);

                    }
                })
                        .setNegativeButton(getResources().getString(R.string.alertmsg3_update_device), new DialogInterface.OnClickListener() { //if cancel no delete
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.create().show();
                return true;
            case R.id.item_learn_ircodes:
                intent = new Intent(TemplateControlir2Activity.this,TemplateControlir2Activity.class);
                //Recharge the window but with editbutton
                intent.putExtra("editButton", "yes");
                intent.putExtra("controlIrName", controlIrName);
                intent.putExtra("learnId", learnId);// Send to the next activity the number of control
                startActivity(intent);
                return true;
                default:
                    return super.onOptionsItemSelected(item);

        }

    }
///Button pressed in the activity
    public void buttonPressed(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                buttonPressed = "1";
                buttonAction();
                break;
            case R.id.btn2:
                buttonPressed = "2";
                buttonAction();
                break;
            case R.id.btn3:
                buttonPressed = "3";
                buttonAction();
                break;
            case R.id.btn4:
                buttonPressed = "4";
                buttonAction();
                break;
            case R.id.btn5:
                buttonPressed = "5";
                buttonAction();
                break;
            case R.id.btn6:
                buttonPressed = "6";
                buttonAction();
                break;
            case R.id.btn7:
                buttonPressed = "7";
                buttonAction();
                break;
            case R.id.btn8:
                buttonPressed = "8";
                buttonAction();
                break;
            case R.id.btn9:
                buttonPressed = "9";
                buttonAction();
                break;
            case R.id.btn10:
                buttonPressed = "10";
                buttonAction();
                break;
            case R.id.btn11:
                buttonPressed = "11";
                buttonAction();
                break;
            case R.id.btn12:
                buttonPressed = "12";
                buttonAction();
                break;
            case R.id.btn13:
                buttonPressed = "13";
                buttonAction();
                break;
            case R.id.btn14:
                buttonPressed = "14";
                buttonAction();
                break;
            case R.id.btn15:
                buttonPressed = "15";
                buttonAction();
                break;
            case R.id.btn16:
                buttonPressed = "16";
                buttonAction();
                break;
            case R.id.btn17:
                buttonPressed = "17";
                buttonAction();
                break;
            case R.id.btn18:
                buttonPressed = "18";
                buttonAction();
                break;
            case R.id.btn19://Numeric Buttons open a pop up window
                Intent intent = new Intent();
                    intent = new Intent(TemplateControlir2Activity.this, NumericButtonsActivity.class);
                    intent.putExtra("controlIrName", controlIrName);
                    intent.putExtra("learnId", learnId);
                    intent.putExtra("editButton", editButton);
                    startActivity(intent);
                break;
            case R.id.btn20://Favorites star or button in other cases
                if (btn20Active==1) {
                    buttonPressed = "20";
                    buttonAction();
                }else {
                    intent = new Intent(TemplateControlir2Activity.this, Favorites1Activity.class);
                    intent.putExtra("controlIrName", controlIrName);
                    intent.putExtra("learnId", learnId);
                    intent.putExtra("editButton", editButton);
                    startActivity(intent);
                }
                break;
            case R.id.btn21://Extra Buttons
                intent = new Intent(TemplateControlir2Activity.this, ExtraButtonsActivity.class);
                intent.putExtra("controlIrName", controlIrName);
                intent.putExtra("learnId", learnId);
                intent.putExtra("editButton", editButton);
                startActivity(intent);
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

    private void editButtonActivity() {//Go to the activity editbutton1 or editbutton2
        if (editButton2.equals(editButton)) {//select ir learn codes
            Intent intent = new Intent(TemplateControlir2Activity.this, EditButton1Activity.class);
            //Get the name of the new control to send the next window
            intent.putExtra("controlIrName", controlIrName);
            intent.putExtra("learnId", learnId);// Send to the next activity the number of contro
            intent.putExtra("buttonPressed", buttonPressed);// Send to the next activity the number of control
            startActivity(intent);
        }
        if (editButton3.equals(editButton)) {//select edit button configuration
            Intent intent = new Intent(TemplateControlir2Activity.this, EditButton2Activity.class);
            //Get the name of the new control to send the next window
            intent.putExtra("controlIrName", controlIrName);
            intent.putExtra("learnId", learnId);// Send to the next activity the number of contro
            intent.putExtra("buttonPressed", buttonPressed);// Send to the next activity the number of control
            startActivity(intent);
        }
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
