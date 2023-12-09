package com.example.program_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.program_01.Models.Service;

public class ServicesPage extends AppCompatActivity
{
    ImageView btn_j_sp_back;
    Button btn_j_sp_oilchange;
    Button btn_j_sp_repair;
    Button btn_j_sp_tuning;
    Button btn_j_sp_detailing;
    Intent serviceListIntent;
    Intent userHomeIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_page);

        btn_j_sp_back = findViewById(R.id.btn_v_sp_back);
        btn_j_sp_oilchange = findViewById(R.id.btn_v_sp_oilchange);
        btn_j_sp_repair = findViewById(R.id.btn_v_sp_repair);
        btn_j_sp_tuning = findViewById(R.id.btn_v_sp_tuning);
        btn_j_sp_detailing = findViewById(R.id.btn_v_sp_detailing);

        serviceListIntent = new Intent(ServicesPage.this, ServiceList.class);
        userHomeIntent = new Intent(ServicesPage.this, UserHome.class);

        oilChangeSelected();
        repairSelected();
        tuningSelected();
        detailingSelected();
        backButtonClick();
    }

    private void oilChangeSelected()
    {
        btn_j_sp_oilchange.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("Button Click", "Moving to Service List. Putting extra: OIL CHANGE");
                serviceListIntent.putExtra("CATEGORY", Service.SERVICE_TYPE_OIL_CHANGE);
                startActivity(serviceListIntent);
            }
        });
    }

    private void repairSelected()
    {
        btn_j_sp_repair.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("Button Click", "Moving to Service List. Putting extra: REPAIRS");
                serviceListIntent.putExtra("CATEGORY", Service.SERVICE_TYPE_REPAIRS);
                startActivity(serviceListIntent);
            }
        });
    }

    private void tuningSelected()
    {
        btn_j_sp_tuning.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("Button Click", "Moving to Service List. Putting extra: TUNING");
                serviceListIntent.putExtra("CATEGORY", Service.SERVICE_TYPE_TUNING);
                startActivity(serviceListIntent);
            }
        });
    }

    private void detailingSelected()
    {
        btn_j_sp_detailing.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("Button Click", "Moving to Service List. Putting extra: DETAILING");
                serviceListIntent.putExtra("CATEGORY", Service.SERVICE_TYPE_DETAILING);
                startActivity(serviceListIntent);
            }
        });
    }

    private void backButtonClick()
    {
        btn_j_sp_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("Button Click", "Moving to User Home");
                startActivity(userHomeIntent);
            }
        });
    }
}