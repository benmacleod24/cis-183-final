package com.example.program_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.program_01.Database.ServiceDatabase;
import com.example.program_01.Models.Service;

import java.util.ArrayList;

public class ServiceList extends AppCompatActivity {

    String serviceCategory;
    ServiceDatabase serviceDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);
        getCategoryFromExtra();

        serviceDb = new ServiceDatabase(this);
        ArrayList<Service> services = serviceDb.getAllServicesByType(serviceCategory);

        for (int i = 0; i < services.size(); i++)
        {
            Log.d("BIZ::ID", services.get(i).getBusinessId());
        }
    }

    private void getCategoryFromExtra()
    {
        Intent origin = getIntent();
        Bundle _bundle = origin.getExtras();

        Log.d("CATEGORY",_bundle.getString("CATEGORY"));
        serviceCategory = _bundle.getString("CATEGORY");
    }
}