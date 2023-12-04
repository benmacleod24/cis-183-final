package com.example.program_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.program_01.Controllers.Session;
import com.example.program_01.Database.OrderDatabase;
import com.example.program_01.Models.Order;

import java.util.ArrayList;

public class UserHome extends AppCompatActivity {

    Button btn_emergency;
    Button btn_services;
    OrderDatabase orderDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        orderDb = new OrderDatabase(this);

        // Setup the page with elements and data.
        mountElements();
        testOrders();
    }

    public void testOrders()
    {
        if (Session.getUser() == null) return;
        ArrayList<Order> orders = orderDb.getOrderByUser(Session.getUser().getEmail());

        for (int i =0; i < orders.size(); i++)
        {
            Log.d("OrderID: " + orders.get(i).getOrderId(), "ServiceID: " + orders.get(i).getServiceId() + "  ||  BizID:" + orders.get(i).getBusinessId());
        }
    }

    public void mountElements()
    {
        btn_emergency = findViewById(R.id.btn_emergency);
        btn_services = findViewById(R.id.btn_services);

        onEmergencyClick();
        onServiceClick();
    }

    private void onEmergencyClick()
    {
        btn_emergency.setOnClickListener(view -> {
            Log.d("BTN_CLICK", "Moving to Emergency Page.");
            Intent emergencyIntent = new Intent(UserHome.this, EmergencyPage.class);
            startActivity(emergencyIntent);
        });
    }

    private void onServiceClick()
    {
        btn_services.setOnClickListener(view -> {
            Log.d("BTN_CLICK", "Moving to service page");
            Intent serviceIntent = new Intent(UserHome.this, ServicesPage.class);
            startActivity(serviceIntent);
        });
    }
}