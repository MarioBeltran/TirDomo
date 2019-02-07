package com.example.mario.tirdomo;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class SettingsActivity extends AppCompatActivity {

    CheckBox checkVibration;
    public String keySettings;
    public String valueSettings;
    String value2 = "yes";///Only modified this variable here because is only to compare


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Actived the button to get back
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getResources().getString(R.string.title_activity_Settings_activity));
        }

        checkVibration = (CheckBox)findViewById(R.id.checkVibration);



        readFileGeneral();

    }

    private void readFileGeneral() {
        SharedPreferences sharedPrefer = getSharedPreferences("General", Context.MODE_PRIVATE);
        String value1 = sharedPrefer.getString("vibration","no hay dato");
        if(value1.equals(value2)){
            checkVibration.setChecked(true);
        }else{
            checkVibration.setChecked(false);
        }
    }

    public void onCheckBox(View view) {
          if(checkVibration.isChecked() == true){
              SharedPreferences sharedPrefer = getSharedPreferences("General", Context.MODE_PRIVATE);
              SharedPreferences.Editor editor = sharedPrefer.edit();
              editor.putString("vibration", "yes");
              editor.commit();
              //Toast.makeText(getApplicationContext(),"activado",Toast.LENGTH_LONG).show();
          }

          if (checkVibration.isChecked() == false){
              SharedPreferences sharedPrefer = getSharedPreferences("General", Context.MODE_PRIVATE);
              SharedPreferences.Editor editor = sharedPrefer.edit();
              editor.putString("vibration", "no");
              editor.commit();
              //Toast.makeText(getApplicationContext(),"desactivado",Toast.LENGTH_LONG).show();
          }
    }

    private void saveSettings() {

    }
}
