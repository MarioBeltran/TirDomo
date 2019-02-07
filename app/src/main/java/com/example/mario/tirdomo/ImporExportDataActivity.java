package com.example.mario.tirdomo;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mario.tirdomo.utility.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class ImporExportDataActivity extends AppCompatActivity {

    private static final int PERMISION_WRITE_EXTERNAL_STORAGE = 123;
    String Filename = "fullbackupTD.txt";
    String linea1 = "";
    String linea2 = "";
    String linea3 = "";
    String linea4 = "";
    String linea5 = "";
    String linea6 = "";
    String linea7 = "";
    String linedatabase = "";

    ConnectionSQLiteHelper conn;
    TextView information;///To put messages


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_impor_export_data);

        conn = new ConnectionSQLiteHelper(this, "bd_devices", null, 1);
        information = (TextView)findViewById(R.id.information);

        //Actived the button to get back
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    //Button to export all the data controls and devices
    public void btnExport(View view) {

        int permissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED){
            saveAllFiles();
            sendEmail();
        }else{
            ActivityCompat.requestPermissions(ImporExportDataActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISION_WRITE_EXTERNAL_STORAGE);
        }

    }

    private void sendEmail() {
        File filelocation = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), Filename);
        Uri path = Uri.fromFile(filelocation);
        String[] TO = {""}; //aquí pon tu correo
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        // Esto podrás modificarlo si quieres, el asunto y el cuerpo del mensaje
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Backup TDomo");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Full Bakcup TDomo");
        emailIntent.putExtra(Intent.EXTRA_STREAM,  path);

        try {
            startActivity(Intent.createChooser(emailIntent, "Enviar email..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getApplicationContext(),"No tienes clientes de email instalados.", Toast.LENGTH_SHORT).show();
        }
    }


    private void saveAllFiles() {////do a full backup
        information.setText(getResources().getString(R.string.msg_exporting_data));
        try{
            File file = new File(Environment.getExternalStorageDirectory(), Filename);
            FileOutputStream fos = new FileOutputStream(file);
            linea1 = "";
            linea2 = "";
            linea3 = "";
            linea4 = "";

            try {//Consult table devices
                SQLiteDatabase db = conn.getReadableDatabase();
                Cursor cursor = db.rawQuery("SELECT * FROM " + utility.TABLE_DEVICES, null);
                while (cursor.moveToNext()) {
                    //String msg1 = "";
                    linea1 = (cursor.getString(0));
                    linea2 = (cursor.getString(1));
                    linea3 = (cursor.getString(2));
                    linea4 = (cursor.getString(3));

                    linedatabase = "INSERT INTO devices (id_Device, device_Name, ip_Lan, ip_Port) VALUES ('"+linea1+"','"+linea2+"','"+linea3+"','"+linea4+"')"+"\n";
                    //Toast.makeText(getApplicationContext(),linedatabase, Toast.LENGTH_LONG).show();
                    fos.write(linedatabase.getBytes());

                }
                cursor.close();
                db.close();
            }catch (Exception e){}

            linea1 = "";
            linea2 = "";
            linea3 = "";
            linea4 = "";
            try {//Consult table CONTROL IR
                SQLiteDatabase db = conn.getReadableDatabase();
                Cursor cursor = db.rawQuery("SELECT * FROM " + utility.TABLE_CONTROLSIR, null);
                while (cursor.moveToNext()) {
                    //String msg1 = "";
                    linea1 = (cursor.getString(0));
                    linea2 = (cursor.getString(1));
                    linea3 = (cursor.getString(2));
                    linedatabase = "INSERT INTO controlsir (id_Controlsir, name_Controlir, template_Controlsir) VALUES ('"+linea1+"','"+linea2+"','"+linea3+"')"+"\n";
                    //Toast.makeText(getApplicationContext(),linedatabase, Toast.LENGTH_LONG).show();
                    fos.write(linedatabase.getBytes());
                }
                cursor.close();
                db.close();
            }catch (Exception e){}

            linea1 = "";
            linea2 = "";
            linea3 = "";
            linea4 = "";
            linea5 = "";
            linea6 = "";
            linea7 = "";
            try {//Consult table LEARN
                SQLiteDatabase db = conn.getReadableDatabase();
                Cursor cursor = db.rawQuery("SELECT * FROM " + utility.TABLE_LEARN, null);
                while (cursor.moveToNext()) {
                    //String msg1 = "";
                    linea1 = (cursor.getString(0));
                    linea2 = (cursor.getString(1));
                    linea3 = (cursor.getString(2));
                    linea4 = (cursor.getString(3));
                    linea5 = (cursor.getString(4));
                    linea6 = (cursor.getString(5));
                    linea7 = (cursor.getString(6));

                    linedatabase = "INSERT INTO learn (learn_id, learn_location_button, learn_button_name, learn_button_icon, learn_ircode, learn_device_number, learn_colorback ) VALUES " +
                            "('"+linea1+"','"+linea2+"','"+linea3+"','"+linea4+"','"+linea5+"','"+linea6+"','"+linea7+"')"+"\n";
                    //Toast.makeText(getApplicationContext(),linedatabase, Toast.LENGTH_LONG).show();
                    fos.write(linedatabase.getBytes());

                }
                cursor.close();
                db.close();
            }catch (Exception e){}

            fos.close();
            information.setText(getResources().getString(R.string.msg_succesful_export));
            //Toast.makeText(getApplicationContext(), "Dato guardado", Toast.LENGTH_LONG).show();
        }catch (java.io.IOException e){
            e.printStackTrace();
            information.setText(getResources().getString(R.string.msg_error_exporting));
            //Toast.makeText(getApplicationContext(), "No Dato guardado", Toast.LENGTH_LONG).show();
        }
    }

    ///this button import new data and erase the old one
    public void btnImport(View view) {
        //Alert dialog to confirm before import new data this delete the  before data
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage(getResources().getString(R.string.alert_msg_import_fullbackup));
        builder.setPositiveButton(getResources().getString(R.string.alertmsg2_update_device), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {//if ok delete old data and import new one

        information.setText("");
        //Before import, delete all the database
        try {
            SQLiteDatabase db = conn.getWritableDatabase();
            db.delete(utility.TABLE_DEVICES, null, null);
            db.delete(utility.TABLE_CONTROLSIR, null, null);
            db.delete(utility.TABLE_LEARN, null, null);
            //Toast.makeText(getApplicationContext(), "Is deleted Data base", Toast.LENGTH_LONG).show();
            db.close();
        } catch (Exception e){
            Toast.makeText(getApplicationContext(), "No elimino tablas", Toast.LENGTH_LONG).show();
        }


       try{
            File file = new File(Environment.getExternalStorageDirectory(), Filename);
            FileInputStream fin = new FileInputStream(file);
            InputStreamReader inputStream = new InputStreamReader(fin);
            BufferedReader bufferedReader = new BufferedReader(inputStream);
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null){
                //stringBuilder.append(line + "\n");
                //Guardar dato
                try{
                    SQLiteDatabase db = conn.getWritableDatabase();
                    db.execSQL(line);
                    db.close();
                    //Toast.makeText(getApplicationContext(), line, Toast.LENGTH_LONG).show();
                }
                catch (Exception e){Toast.makeText(getApplicationContext(), "no inserto datos a la tabla", Toast.LENGTH_LONG).show();}

            }

            fin.close();
            inputStream.close();
            information.setText(getResources().getString(R.string.msg_succesful_import));
            //informacion.setText( stringBuilder.toString());
            //Toast.makeText(getApplicationContext(), "Dato retornado:  " +  stringBuilder.toString(), Toast.LENGTH_LONG).show();

        }catch (java.io.IOException e){
            e.printStackTrace();
        }

            }

        }).setNegativeButton(getResources().getString(R.string.alertmsg3_update_device), new DialogInterface.OnClickListener() { //if cancel no import
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();

    }//end btnImport

}
