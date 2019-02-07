package com.example.mario.tirdomo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AddDevice1Activity extends AppCompatActivity {

    EditText editText_deviceName;
    Spinner numberSelection;
    public String   deviceName;
    public String   numberDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device1);


            numberSelection = (Spinner)findViewById(R.id.numberSelection);
            editText_deviceName = (EditText)findViewById(R.id.editText_deviceName);
            final ArrayList<String> numberSelectionList = new ArrayList<String>();

            numberSelectionList.add(getResources().getString(R.string.msg3_add_device1));
            for (int i=1; i<11; i++){
                numberSelectionList.add(""+i);
            }

            ArrayAdapter<CharSequence> adapter =new ArrayAdapter(this, android.R.layout.simple_spinner_item,numberSelectionList);
            numberSelection.setAdapter(adapter);
            //numberSelection.setSelection(4);

            numberSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent , View view, int position, long id) {
                    //Toast.makeText(getApplicationContext(),"Posicion "+parent.getItemIdAtPosition(position), Toast.LENGTH_LONG).show();
                    //numberDevice = ""+parent.getItemIdAtPosition(position);
                    switch (position){
                        case 0:
                            numberDevice="0";
                            break;
                        case 1:
                            numberDevice="1";
                            break;
                        case 2:
                            numberDevice="2";
                            break;
                        case 3:
                            numberDevice="3";
                            break;
                        case 4:
                            numberDevice="4";
                            break;
                        case 5:
                            numberDevice="5";
                            break;
                        case 6:
                            numberDevice="6";
                            break;
                        case 7:
                            numberDevice="7";
                            break;
                        case 8:
                            numberDevice="8";
                            break;
                        case 9:
                            numberDevice="9";
                            break;
                        case 10:
                            numberDevice="10";
                            break;
                            default:
                                break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });


    }

    public void button_next(View view) {
        //Button to go the next part of configuration the  new device
        deviceName = editText_deviceName.getText().toString();
           if(deviceName.equals("")||numberDevice=="0") {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.msg4_add_device1), Toast.LENGTH_LONG).show();
           }else {

               //deviceName = editText_deviceName.getText().toString();
               Intent intent = new Intent(AddDevice1Activity.this, AddDevice2Activity.class);
               intent.putExtra("numberDevice", numberDevice);
               intent.putExtra("deviceName", deviceName);
               startActivity(intent);
           }

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}
