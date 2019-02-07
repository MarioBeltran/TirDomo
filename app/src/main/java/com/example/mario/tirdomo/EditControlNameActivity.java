package com.example.mario.tirdomo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mario.tirdomo.utility.utility;

import java.util.ArrayList;

public class EditControlNameActivity extends AppCompatActivity {

    public String codeControl;//this is, if the control is TV or DVD or ...
    public String   device_id;//this is the device to sent websocket information through ip and port
    public String controlIrName;
    public String template_Controlsir;

    String buttonName = "";
    String buttonIcon = "";
    String irCode = "";
    String colorBack = "";
    String learnId = "";
    String editButton = "";

    ArrayList<String> msg1;
    public Integer tableLines;//To save how many lines have the table

    EditText editTextControlName;

    ConnectionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_control_name);

        conn = new ConnectionSQLiteHelper(getApplicationContext(),"bd_devices",null,1);

        editTextControlName = (EditText)findViewById(R.id.editTextControlName);

        //Actived the button to get back
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getResources().getString(R.string.title_activity_editcontrolname));
        }

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras!=null){
            codeControl = extras.getString("codeControl");
            device_id = extras.getString("device_id");
            template_Controlsir = extras.getString("template_Controlsir");
            editButton = extras.getString("editButton");
        }
        //Toast.makeText(getApplicationContext(), device_id+" , "+codeControl, Toast.LENGTH_SHORT).show();
    }

    public void buttonSave(View view) {

        try {//Consult if the control name exist
            SQLiteDatabase db = conn.getReadableDatabase();
            String [] parameters = {editTextControlName.getText().toString()};
            String [] column = {utility.COLUMN_NAME_CONTROLSIR};
            Cursor cursor = db.query(utility.TABLE_CONTROLSIR, column, utility.COLUMN_NAME_CONTROLSIR+"=?", parameters, null, null, null);
            cursor.moveToFirst();
            controlIrName = (cursor.getString(0));
            Toast.makeText(getApplicationContext(),getResources().getString(R.string.msg1_edit_controlname)+controlIrName, Toast.LENGTH_LONG).show();
            cursor.close();
            db.close();
        }catch (Exception e){


        //save the control name,template, device id to control if not exist; in the table CONTROLSIR
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(utility.COLUMN_NAME_CONTROLSIR, editTextControlName.getText().toString());
        values.put(utility.COLUMN_TEMPLATE_CONTROLSIR, template_Controlsir);
        //Long result = db.insert(utility.TABLE_CONTROLSIR, null, values)
            Long result = db.insert(utility.TABLE_CONTROLSIR, utility.COLUMN_ID_CONTROLSIR, values);
            learnId = result.toString();
        db.close();

       /* if (result==-1){
            Toast.makeText(getApplicationContext(),getResources().getString(R.string.msg6_add_device3), Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(),getResources().getString(R.string.msg7_add_device3)+editTextControlName.getText().toString(), Toast.LENGTH_LONG).show();

        }*/

        //Read the kind template(Tv,dvd...) and insert a new data in the table learn with connection with the controlir
            //Consult how many lines get
            SQLiteDatabase db2 =conn.getReadableDatabase();
            String [] parameters = {codeControl};
            String [] column = {utility.COLUMN_PRE_BUTTON_NAME};
            Cursor cursor = db2.query(utility.TABLE_PRECHARGED, column, utility.COLUMN_PRE_ID+"=?", parameters, null, null, null);
            //With this count how many lines get
            tableLines = cursor.getCount();
            cursor.close();
            db2.close();

           copyTableDefault();//copy default table precharged in learn table

       // Intent intent = new Intent(EditControlNameActivity.this,EditButtons1Activity.class);
         Intent intent = new Intent(EditControlNameActivity.this,TemplateControlir2Activity.class);
        //Get the name of the new control to send the next window
        intent.putExtra("controlIrName", editTextControlName.getText().toString());
        intent.putExtra("learnId", learnId);// Send to the next activity the number of contro
            intent.putExtra("editButton", editButton);// Send to the next activity the number of control
        startActivity(intent);
        }
    }

    private void copyTableDefault() {

        String locationId = "";// To sabe the number to search in the pre_location_button
       try{

       for (int i = 1; i<tableLines+1; i++){
           // String locationId = "";// To sabe the number to search in the pre_location_button
            buttonName = "";
            buttonIcon = "";
            irCode = "";
            colorBack = "";
            SQLiteDatabase db = conn.getReadableDatabase();
            locationId = Integer.toString(i);//Integer to string the location_button
            String [] parameters = {codeControl , locationId};
            //String [] column = {utility.COLUMN_PRE_BUTTON_NAME, utility.COLUMN_PRE_BUTTON_ICON, utility.COLUMN_PRE_IRCODE};
            String [] column = {utility.COLUMN_PRE_BUTTON_NAME, utility.COLUMN_PRE_BUTTON_ICON, utility.COLUMN_PRE_IRCODE, utility.COLUMN_PRE_COLORBACK};
            Cursor cursor = db.query(utility.TABLE_PRECHARGED, column, utility.COLUMN_PRE_ID+"=? AND "+utility.COLUMN_PRE_LOCATION_BUTTON+"=?", parameters, null, null, null);
            cursor.moveToFirst();
            buttonName = (cursor.getString(0));
            buttonIcon = (cursor.getString(1));
            irCode = (cursor.getString(2));
            colorBack = (cursor.getString(3));
            cursor.close();
            db.close();

           SQLiteDatabase db2 = conn.getWritableDatabase();
           ContentValues values = new ContentValues();
           values.put(utility.COLUMN_LEARN_ID, learnId);
           values.put(utility.COLUMN_LEARN_LOCATION_BUTTON, locationId);
           values.put(utility.COLUMN_LEARN_BUTTON_NAME, buttonName);
           values.put(utility.COLUMN_LEARN_BUTTON_ICON, buttonIcon);
           values.put(utility.COLUMN_LEARN_IRCODE, irCode);
           values.put(utility.COLUMN_LEARN_DEVICE_NUMBER, device_id);
           values.put(utility.COLUMN_LEARN_COLORBACK, colorBack );
           Long result = db2.insert(utility.TABLE_LEARN, null, values);
           db2.close();


        }
               Toast.makeText(getApplicationContext(),getResources().getString(R.string.msg2_edit_controlname)+" "+editTextControlName.getText().toString(), Toast.LENGTH_LONG).show();

       }catch (Exception e){

           Toast.makeText(getApplicationContext(),"no guardo", Toast.LENGTH_LONG).show();

       }


    }

    public void buttonCancel(View view) {
        //cancel creating the new control
        //Intent intent = new Intent(EditControlNameActivity.this,MainActivity.class);
        Intent intent = new Intent(EditControlNameActivity.this,TemplateControlir1Activity.class);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}
