package com.example.program_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.program_01.Controllers.Session;
import com.example.program_01.Database.OrderDatabase;
import com.example.program_01.Models.Order;

import java.util.ArrayList;

public class BusinessOrders extends AppCompatActivity {

    OrderDatabase orderDb;
    ArrayList<Order> listOfOrders;

    TextView txt_orderCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_orders);

        orderDb = new OrderDatabase(this);
        listOfOrders = orderDb.geOrdersByBusinessId(Session.getBusiness().getEmail());

        testOrders();
        mountElements();
        populateData();
    }

    private void mountElements()
    {
        txt_orderCount = findViewById(R.id.txt_bizOrders_orderCount);
    }

    private void populateData()
    {
        txt_orderCount.setText("Order Count: " + listOfOrders.size());
    }

    private void testOrders()
    {
        for (int i = 0; i < listOfOrders.size(); i++)
        {
            Log.d("Order: " + listOfOrders.get(i).getOrderId(), "For user: " + listOfOrders.get(i).getUserId() + "::serviceId " + listOfOrders.get(i).getServiceId());
        }
    }
}