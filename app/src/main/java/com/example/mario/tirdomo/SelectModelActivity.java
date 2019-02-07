package com.example.mario.tirdomo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mario.tirdomo.entity.precharged;
import com.example.mario.tirdomo.utility.utility;

import java.util.ArrayList;

public class SelectModelActivity extends AppCompatActivity {

    String pre_id = "";
    String template_Controlsir = "";//this variable is to charge the information of the icon to use in the Ircontrols

    Integer countStart;
    Integer countEnd;

    ListView listViewModels;
    ArrayList<String> InformationList;
    ArrayList <precharged> ModelList;

    public String codeControl;//this is, if the control is TV or DVD or ...
    public String   device_id;//this is the device to sent websocket information through ip and port
    String editButton = "";

    ConnectionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_model);

        listViewModels = findViewById(R.id.listViewModels);

        conn = new ConnectionSQLiteHelper(getApplicationContext(),"bd_devices",null,1);

        //Actived the button to get back
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getResources().getString(R.string.title_activity_selectmodel));
        }

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras!=null){
            codeControl = extras.getString("codeControl");
            device_id = extras.getString("device_id");
        }
        ///initialize the range to looking the precharged table of controls
        switch (codeControl){
            case "1": //TV
                countStart = 10;
                countEnd = 19;
                template_Controlsir = "1";
                break;
            case "2"://Decoder
                countStart = 20;
                countEnd = 29;
                template_Controlsir = "2";
                break;
            case "3"://AC
                countStart = 30;
                countEnd = 39;
                template_Controlsir = "3";
                break;
            case "4"://Box
                countStart = 40;
                countEnd = 49;
                template_Controlsir = "4";
                break;
            case "5"://DVD
                countStart = 50;
                countEnd = 59;
                template_Controlsir = "5";
                break;
            case "6"://Fan
                countStart = 60;
                countEnd = 69;
                template_Controlsir = "6";
                break;
            case "7"://Proyector
                countStart = 70;
                countEnd = 79;
                template_Controlsir = "7";
                break;
            case "8":
                countStart = 80;
                countEnd = 89;
                template_Controlsir = "8";
                break;

                default:
                    break;
        }


        consultListModels();

        ArrayAdapter adaptader = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, InformationList);
        listViewModels.setAdapter(adaptader);

        listViewModels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                codeControl = ModelList.get(position).getPre_id().toString();
                //Toast.makeText(getApplicationContext(), codeControl, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SelectModelActivity.this,EditControlNameActivity.class);
                intent.putExtra("device_id", device_id);
                intent.putExtra("codeControl", codeControl);
                intent.putExtra("template_Controlsir", template_Controlsir);
                intent.putExtra("editButton", "no");
                startActivity(intent);
            }
        });

        //Toast.makeText(getApplicationContext(), device_id+" , "+codeControl, Toast.LENGTH_SHORT).show();
    }


    ///consulto the list models
    private void consultListModels() {

        precharged Precharged = null;
        ModelList = new ArrayList<precharged>();

      for (int i = countStart; i<countEnd+1; i++) {
            try {
                SQLiteDatabase db = conn.getReadableDatabase();
                pre_id = Integer.toString(i);
                String[] parameters = {pre_id, "1"};
                String[] column = {utility.COLUMN_PRE_ID, utility.COLUMN_PRE_MODEL};
                Cursor cursor = db.query(utility.TABLE_PRECHARGED, column, utility.COLUMN_PRE_ID + "=? AND " + utility.COLUMN_PRE_LOCATION_BUTTON + "=?", parameters, null, null, null);
                cursor.moveToFirst();
                Precharged = new precharged();
                Precharged.setPre_id(cursor.getInt(0));
                Precharged.setPre_model(cursor.getString(1));
                ModelList.add(Precharged);
                cursor.close();
                db.close();
            } catch (Exception e) {}
                                                    }
        GetListmodels();

    }


    private void GetListmodels() {
        InformationList = new ArrayList<String>();

        for (int i = 0; i<ModelList.size(); i++){
            //InformationList.add(ModelList.get(i).getPre_id()+" - "+ModelList.get(i).getPre_model());
            InformationList.add(ModelList.get(i).getPre_model());
        }
    }

    public void learnIrCode(View view) {
        //the Button learn code was pressed
        Intent intent = new Intent(SelectModelActivity.this,EditControlNameActivity.class);
        intent.putExtra("device_id", device_id);
        intent.putExtra("codeControl", codeControl);
        intent.putExtra("template_Controlsir", template_Controlsir);
        intent.putExtra("editButton", "yes");
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}
