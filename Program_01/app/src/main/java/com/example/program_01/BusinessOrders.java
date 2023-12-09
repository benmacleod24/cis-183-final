package com.example.program_01;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.program_01.Adaptors.BusinessOrderAdaptor;
import com.example.program_01.Controllers.Session;
import com.example.program_01.Database.OrderDatabase;
import com.example.program_01.Models.Order;

import java.util.ArrayList;

public class BusinessOrders extends AppCompatActivity
{
    ImageView btn_j_businessHome;
    ImageView btn_j_editProfile;

    Intent businessHomeIntent;
    Intent editProfileIntent;

    OrderDatabase orderDb;
    ArrayList<Order> listOfOrders;

    TextView txt_orderCount;
    ListView listView;
    BusinessOrderAdaptor adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_orders);

        businessHomeIntent = new Intent(BusinessOrders.this, businessHome.class);
        editProfileIntent = new Intent(BusinessOrders.this, editBusinessProfile.class);

        orderDb = new OrderDatabase(this);
        listOfOrders = orderDb.geOrdersByBusinessId(Session.getBusiness().getEmail());

        testOrders();
        mountElements();
        populateData();
        businessHomeClick();
        editProfileClick();
    }

    private void mountElements()
    {
        txt_orderCount = findViewById(R.id.txt_bizOrders_orderCount);
        listView = findViewById(R.id.lv_bizOrders_list);
        btn_j_businessHome = findViewById(R.id.btn_v_orders_businessHome);
        btn_j_editProfile = findViewById(R.id.btn_v_orders_editProfile);
    }

    @SuppressLint("SetTextI18n")
    private void populateData()
    {
        txt_orderCount.setText("Order Count: " + listOfOrders.size());

        adaptor = new BusinessOrderAdaptor(this, listOfOrders);
        listView.setAdapter(adaptor);
    }

    private void testOrders()
    {
        for (int i = 0; i < listOfOrders.size(); i++)
        {
            Log.d("Order: " + listOfOrders.get(i).getOrderId(), "For user: " + listOfOrders.get(i).getUserId() + "::serviceId " + listOfOrders.get(i).getServiceId());
        }
    }

    private void businessHomeClick()
    {
        btn_j_businessHome.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("Button Press", "=====Business Home Btn=====");
                startActivity(businessHomeIntent);
            }
        });
    }

    private void editProfileClick()
    {
        btn_j_editProfile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("Button Press", "=====Edit Profile Btn (Biz)=====");
                startActivity(editProfileIntent);
            }
        });
    }
}