package com.example.mario.tirdomo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mario.tirdomo.entity.devices;
import com.example.mario.tirdomo.utility.ListAdapter;
import com.example.mario.tirdomo.utility.utility;

import java.util.ArrayList;

public class EditButton2Activity extends AppCompatActivity {

    ListSelectIcons listSelectIcons;

    ArrayList<String> InformationList;
    ArrayList <devices> DevicesList;

    ArrayList<String> titles;
    ArrayList<Integer> imagens;
    ListAdapter adapterIcon;

    public String controlIrName;
    public String learnId;
    public String buttonPressed;

    public String buttonName;
    public String buttonDevice;
    public String buttonColor;
    public String buttonIcon;
    public String deviceNumber;

    public String saveColor;
    public String saveIcon;
    public Integer finalColor;
    public Integer finalIcon;

    EditText edit_btnName;
    Spinner spinner_deviceControl;
    Spinner spinner_btnColor;
    Spinner spinner_btnIcon;
    Button btnTemplate;



    ConnectionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_button2);

        conn = new ConnectionSQLiteHelper(getApplicationContext(),"bd_devices",null,1);

        //Actived the button to get back
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getResources().getString(R.string.title_editbutton));
        }

        edit_btnName = (EditText)findViewById(R.id.edit_btnName);
        spinner_deviceControl = (Spinner) findViewById(R.id.spinner_deviceControl);
        spinner_btnColor = (Spinner) findViewById(R.id.spinner_btnColor);
        spinner_btnIcon = (Spinner) findViewById(R.id.spinner_btnIcon);
        btnTemplate = (Button) findViewById(R.id.btnTemplate);

        listSelectIcons = new ListSelectIcons();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras!=null){
            // learnId = extras.getString("learnId");
            controlIrName = extras.getString("controlIrName");//The control name
            learnId = extras.getString("learnId");//number of control
            buttonPressed = extras.getString("buttonPressed");//number of the button location
        }

        //Search all the information about this button, name, device , clor, icon.
        try {
            SQLiteDatabase db = conn.getReadableDatabase();
            String[] parameters = {learnId, buttonPressed};
            String[] column = {utility.COLUMN_LEARN_BUTTON_NAME, utility.COLUMN_LEARN_DEVICE_NUMBER, utility.COLUMN_LEARN_COLORBACK, utility.COLUMN_LEARN_BUTTON_ICON};
            Cursor cursor = db.query(utility.TABLE_LEARN, column, utility.COLUMN_LEARN_ID + "=? AND " + utility.COLUMN_LEARN_LOCATION_BUTTON + "=?", parameters, null, null, null);
            cursor.moveToFirst();
            edit_btnName.setText(cursor.getString(0));
            deviceNumber = (cursor.getString(1));
            buttonColor = (cursor.getString(2));
            buttonIcon = (cursor.getString(3));
            //idDevice = (cursor.getString(1));
            cursor.close();
            db.close();
        } catch (Exception e) {
        }


        //Information for spinner about color
        final ArrayList<String> selectionListColor = new ArrayList<String>();
        selectionListColor.add(getResources().getString(R.string.msg_color_nochange));
        selectionListColor.add(getResources().getString(R.string.color_red1));
        selectionListColor.add(getResources().getString(R.string.color_red2));
        selectionListColor.add(getResources().getString(R.string.color_blue1));
        selectionListColor.add(getResources().getString(R.string.color_blue2));
        selectionListColor.add(getResources().getString(R.string.color_gray1));
        selectionListColor.add(getResources().getString(R.string.color_gray2));
        selectionListColor.add(getResources().getString(R.string.color_yellow1));
        selectionListColor.add(getResources().getString(R.string.color_green1));
        selectionListColor.add(getResources().getString(R.string.color_white));

        ArrayAdapter<String> adapterColor =new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,selectionListColor);
        spinner_btnColor.setAdapter(adapterColor);

        spinner_btnColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(),"Posicion Color "+parent.getItemIdAtPosition(position), Toast.LENGTH_LONG).show();
                switch (position){
                    case 0:
                        saveColor = buttonColor;
                        //saveIcon = buttonIcon;
                        btnTemplate.setText(edit_btnName.getText().toString());
                        finalColor = Integer.parseInt(listSelectIcons.listColors(saveColor));
                        btnTemplate.setBackgroundColor(getResources().getColor(finalColor));
                        //finalIcon = Integer.parseInt(listSelectIcons.listIcons(saveIcon));
                        //btnTemplate.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                        break;
                    case 1:
                        saveColor = "Red1";
                        btnTemplate.setText(edit_btnName.getText().toString());
                        finalColor = Integer.parseInt(listSelectIcons.listColors(saveColor));
                        btnTemplate.setBackgroundColor(getResources().getColor(finalColor));
                        break;
                    case 2:
                        saveColor = "Red2";
                        btnTemplate.setText(edit_btnName.getText().toString());
                        finalColor = Integer.parseInt(listSelectIcons.listColors(saveColor));
                        btnTemplate.setBackgroundColor(getResources().getColor(finalColor));
                        break;
                    case 3:
                        saveColor = "Blue1";
                        btnTemplate.setText(edit_btnName.getText().toString());
                        finalColor = Integer.parseInt(listSelectIcons.listColors(saveColor));
                        btnTemplate.setBackgroundColor(getResources().getColor(finalColor));
                        break;
                    case 4:
                        saveColor = "Blue2";
                        btnTemplate.setText(edit_btnName.getText().toString());
                        finalColor = Integer.parseInt(listSelectIcons.listColors(saveColor));
                        btnTemplate.setBackgroundColor(getResources().getColor(finalColor));
                        break;
                    case 5:
                        saveColor = "Gray1";
                        btnTemplate.setText(edit_btnName.getText().toString());
                        finalColor = Integer.parseInt(listSelectIcons.listColors(saveColor));
                        btnTemplate.setBackgroundColor(getResources().getColor(finalColor));
                        break;
                    case 6:
                        saveColor = "Gray2";
                        btnTemplate.setText(edit_btnName.getText().toString());
                        finalColor = Integer.parseInt(listSelectIcons.listColors(saveColor));
                        btnTemplate.setBackgroundColor(getResources().getColor(finalColor));
                        break;
                    case 7:
                        saveColor = "Yellow1";
                        btnTemplate.setText(edit_btnName.getText().toString());
                        finalColor = Integer.parseInt(listSelectIcons.listColors(saveColor));
                        btnTemplate.setBackgroundColor(getResources().getColor(finalColor));
                        break;
                    case 8:
                        saveColor = "Green1";
                        btnTemplate.setText(edit_btnName.getText().toString());
                        finalColor = Integer.parseInt(listSelectIcons.listColors(saveColor));
                        btnTemplate.setBackgroundColor(getResources().getColor(finalColor));
                        break;
                    case 9:
                        saveColor = "White";
                        btnTemplate.setText(edit_btnName.getText().toString());
                        finalColor = Integer.parseInt(listSelectIcons.listColors(saveColor));
                        btnTemplate.setBackgroundColor(getResources().getColor(finalColor));
                        break;
                        default:
                            break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Information for spinner about icon
        titles = new ArrayList<String>();
        titles.add(getResources().getString(R.string.msg_color_nochange));
        titles.add("Power");
        titles.add("Cursor Up");
        titles.add("Cursor Down");
        titles.add("Cursor Left");
        titles.add("Cursor Right");
        titles.add("Add");
        titles.add("Remove");
        titles.add("Back");
        titles.add("Input");
        titles.add("Home");
        titles.add("Mute");
        titles.add("Favorite");
        titles.add("More");
        titles.add("BackSpace");
        titles.add("Play");
        titles.add("Pause");
        titles.add("Stop");
        titles.add("Eject");
        titles.add("Previous");
        titles.add("Next");
        titles.add("Forward");
        titles.add("Rewind");
        titles.add("No Icon");
        imagens = new ArrayList<Integer>();
        finalIcon = Integer.parseInt(listSelectIcons.listIcons(buttonIcon));
        imagens.add(finalIcon);
        imagens.add(R.drawable.ic_power_settings_new_black_24dp);
        imagens.add(R.drawable.ic_keyboard_arrow_up_black_24dp);
        imagens.add(R.drawable.ic_keyboard_arrow_down_black_24dp);
        imagens.add(R.drawable.ic_keyboard_arrow_left_black_24dp);
        imagens.add(R.drawable.ic_keyboard_arrow_right_black_24dp);
        imagens.add(R.drawable.ic_add_black_24dp);
        imagens.add(R.drawable.ic_remove_black_24dp);
        imagens.add(R.drawable.ic_reply_black_24dp);
        imagens.add(R.drawable.ic_input_black_24dp);
        imagens.add(R.drawable.ic_home_black_24dp);
        imagens.add(R.drawable.ic_volume_off_black_24dp);
        imagens.add(R.drawable.ic_stars_black_24dp);
        imagens.add(R.drawable.ic_more_horiz_black_24dp);
        imagens.add(R.drawable.ic_backspace_black_24dp);
        imagens.add(R.drawable.ic_play_arrow_black_24dp);
        imagens.add(R.drawable.ic_pause_black_24dp);
        imagens.add(R.drawable.ic_stop_black_24dp);
        imagens.add(R.drawable.ic_eject_black_24dp);
        imagens.add(R.drawable.ic_skip_previous_black_24dp);
        imagens.add(R.drawable.ic_skip_next_black_24dp);
        imagens.add(R.drawable.ic_fast_forward_black_24dp);
        imagens.add(R.drawable.ic_fast_rewind_black_24dp);
        imagens.add(0);
        adapterIcon = new ListAdapter(this, titles, imagens);
        spinner_btnIcon.setAdapter(adapterIcon);

       spinner_btnIcon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               //Toast.makeText(getApplicationContext(), titles.get(position), Toast.LENGTH_SHORT).show();
               switch (titles.get(position)){
                   case "Power":
                       saveIcon = "Power1";
                       btnTemplate.setText(edit_btnName.getText().toString());
                       finalIcon = Integer.parseInt(listSelectIcons.listIcons(saveIcon));
                       btnTemplate.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                       break;
                   case "Cursor Up":
                       saveIcon = "CurUp1";
                       btnTemplate.setText(edit_btnName.getText().toString());
                       finalIcon = Integer.parseInt(listSelectIcons.listIcons(saveIcon));
                       btnTemplate.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                       break;
                   case "Cursor Down":
                       saveIcon = "CurDo1";
                       btnTemplate.setText(edit_btnName.getText().toString());
                       finalIcon = Integer.parseInt(listSelectIcons.listIcons(saveIcon));
                       btnTemplate.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                       break;
                   case "Cursor Left":
                       saveIcon = "CurLe1";
                       btnTemplate.setText(edit_btnName.getText().toString());
                       finalIcon = Integer.parseInt(listSelectIcons.listIcons(saveIcon));
                       btnTemplate.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                       break;
                   case "Cursor Right":
                       saveIcon = "CurRi1";
                       btnTemplate.setText(edit_btnName.getText().toString());
                       finalIcon = Integer.parseInt(listSelectIcons.listIcons(saveIcon));
                       btnTemplate.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                       break;
                   case "Add":
                       saveIcon = "Mas1";
                       btnTemplate.setText(edit_btnName.getText().toString());
                       finalIcon = Integer.parseInt(listSelectIcons.listIcons(saveIcon));
                       btnTemplate.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                       break;
                   case "Remove":
                       saveIcon = "Menos1";
                       btnTemplate.setText(edit_btnName.getText().toString());
                       finalIcon = Integer.parseInt(listSelectIcons.listIcons(saveIcon));
                       btnTemplate.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                       break;
                   case "Back":
                       saveIcon = "Back1";
                       btnTemplate.setText(edit_btnName.getText().toString());
                       finalIcon = Integer.parseInt(listSelectIcons.listIcons(saveIcon));
                       btnTemplate.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                       break;
                   case "Input":
                       saveIcon = "Input1";
                       btnTemplate.setText(edit_btnName.getText().toString());
                       finalIcon = Integer.parseInt(listSelectIcons.listIcons(saveIcon));
                       btnTemplate.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                       break;
                   case "Home":
                       saveIcon = "Home1";
                       btnTemplate.setText(edit_btnName.getText().toString());
                       finalIcon = Integer.parseInt(listSelectIcons.listIcons(saveIcon));
                       btnTemplate.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                       break;
                   case "Mute":
                       saveIcon = "Mute1";
                       btnTemplate.setText(edit_btnName.getText().toString());
                       finalIcon = Integer.parseInt(listSelectIcons.listIcons(saveIcon));
                       btnTemplate.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                       break;
                   case "Favorite":
                       saveIcon = "Star1";
                       btnTemplate.setText(edit_btnName.getText().toString());
                       finalIcon = Integer.parseInt(listSelectIcons.listIcons(saveIcon));
                       btnTemplate.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                       break;
                   case "More":
                       saveIcon = "Extra1";
                       btnTemplate.setText(edit_btnName.getText().toString());
                       finalIcon = Integer.parseInt(listSelectIcons.listIcons(saveIcon));
                       btnTemplate.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                       break;
                   case "BackSpace":
                       saveIcon = "BackSpa1";
                       btnTemplate.setText(edit_btnName.getText().toString());
                       finalIcon = Integer.parseInt(listSelectIcons.listIcons(saveIcon));
                       btnTemplate.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                       break;
                   case "Play":
                       saveIcon = "Play1";
                       btnTemplate.setText(edit_btnName.getText().toString());
                       finalIcon = Integer.parseInt(listSelectIcons.listIcons(saveIcon));
                       btnTemplate.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                       break;
                   case "Pause":
                       saveIcon = "Pause1";
                       btnTemplate.setText(edit_btnName.getText().toString());
                       finalIcon = Integer.parseInt(listSelectIcons.listIcons(saveIcon));
                       btnTemplate.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                       break;
                   case "Stop":
                       saveIcon = "Stop1";
                       btnTemplate.setText(edit_btnName.getText().toString());
                       finalIcon = Integer.parseInt(listSelectIcons.listIcons(saveIcon));
                       btnTemplate.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                       break;
                   case "Eject":
                       saveIcon = "Eject1";
                       btnTemplate.setText(edit_btnName.getText().toString());
                       finalIcon = Integer.parseInt(listSelectIcons.listIcons(saveIcon));
                       btnTemplate.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                       break;
                   case "Previous":
                       saveIcon = "SPre1";
                       btnTemplate.setText(edit_btnName.getText().toString());
                       finalIcon = Integer.parseInt(listSelectIcons.listIcons(saveIcon));
                       btnTemplate.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                       break;
                   case "Next":
                       saveIcon = "SNex1";
                       btnTemplate.setText(edit_btnName.getText().toString());
                       finalIcon = Integer.parseInt(listSelectIcons.listIcons(saveIcon));
                       btnTemplate.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                       break;
                   case "Forward":
                       saveIcon = "FFor1";
                       btnTemplate.setText(edit_btnName.getText().toString());
                       finalIcon = Integer.parseInt(listSelectIcons.listIcons(saveIcon));
                       btnTemplate.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                       break;
                   case "Rewind":
                       saveIcon = "FRew1";
                       btnTemplate.setText(edit_btnName.getText().toString());
                       finalIcon = Integer.parseInt(listSelectIcons.listIcons(saveIcon));
                       btnTemplate.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                       break;
                   case "No Icon":
                       saveIcon = "";
                       btnTemplate.setText(edit_btnName.getText().toString());
                       finalIcon = 0;
                       btnTemplate.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                       break;
                       default:
                           saveIcon = buttonIcon;
                           finalIcon = Integer.parseInt(listSelectIcons.listIcons(saveIcon));
                           btnTemplate.setCompoundDrawablesWithIntrinsicBounds(0, finalIcon, 0, 0);
                           break;
               }

           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });


        ////Information about device control number
        consultListDevices();

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, InformationList);
        spinner_deviceControl.setAdapter(adaptador);

        spinner_deviceControl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent , View view, int position, long id) {
                buttonDevice = DevicesList.get(position).getId_Device().toString();
                //Toast.makeText(getApplicationContext(),"Posicion "+buttonDevice, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    private void consultListDevices() {
        SQLiteDatabase db = conn.getReadableDatabase();

        devices Devices = null;
        DevicesList = new ArrayList<devices>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + utility.TABLE_DEVICES, null);
        while (cursor.moveToNext()){
            Devices = new devices();
            Devices.setId_Device(cursor.getInt(0));
            Devices.setDevice_Name(cursor.getString(1));
            Devices.setIp_Lan(cursor.getString(2));
            Devices.setIp_port(cursor.getString(3));
            DevicesList.add(Devices);
        }
        cursor.close();
        GetListdevices();
    }
    private void GetListdevices() {
        InformationList = new ArrayList<String>();

        //InformationList.add(getResources().getString(R.string.editcontroltemplate_msg1));
        for (int i = 0; i<DevicesList.size(); i++){
            //InformationList.add(DevicesList.get(i).getId_Device()+" - "+DevicesList.get(i).getDevice_Name()+" - Port: "+DevicesList.get(i).getIp_port());
            InformationList.add(DevicesList.get(i).getId_Device()+" - "+DevicesList.get(i).getDevice_Name());
        }

    }

    //btn update parametere graphics in the button
    public void btn_update_button(View view) {
        try{
            SQLiteDatabase db = conn.getWritableDatabase();
            String [] parametros = {learnId, buttonPressed};
            ContentValues values = new ContentValues();
            values.put(utility.COLUMN_LEARN_BUTTON_NAME, edit_btnName.getText().toString());
            values.put(utility.COLUMN_LEARN_BUTTON_ICON, saveIcon);
            values.put(utility.COLUMN_LEARN_COLORBACK, saveColor);
            db.update(utility.TABLE_LEARN, values, utility.COLUMN_LEARN_ID+"=? AND "+utility.COLUMN_LEARN_LOCATION_BUTTON+"=?", parametros);
            db.close();

            Toast.makeText(getApplicationContext(),getResources().getString(R.string.msg_saved),Toast.LENGTH_LONG).show();

            Intent intent = new Intent(EditButton2Activity.this,TemplateControlir2Activity.class);
            //Recharge the window but with editbutton
            intent.putExtra("editButton", "yes2");
            intent.putExtra("controlIrName", controlIrName);
            intent.putExtra("learnId", learnId);// Send to the next activity the number of control
            startActivity(intent);
        }catch (Exception e){
        }
    }

    //btn update device of only this button
    public void btn_update_device(View view) {
        try{
            SQLiteDatabase db = conn.getWritableDatabase();
            String [] parametros = {learnId, buttonPressed};
            ContentValues values = new ContentValues();
            values.put(utility.COLUMN_LEARN_DEVICE_NUMBER, buttonDevice);
            db.update(utility.TABLE_LEARN, values, utility.COLUMN_LEARN_ID+"=? AND "+utility.COLUMN_LEARN_LOCATION_BUTTON+"=?", parametros);
            db.close();

            Toast.makeText(getApplicationContext(),getResources().getString(R.string.msg_saved),Toast.LENGTH_LONG).show();

            Intent intent = new Intent(EditButton2Activity.this,TemplateControlir2Activity.class);
            //Recharge the window but with editbutton
            intent.putExtra("editButton", "yes2");
            intent.putExtra("controlIrName", controlIrName);
            intent.putExtra("learnId", learnId);// Send to the next activity the number of control
            startActivity(intent);
        }catch (Exception e){
        }
    }
}
