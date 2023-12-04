package com.example.program_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.program_01.Controllers.Session;
import com.example.program_01.Database.BusinessDatabase;
import com.example.program_01.Database.ServiceDatabase;
import com.example.program_01.Models.Service;

import java.io.Serializable;
import java.util.ArrayList;

public class businessHome extends AppCompatActivity
{
    //GUI
    Button btn_j_createService;
    ListView lv_j_bh_myServices;

    //Intent Stuff
    Intent createServiceIntent;
    Intent editServiceIntent;

    //Database Stuff if we even need it here
    BusinessDatabase businessDb;
    ServiceDatabase serviceDb;

    //Adapter Stuff
    MyServicesAdapter adapter;

    //MyServices Array
    ArrayList<Service> listOfMyServices;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_home);

        //GUI
        btn_j_createService = findViewById(R.id.btn_v_createService);
        lv_j_bh_myServices = findViewById(R.id.lv_v_bh_myServices);

        //Database
        serviceDb = new ServiceDatabase(this);
        businessDb = new BusinessDatabase(this);

        //MyServices Array
        listOfMyServices = new ArrayList<Service>();
        listOfMyServices = serviceDb.getAllServicesUnderBusiness(Session.getBusiness().getEmail());

        //Intents
        createServiceIntent = new Intent(businessHome.this, createService.class);
        editServiceIntent = new Intent(businessHome.this, editService.class);


        //FUNCTIONS
        fillListView();
        createServiceButtonEvent();
        listViewOnClickListener();
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

    public void listViewOnClickListener()
    {
        lv_j_bh_myServices.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                editServiceIntent.putExtra("myService", listOfMyServices.get(i));
                startActivity(editServiceIntent);
            }
        });
    }

    public void fillListView()
    {
        adapter = new MyServicesAdapter(this, listOfMyServices);
        lv_j_bh_myServices.setAdapter(adapter);
    }
}