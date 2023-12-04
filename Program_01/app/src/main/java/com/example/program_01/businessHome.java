package com.example.program_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.program_01.Database.BusinessDatabase;
import com.example.program_01.Database.ServiceDatabase;

public class businessHome extends AppCompatActivity
{
    //GUI
    Button btn_j_createService;

    //Intent Stuff
    Intent createServiceIntent;
    Intent editServiceIntent;

    //Database Stuff if we even need it here
    BusinessDatabase businessDb;
    ServiceDatabase serviceDb;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_home);

        //GUI
        btn_j_createService = findViewById(R.id.btn_v_createService);

        //Database
        serviceDb = new ServiceDatabase(this);
        businessDb = new BusinessDatabase(this);

        //Intents
        createServiceIntent = new Intent(businessHome.this, createService.class);
        editServiceIntent = new Intent(businessHome.this, editService.class);


        //FUNCTIONS
        createServiceButtonEvent();
    }

    public void createServiceButtonEvent()
    {
        btn_j_createService.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("Button Pressed:", "=====Create Service Button Pressed=====");
                startActivity(createServiceIntent);
            }
        });
    }

}