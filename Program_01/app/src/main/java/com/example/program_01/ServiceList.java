package com.example.program_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.program_01.Adaptors.ServiceListAdaptor;
import com.example.program_01.Database.ServiceDatabase;
import com.example.program_01.Models.Service;

import java.util.ArrayList;

public class ServiceList extends AppCompatActivity {

    String serviceCategory;
    ServiceDatabase serviceDb;
    ListView listView;
    ImageView btn_j_sl_back;
    Intent userHomeIntent;
    ServiceListAdaptor adaptor;
    ArrayList<Service> listOfServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);
        getCategoryFromExtra();

        serviceDb = new ServiceDatabase(this);
        listOfServices = serviceDb.getAllServicesByType(serviceCategory);

        listView = findViewById(R.id.lv_serviceLs_list);
        btn_j_sl_back = findViewById(R.id.btn_v_sl_back);
        userHomeIntent = new Intent(ServiceList.this, UserHome.class);

        fillAdaptor();
        backButtonClick();
    }

    private void getCategoryFromExtra()
    {
        Intent origin = getIntent();
        Bundle _bundle = origin.getExtras();

        Log.d("CATEGORY",_bundle.getString("CATEGORY"));
        serviceCategory = _bundle.getString("CATEGORY");
    }

    private void fillAdaptor()
    {
        adaptor = new ServiceListAdaptor(this, listOfServices);
        listView.setAdapter(adaptor);
    }

    private void backButtonClick()
    {
        btn_j_sl_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("Button Press", "Moving to Emergency Page from Service List Page");
                startActivity(userHomeIntent);
            }
        });
    }
}