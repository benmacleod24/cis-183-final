package com.example.program_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.program_01.Adaptors.ServiceListAdaptor;
import com.example.program_01.Database.ServiceDatabase;
import com.example.program_01.Models.Service;

import java.util.ArrayList;

public class ServiceList extends AppCompatActivity {

    String serviceCategory;
    ServiceDatabase serviceDb;
    ListView listView;
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
        fillAdaptor();
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
}