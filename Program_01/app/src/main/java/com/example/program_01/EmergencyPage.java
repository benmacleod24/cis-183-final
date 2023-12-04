package com.example.program_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.program_01.Database.OrderDatabase;
import com.example.program_01.Models.Service;

public class EmergencyPage extends AppCompatActivity {

    OrderDatabase orderDb;
    Button btn_j_towing;
    Button btn_j_roadSide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_page);

        orderDb = new OrderDatabase(this);

        btn_j_towing = findViewById(R.id.btn_emergency_towing);
        btn_j_roadSide = findViewById(R.id.btn_emergency_roadSide);

        onTowingClick();
        onRoadSideClick();
    }

    public void onTowingClick()
    {
        btn_j_towing.setOnClickListener(view -> {
            Intent serviceListIntent = new Intent(EmergencyPage.this, ServiceList.class);

            serviceListIntent.putExtra("CATEGORY", Service.)
        });
    }

    public void onRoadSideClick()
    {
        btn_j_roadSide.setOnClickListener(view -> {
            // Redirect To Intent For Services
        });
    }
}