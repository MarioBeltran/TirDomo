package com.example.mario.tirdomo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mario.tirdomo.utility.utility;

public class EditButtonFavorite1Activity extends AppCompatActivity {

    Button btnSaveChannel;
    EditText editChannelNumber, editChannelName;

    public String buttonPressed;
    public String learnId;
    public String controlIrName;
    public String editButton;//This variable permit edit or not the buttons

    ConnectionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_button_favorite1);

        btnSaveChannel = (Button) findViewById(R.id.btnSaveChannel);
        editChannelNumber = (EditText) findViewById(R.id.editChannelNumber);
        editChannelName = (EditText) findViewById(R.id.editChannelName);

        conn = new ConnectionSQLiteHelper(getApplicationContext(), "bd_devices", null, 1);

        //Actived the button to get back
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
           // actionBar.setTitle(getResources().getString(R.string.title_activity_editbutton1));
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
            String[] column = {utility.COLUMN_LEARN_BUTTON_NAME, utility.COLUMN_LEARN_IRCODE};
            Cursor cursor = db.query(utility.TABLE_LEARN, column, utility.COLUMN_LEARN_ID + "=? AND " + utility.COLUMN_LEARN_LOCATION_BUTTON + "=?", parameters, null, null, null);
            cursor.moveToFirst();
            editChannelName.setText(cursor.getString(0));
            editChannelNumber.setText(cursor.getString(1));
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

    public void buttonSaveChannel(View view) {

        try{
            SQLiteDatabase db = conn.getWritableDatabase();
            String [] parametros = {learnId, buttonPressed};
            ContentValues values = new ContentValues();
            values.put(utility.COLUMN_LEARN_BUTTON_NAME, editChannelName.getText().toString());
            values.put(utility.COLUMN_LEARN_IRCODE, editChannelNumber.getText().toString());
            db.update(utility.TABLE_LEARN, values, utility.COLUMN_LEARN_ID+"=? AND "+utility.COLUMN_LEARN_LOCATION_BUTTON+"=?", parametros);
            db.close();
            Toast.makeText(getApplicationContext(),getResources().getString(R.string.msg_saved) ,Toast.LENGTH_LONG).show();

            //After finish go to the template2 edit
            Intent intent = new Intent(EditButtonFavorite1Activity.this,TemplateControlir2Activity.class);
            //Get the name of the new control to send the next window
            intent.putExtra("controlIrName", controlIrName);
            intent.putExtra("editButton", "yes");// Send to the next activity the number of contro
            startActivity(intent);

        }catch (Exception e){
        }

    }

    public void buttonDeleteChannel(View view) {
        try{
            SQLiteDatabase db = conn.getWritableDatabase();
            String [] parametros = {learnId, buttonPressed};
            ContentValues values = new ContentValues();
            values.put(utility.COLUMN_LEARN_BUTTON_NAME, "");
            values.put(utility.COLUMN_LEARN_IRCODE, "");
            db.update(utility.TABLE_LEARN, values, utility.COLUMN_LEARN_ID+"=? AND "+utility.COLUMN_LEARN_LOCATION_BUTTON+"=?", parametros);
            db.close();

            //After finish go to the template2 edit
            Intent intent = new Intent(EditButtonFavorite1Activity.this,TemplateControlir2Activity.class);
            //Get the name of the new control to send the next window
            intent.putExtra("controlIrName", controlIrName);
            intent.putExtra("editButton", "yes");// Send to the next activity the number of contro
            startActivity(intent);

        }catch (Exception e){
        }

    }


}
