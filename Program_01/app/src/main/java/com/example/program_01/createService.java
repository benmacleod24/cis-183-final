package com.example.program_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import android.widget.Toast;

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
    EditText et_j_cs_desc;
    TextView tv_j_cs_fieldsError;
    Button btn_j_cs_createService;
    Button btn_j_cs_back;
    Spinner sp_j_cs_serviceType;

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
        et_j_cs_desc = findViewById(R.id.et_v_cs_desc);
        tv_j_cs_fieldsError = findViewById(R.id.tv_v_cs_fieldsError);
        btn_j_cs_createService = findViewById(R.id.btn_v_cs_createService);
        btn_j_cs_back = findViewById(R.id.btn_v_cs_back);
        sp_j_cs_serviceType = findViewById(R.id.sp_v_cs_serviceType);

        //Drop Down Stuff
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.serviceTypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_j_cs_serviceType.setAdapter(adapter);

        //Database Stuff
        businessDb = new BusinessDatabase(this);
        serviceDb = new ServiceDatabase(this);

        //Intent Stuff
        bussinessHomeIntent = new Intent(createService.this, businessHome.class);


        //TESTING
        ArrayList<Service> allServicesUnderMe = new ArrayList<Service>();
        allServicesUnderMe = serviceDb.getAllServicesUnderBusiness(Session.getBusiness().getEmail());
        if (allServicesUnderMe != null)
        {
            for (int i = 0; i < allServicesUnderMe.size(); i++)
            {
                Log.d("SERVICES UNDER " + Session.getBusiness().getEmail() + ":", allServicesUnderMe.get(i).getServiceType());
            }
        }
        //TESTING


        //Functions
        spinnerItemSelectEvent();
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

                String sDesc = et_j_cs_desc.getText().toString();

                if(sDesc.equals(""))
                {
                    tv_j_cs_fieldsError.setVisibility(View.VISIBLE);
                }
                else
                {
                    tv_j_cs_fieldsError.setVisibility(View.INVISIBLE);

                    //Must make service through database first, then read services later because serviceId is auto incrementing
                    //No other way to do this really other than hardcode it
                    //(There technically is but it'll take too long and I don't want to do it)
                    if (sp_j_cs_serviceType.getSelectedItem().toString().equals("Oil Change"))
                    {
                        serviceDb.createService(Session.getBusiness().getEmail(), Service.SERVICE_TYPE_OIL_CHANGE, sDesc);
                    }
                    if (sp_j_cs_serviceType.getSelectedItem().toString().equals("Repairs"))
                    {
                        serviceDb.createService(Session.getBusiness().getEmail(), Service.SERVICE_TYPE_REPAIRS, sDesc);
                    }
                    if (sp_j_cs_serviceType.getSelectedItem().toString().equals("Tuning"))
                    {
                        serviceDb.createService(Session.getBusiness().getEmail(), Service.SERVICE_TYPE_TUNING, sDesc);
                    }
                    if (sp_j_cs_serviceType.getSelectedItem().toString().equals("Detailing"))
                    {
                        serviceDb.createService(Session.getBusiness().getEmail(), Service.SERVICE_TYPE_DETAILING, sDesc);
                    }
                    if (sp_j_cs_serviceType.getSelectedItem().toString().equals("Towing"))
                    {
                        serviceDb.createService(Session.getBusiness().getEmail(), Service.SERVICE_TYPE_TOWING, sDesc);
                    }
                    if (sp_j_cs_serviceType.getSelectedItem().toString().equals("Roadside Assistance"))
                    {
                        serviceDb.createService(Session.getBusiness().getEmail(), Service.SERVICE_TYPE_ROADSIDE_ASSISTANCE, sDesc);
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

    public void spinnerItemSelectEvent()
    {
        sp_j_cs_serviceType.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String text = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
    }
}