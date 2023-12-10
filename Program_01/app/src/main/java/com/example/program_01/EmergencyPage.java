package com.example.program_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.example.program_01.Database.OrderDatabase;
import com.example.program_01.Models.Service;

public class EmergencyPage extends AppCompatActivity {

    OrderDatabase orderDb;
    Button btn_j_towing;
    Button btn_j_roadSide;
    ImageView btn_back;
    ImageView btn_j_menu_orders;
    ImageView btn_j_menu_home;
    ImageView btn_j_menu_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_page);

        orderDb = new OrderDatabase(this);

        btn_j_towing = findViewById(R.id.btn_emergency_towing);
        btn_j_roadSide = findViewById(R.id.btn_emergency_roadSide);
        btn_back = findViewById(R.id.btn_v_ep_back);

        // Menu Buttons
        btn_j_menu_profile = findViewById(R.id.btn_v_ep_profile);
        btn_j_menu_home = findViewById(R.id.btn_v_ep_home);
        btn_j_menu_orders = findViewById(R.id.btn_v_ep_order);

        onTowingClick();
        onRoadSideClick();
        onBackCLick();
        setupMenuClick();
    }

    public void onTowingClick()
    {
        btn_j_towing.setOnClickListener(view -> {
            Intent serviceListIntent = new Intent(EmergencyPage.this, ServiceList.class);
            serviceListIntent.putExtra("CATEGORY", Service.SERVICE_TYPE_TOWING);
            startActivity(serviceListIntent);
        });
    }

    public void onRoadSideClick()
    {
        btn_j_roadSide.setOnClickListener(view -> {
            Intent serviceListIntent = new Intent(EmergencyPage.this, ServiceList.class);
            serviceListIntent.putExtra("CATEGORY", Service.SERVICE_TYPE_ROADSIDE_ASSISTANCE);
            startActivity(serviceListIntent);
        });
    }

    public void onBackCLick()
    {
        btn_back.setOnClickListener(view -> {
            Intent goBack = new Intent(EmergencyPage.this, UserHome.class);
            startActivity(goBack);
        });
    }

    public void setupMenuClick()
    {
        btn_j_menu_home.setOnClickListener(view -> {
            Intent userHomeIntent = new Intent(EmergencyPage.this, UserHome.class);
            startActivity(userHomeIntent);
        });

        btn_j_menu_orders.setOnClickListener(view -> {
            Intent userOrderIntent = new Intent(EmergencyPage.this, UserOrders.class);
            startActivity(userOrderIntent);
        });

        btn_j_menu_profile.setOnClickListener(view -> {
            Intent profileIntent = new Intent(EmergencyPage.this, UserProfile.class);
            startActivity(profileIntent);
        });
    }
}