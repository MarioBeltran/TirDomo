package com.example.mario.tirdomo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mario.tirdomo.entity.controlsir;

import com.example.mario.tirdomo.utility.ListAdapter;
import com.example.mario.tirdomo.utility.utility;

import java.util.ArrayList;

public class IrControlsActivity extends AppCompatActivity {

    public String controlIrName;///name of the control that i will call
    ArrayList<String> titles;
    ArrayList<Integer> imagens;

    public String codeImagens;

    ListAdapter adapter;

    //ListView listViewInfoControlsir;

    ArrayList <String> InformationList;
    ArrayList <controlsir> ControlsirList;

    ConnectionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ir_controls);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        conn = new ConnectionSQLiteHelper(getApplicationContext(),"bd_devices",null,1);
        //listViewInfoControlsir = (ListView)findViewById(R.id.listViewInfoControlsir);

        consultListControlsir();

        final ListView lista = (ListView) findViewById(R.id.listViewInfoControlsir);
        adapter = new ListAdapter(this, titles, imagens);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                controlIrName = titles.get(position);
               // Intent intent = new Intent(IrControlsActivity.this,TemplateControlir1Activity.class);
                Intent intent = new Intent(IrControlsActivity.this,TemplateControlir2Activity.class);
                intent.putExtra("controlIrName", controlIrName);
                intent.putExtra("editButton", "no");
                startActivity(intent);

                //Toast.makeText(getApplicationContext(), device_id, Toast.LENGTH_SHORT).show();
            }
        });


       // ArrayAdapter adaptader = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, InformationList);
        //listViewInfoControlsir.setAdapter(adaptader);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
         */

        //Actived the button to get back
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    private void consultListControlsir() {
        //Consult the listo of controls ir
        SQLiteDatabase db = conn.getReadableDatabase();

        try {
        titles = new ArrayList<String>();
        imagens = new ArrayList<Integer>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + utility.TABLE_CONTROLSIR, null);
        while (cursor.moveToNext()){
            //learnId = cursor.getString(0);
            titles.add(cursor.getString(1));
            codeImagens = cursor.getString(2);
            switch (codeImagens){
                case "1"://tv
                    imagens.add(R.drawable.ic_tv_black_24dp);
                    break;
                case "2"://Decoder
                    imagens.add(R.drawable.ic_satdeco_black_24dp);
                    break;
                case "3"://Ac
                    imagens.add(R.drawable.ic_ac_black_24dp);
                    break;
                case "4"://Box
                    imagens.add(R.drawable.ic_box_black_24dp);
                    break;
                case "5"://DVD
                    imagens.add(R.drawable.ic_dvd_black_24dp);
                    break;
                case "6"://Fan
                    imagens.add(R.drawable.ic_toys_black_24dp);
                    break;
                case "7"://Projector
                    imagens.add(R.drawable.ic_projector_black_24dp);
                    break;
                case "8"://Av
                    imagens.add(R.drawable.ic_speaker_black_24dp);
                    break;

                    default:
                        break;

            }

        }

        /*controlsir Controlsir = null;
        ControlsirList = new ArrayList<controlsir>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + utility.TABLE_CONTROLSIR, null);
        while (cursor.moveToNext()){
            Controlsir = new controlsir();
            Controlsir.setId_Controlsir(cursor.getInt(0));
            Controlsir.setName_Controlsir(cursor.getString(1));
            Controlsir.setTemplate_Controlsir(cursor.getString(2));
            ControlsirList.add(Controlsir);
        }*/
        cursor.close();
        }catch (Exception e){}
        //GetListControlsir();
    }

    private void GetListControlsir() {
        InformationList = new ArrayList<String>();

        for (int i = 0; i<ControlsirList.size(); i++){
            InformationList.add(ControlsirList.get(i).getId_Controlsir()+" - "+ControlsirList.get(i).getName_Controlsir()+" - : "+ControlsirList.get(i).getTemplate_Controlsir());
          // InformationList.add(ControlsirList.get(i).getId_Controlsir()+" - "+ControlsirList.get(i).getName_Controlsir()+" - : "+R.drawable.ic_tv_black_24dp);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ircontrols, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.item_add_ircontrols) {//Go to the window with devices information
            Intent intent = new Intent(IrControlsActivity.this,AddControlirActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
