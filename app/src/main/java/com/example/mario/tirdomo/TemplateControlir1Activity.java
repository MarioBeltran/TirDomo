package com.example.mario.tirdomo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mario.tirdomo.utility.utility;


public class TemplateControlir1Activity extends AppCompatActivity {

    ListSelectIcons listSelectIcons;


    /// SON DE PRUEBA ELIMINAR CUANDO TERMINE DE CODIFICAR
    public Integer prueba1 = 0;
    public Integer prueba2;
    public String prueba3;
    //public Integer prueba2 = R.drawable.ic_ac_black_24dp;


    public Integer finalIcon;
    public String controlIrName;
    public String learnId;
    public String buttonName;
    public String buttonIcon;
    public String colorBack;
    public Integer finalColor;

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11;
    Button btn12, btn13, btn14, btn15, btn16, btn17, btn18, btn19, btn20, btn21;

    ConnectionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template_controlir1);

        conn = new ConnectionSQLiteHelper(getApplicationContext(),"bd_devices",null,1);

        //btn1.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_ac_black_24dp,0,0);
       // btn1.setBackgroundColor(getResources().getColor(R.color.colorAccent));

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras!=null){
           // learnId = extras.getString("learnId");
            controlIrName = extras.getString("controlIrName");
        }

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


       /* //Search in the table sqlite the name of the control-Ir
        try {//Consult if the control name exist
            SQLiteDatabase db = conn.getReadableDatabase();
            String [] parameters = {learnId};
            String [] column = {utility.COLUMN_NAME_CONTROLSIR};
            Cursor cursor = db.query(utility.TABLE_CONTROLSIR, column, utility.COLUMN_ID_CONTROLSIR+"=?", parameters, null, null, null);
            cursor.moveToFirst();
            controlIrName = (cursor.getString(0));
            //Toast.makeText(getApplicationContext(),getResources().getString(R.string.msg1_edit_controlname)+controlIrName, Toast.LENGTH_LONG).show();
            cursor.close();
            db.close();
        }catch (Exception e){
            controlIrName = "Error";
        }*/

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
            //prueba3 = listSelectIcons.listIcons(buttonIcon);
           finalColor = Integer.parseInt(listSelectIcons.listColors(colorBack));
            finalIcon = Integer.parseInt(listSelectIcons.listIcons(buttonIcon));


            switch (i){
                case 1:
                    btn1 = (Button)findViewById(R.id.btn1);
                    btn1.setText(buttonName);
                    if(finalIcon == 0){
                        btn1.setTextSize(15);
                    }
                    btn1.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                    btn1.setBackgroundColor(getResources().getColor(finalColor));
                    break;

                case 2:
                    btn2 = (Button)findViewById(R.id.btn2);
                    btn2.setText(buttonName);
                    if(finalIcon == 0){
                        btn2.setTextSize(15);
                    }
                    btn2.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                    btn2.setBackgroundColor(getResources().getColor(finalColor));
                    break;

                case 3:
                    btn3 = (Button)findViewById(R.id.btn3);
                    btn3.setText(buttonName);
                    if(finalIcon == 0){
                        btn3.setTextSize(15);
                    }
                    btn3.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                    btn3.setBackgroundColor(getResources().getColor(finalColor));
                    break;

                case 4:
                    btn4 = (Button)findViewById(R.id.btn4);
                    btn4.setText(buttonName);
                    if(finalIcon == 0){
                        btn4.setTextSize(15);
                    }
                    btn4.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                    btn4.setBackgroundColor(getResources().getColor(finalColor));
                    break;

                case 5:
                    btn5 = (Button)findViewById(R.id.btn5);
                    btn5.setText(buttonName);
                    if(finalIcon == 0){
                        btn5.setTextSize(15);
                    }
                    btn5.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                    btn5.setBackgroundColor(getResources().getColor(finalColor));
                    break;

                case 6:
                    btn6 = (Button)findViewById(R.id.btn6);
                    btn6.setText(buttonName);
                    if(finalIcon == 0){
                        btn6.setTextSize(15);
                    }
                    btn6.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                    btn6.setBackgroundColor(getResources().getColor(finalColor));
                    break;

                case 7:
                    btn7 = (Button)findViewById(R.id.btn7);
                    btn7.setText(buttonName);
                    if(finalIcon == 0){
                        btn7.setTextSize(15);
                    }
                    btn7.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                    btn7.setBackgroundColor(getResources().getColor(finalColor));
                    break;

                case 8:
                    btn8 = (Button)findViewById(R.id.btn8);
                    btn8.setText(buttonName);
                    if(finalIcon == 0){
                        btn8.setTextSize(15);
                    }

                    btn8.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                    btn8.setBackgroundColor(getResources().getColor(finalColor));
                    break;

                case 9:
                    btn9 = (Button)findViewById(R.id.btn9);
                    btn9.setText(buttonName);
                    if(finalIcon == 0){
                        btn9.setTextSize(15);
                    }
                    btn9.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                    btn9.setBackgroundColor(getResources().getColor(finalColor));
                    break;

                case 10:
                    btn10 = (Button)findViewById(R.id.btn10);
                    btn10.setText(buttonName);
                    if(finalIcon == 0){
                        btn10.setTextSize(15);
                    }
                    btn10.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                    btn10.setBackgroundColor(getResources().getColor(finalColor));
                    break;

                case 11:
                    btn11 = (Button)findViewById(R.id.btn11);
                    btn11.setText(buttonName);
                    if(finalIcon == 0){
                        btn11.setTextSize(15);
                    }
                    btn11.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                    btn11.setBackgroundColor(getResources().getColor(finalColor));
                    break;

                case 12:
                    btn12 = (Button)findViewById(R.id.btn12);
                    btn12.setText(buttonName);
                    if(finalIcon == 0){
                        btn12.setTextSize(15);
                    }
                    btn12.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                    btn12.setBackgroundColor(getResources().getColor(finalColor));
                    break;

                case 13:
                    btn13 = (Button)findViewById(R.id.btn13);
                    btn13.setText(buttonName);
                    if(finalIcon == 0){
                        btn13.setTextSize(15);
                    }
                    btn13.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                    btn13.setBackgroundColor(getResources().getColor(finalColor));
                    break;

                case 14:
                    btn14 = (Button)findViewById(R.id.btn14);
                    btn14.setText(buttonName);
                    if(finalIcon == 0){
                        btn14.setTextSize(15);
                    }
                    btn14.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                    btn14.setBackgroundColor(getResources().getColor(finalColor));
                    break;

                case 15:
                    btn15 = (Button)findViewById(R.id.btn15);
                    btn15.setText(buttonName);
                    if(finalIcon == 0){
                        btn15.setTextSize(15);
                    }
                    btn15.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                    btn15.setBackgroundColor(getResources().getColor(finalColor));
                    break;

                case 16:
                    btn16 = (Button)findViewById(R.id.btn16);
                    btn16.setText(buttonName);
                    if(finalIcon == 0){
                        btn16.setTextSize(15);
                    }
                    btn16.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                    btn16.setBackgroundColor(getResources().getColor(finalColor));
                    break;

                case 17:
                    btn17 = (Button)findViewById(R.id.btn17);
                    btn17.setText(buttonName);
                    if(finalIcon == 0){
                        btn17.setTextSize(15);
                    }
                    btn17.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                    btn17.setBackgroundColor(getResources().getColor(finalColor));
                    break;

                case 18:
                    btn18 = (Button)findViewById(R.id.btn18);
                    btn18.setText(buttonName);
                    btn18.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
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
                    btn19.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                    btn19.setBackgroundColor(getResources().getColor(finalColor));

                    break;

                case 20:
                    btn20 = (Button)findViewById(R.id.btn20);
                    btn20.setText(buttonName);
                    if(finalIcon == 0){
                        btn20.setTextSize(15);
                    }
                    btn20.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                    btn20.setBackgroundColor(getResources().getColor(finalColor));
                    break;

                case 21:
                    btn21 = (Button)findViewById(R.id.btn21);
                    btn21.setText(buttonName);
                    if(finalIcon == 0){
                        btn21.setTextSize(15);
                    }
                    btn21.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                    btn21.setBackgroundColor(getResources().getColor(finalColor));
                    break;


                    default:
                        break;
            }//finish the switch case
        }

    }

    public void buttonPressed(View view) {
        switch (view.getId()){
            case R.id.btn1:///ESTO SE PUEDE BORRAR DESPUES
                try{
                String  codeControl = "";
                String linesTable = "";
                //consult the number of the table  learn to consult
                SQLiteDatabase db =conn.getReadableDatabase();
                String [] parameters = {controlIrName};
                String [] column = {utility.COLUMN_ID_CONTROLSIR};
                Cursor cursor = db.query(utility.TABLE_CONTROLSIR, column, utility.COLUMN_NAME_CONTROLSIR+"=?", parameters, null, null, null);
                cursor.moveToFirst();
                codeControl = (cursor.getString(0));
                    Toast.makeText(getApplicationContext(),codeControl, Toast.LENGTH_LONG).show();
                cursor.close();
                db.close();

                   //Consult how many lines get  DE PRUEBA 2
                    SQLiteDatabase db2 =conn.getReadableDatabase();
                    //String [] parameters2 = {codeControl};
                    //String [] column2 = {utility.COLUMN_LEARN_DEVICE_NUMBER};
                    Cursor cursor2 = db2.rawQuery("SELECT * FROM " + utility.TABLE_LEARN, null);
                    //Cursor cursor2 = db2.query(utility.TABLE_LEARN, column2, utility.COLUMN_LEARN_DEVICE_NUMBER+"=?", parameters2, null, null, null);
                    //With this count how many lines get
                    linesTable = Integer.toString(cursor2.getCount());
                    Toast.makeText(getApplicationContext(),linesTable, Toast.LENGTH_LONG).show();
                    cursor2.close();
                    db2.close();

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"no consulto", Toast.LENGTH_LONG).show();
                }


                break;
        }
    }


  /* @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }*/
}
