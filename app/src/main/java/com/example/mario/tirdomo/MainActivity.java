package com.example.mario.tirdomo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    ArrayList<String> msg1;
    public String msg2;

    ConnectionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        conn = new ConnectionSQLiteHelper(this, "bd_devices", null, 1);


       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_infoDevice) {//Go to the window with devices information
            Intent intent = new Intent(MainActivity.this,InfoDevicesActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_controls) {
            //Go to the window ir Controls - Infrarred
        } else if (id == R.id.nav_addDevices) {
            //Go to the window to start configuration in add device 1
            Intent intent = new Intent(MainActivity.this,AddDevice1Activity.class);
            startActivity(intent);
        } else if (id == R.id.nav_settings) {
            //Go to the window configuration settings...
            Intent intent = new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_backup) {
            //Go to the window Import and export data...
            Intent intent = new Intent(MainActivity.this,ImporExportDataActivity.class);
            startActivity(intent);

        }else if (id == R.id.nav_help) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void button_select_control(View view) {
        //To select diferent controls ir, rf, switch and more in the main content
        switch (view.getId()){
            case R.id.button_irControls:
                Intent intent = new Intent(MainActivity.this,IrControlsActivity.class);
                startActivity(intent);
                break;

            case R.id.button_lights:
                //CODE NOT COMPLETED
                /*

                try {//Consult if the device exist
                    msg1 = new ArrayList<String>();
                    SQLiteDatabase db = conn.getReadableDatabase();
                    //String [] parameters = {"1" , "1"};
                    String [] parameters = {"1"};
                    String [] column = {utility.COLUMN_PRE_BUTTON_NAME};
                    Cursor cursor = db.rawQuery("SELECT * FROM " + utility.TABLE_PRECHARGED, null);
                   //Cursor cursor = db.query(utility.TABLE_PRECHARGED, column, utility.COLUMN_PRE_ID+"=? AND "+utility.COLUMN_PRE_LOCATION_BUTTON+"=?", parameters, null, null, null);

                    //With this count how many lines get
                    msg2= Integer.toString(cursor.getCount());
                    Toast.makeText(getApplicationContext(), msg2, Toast.LENGTH_SHORT).show();


                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"No guardo", Toast.LENGTH_LONG).show();
                }

                */



                break;

            case R.id.button_curtains:

                //CODE NOT COMPLETED

                 /*
                //Toast.makeText(getApplicationContext(),getResources().getDrawable(R.drawable.ic_dvd_black_24dp).toString(), Toast.LENGTH_LONG).show();
                String line = "INSERT INTO devices (id_Device, device_Name, ip_Lan, ip_Port) VALUES ('5','Prueba 5','0.0.0.94','8084')";
                try {
                    SQLiteDatabase db = conn.getWritableDatabase();
                    db.execSQL(line);
                    db.close();
                 }catch (Exception e){
            Toast.makeText(getApplicationContext(),"No guardo", Toast.LENGTH_LONG).show();
                    }

               ContentValues values = new ContentValues();
                values.put(utility.COLUMN_LEARN_ID, "20");
                values.put(utility.COLUMN_LEARN_LOCATION_BUTTON, "TV");
                values.put(utility.COLUMN_LEARN_BUTTON_NAME, "1");
                values.put(utility.COLUMN_LEARN_BUTTON_ICON, "POWER");
                values.put(utility.COLUMN_LEARN_IRCODE, "icono power");
                values.put(utility.COLUMN_LEARN_DEVICE_NUMBER, "20");
                values.put(utility.COLUMN_LEARN_COLORBACK, "Red1");
                Long result = db.insert(utility.TABLE_LEARN, null, values);
                db.close();

                if (result==-1){
                    Toast.makeText(getApplicationContext(),"NO GUARDO", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"GUARDO", Toast.LENGTH_LONG).show();

                }*/

                break;

            case R.id.button_switch:
                break;

            case R.id.button_security:
                break;
        }
    }
}
