package com.example.mario.tirdomo;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AddControlirActivity extends AppCompatActivity {

    public String  codeControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_controlir);

        //Actived the button to get back
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getResources().getString(R.string.title_activity_addcontrolir));
        }
    }

    public void onclick_icon(View view) {
        switch (view.getId()){


            case R.id.add_controlTv:
                codeControl = "1";
                Intent intent = new Intent(AddControlirActivity.this, SelectDeviceActivity.class);
                intent.putExtra("codeControl", codeControl);
                startActivity(intent);
                break;

            case R.id.add_controlDecoder:
                codeControl = "2";
                intent = new Intent(AddControlirActivity.this, SelectDeviceActivity.class);
                intent.putExtra("codeControl", codeControl);
                startActivity(intent);
                break;

            case R.id.add_controlAc:
                codeControl = "3";
                intent = new Intent(AddControlirActivity.this, SelectDeviceActivity.class);
                intent.putExtra("codeControl", codeControl);
                startActivity(intent);
                break;

            case R.id.add_controlBox:
                codeControl = "4";
                intent = new Intent(AddControlirActivity.this, SelectDeviceActivity.class);
                intent.putExtra("codeControl", codeControl);
                startActivity(intent);
                break;

            case R.id.add_controlDvd:
                codeControl = "5";
                intent = new Intent(AddControlirActivity.this, SelectDeviceActivity.class);
                intent.putExtra("codeControl", codeControl);
                startActivity(intent);
                break;

            case R.id.add_controlFan:
                codeControl = "6";
                intent = new Intent(AddControlirActivity.this, SelectDeviceActivity.class);
                intent.putExtra("codeControl", codeControl);
                startActivity(intent);
                break;

            case R.id.add_controlProjector:
                codeControl = "7";
                intent = new Intent(AddControlirActivity.this, SelectDeviceActivity.class);
                intent.putExtra("codeControl", codeControl);
                startActivity(intent);
                break;

            case R.id.add_controlReceptorAV:
                codeControl = "8";
                intent = new Intent(AddControlirActivity.this, SelectDeviceActivity.class);
                intent.putExtra("codeControl", codeControl);
                startActivity(intent);
                break;
        }
    }
}
