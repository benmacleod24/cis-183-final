package com.example.program_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.program_01.Controllers.Session;
import com.example.program_01.Database.BusinessDatabase;
import com.example.program_01.Database.ServiceDatabase;
import com.example.program_01.Database.UsersDatabase;
import com.example.program_01.Models.Business;
import com.example.program_01.Models.Service;

import java.util.ArrayList;

public class createService extends AppCompatActivity
{
    //GUI Stuff
    EditText et_j_cs_name;
    EditText et_j_cs_desc;
    CheckBox cb_j_cs_isEmergency;
    TextView tv_j_cs_fieldsError;
    Button btn_j_cs_createService;
    Button btn_j_cs_back;

    //Intent Stuff
    Intent bussinessHomeIntent;

    //Database Stuff
    BusinessDatabase businessDb;
    ServiceDatabase serviceDb;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_service);

        //GUI Stuff
        et_j_cs_name = findViewById(R.id.et_v_cs_name);
        et_j_cs_desc = findViewById(R.id.et_v_cs_desc);
        cb_j_cs_isEmergency = findViewById(R.id.cb_v_cs_isEmergency);
        tv_j_cs_fieldsError = findViewById(R.id.tv_v_cs_fieldsError);
        btn_j_cs_createService = findViewById(R.id.btn_v_cs_createService);
        btn_j_cs_back = findViewById(R.id.btn_v_cs_back);

        //Database Stuff
        businessDb = new BusinessDatabase(this);
        serviceDb = new ServiceDatabase(this);

        //Intent Stuff
        bussinessHomeIntent = new Intent(createService.this, businessHome.class);

        //TESTING
        ArrayList<Service> allServicesUnderMe = new ArrayList<Service>();
        allServicesUnderMe = serviceDb.getAllServicesUnderBusiness(Session.getBusiness().getEmail());
        for (int i = 0; i < allServicesUnderMe.size(); i++)
        {
            Log.d("SERVICES UNDER " + Session.getBusiness().getEmail() + ":", allServicesUnderMe.get(i).getServiceName());
        }

        //Functions
        createServiceButtonEvent();
        backButtonEvent();
    }

    public void createServiceButtonEvent()
    {
        btn_j_cs_createService.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("Button Pressed: ", "=====Create Service Button Press (From Create Service Intent)=====");


                String sName = et_j_cs_name.getText().toString();
                String sDesc = et_j_cs_desc.getText().toString();

                if (sName.equals("") || sDesc.equals(""))
                {
                    tv_j_cs_fieldsError.setVisibility(View.VISIBLE);
                }
                else
                {
                    tv_j_cs_fieldsError.setVisibility(View.INVISIBLE);

                    //Must make service through database first, then read services later because serviceId is auto incrementing
                    if (cb_j_cs_isEmergency.isChecked()) //Service is of type EMERGENCY
                    {
                        //Looks like this
                        serviceDb.createService(Session.getBusiness().getEmail(), Service.SERVICE_TYPE_EMERGENCY, sName, sDesc);
                    }
                    else //Service is of type BASIC
                    {
                        //Looks like this
                        serviceDb.createService(Session.getBusiness().getEmail(), Service.SERVICE_TYPE_BASIC, sName, sDesc);
                    }

                    startActivity(bussinessHomeIntent);
                }
            }
        });
    }

    public void backButtonEvent()
    {
        btn_j_cs_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("Button Pressed: ", "=====Back Button Press (From Create Service Intent)=====");
                startActivity(bussinessHomeIntent);
            }
        });
    }
}