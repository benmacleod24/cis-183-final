package com.example.program_01.Adaptors;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.program_01.Database.ServiceDatabase;
import com.example.program_01.Database.UsersDatabase;
import com.example.program_01.Models.Order;
import com.example.program_01.Models.Service;
import com.example.program_01.Models.User;
import com.example.program_01.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BusinessOrderAdaptor extends BaseAdapter {

    ArrayList<Order> listOfOrders;
    Context ctx;
    ServiceDatabase serviceDb;
    UsersDatabase userDb;

    public BusinessOrderAdaptor(Context ctx, ArrayList<Order> lOfO)
    {
        this.ctx = ctx;
        listOfOrders = lOfO;
        serviceDb = new ServiceDatabase(ctx);
        userDb = new UsersDatabase(ctx);
    }

    @Override
    public int getCount() {
        return listOfOrders.size();
    }

    @Override
    public Object getItem(int position) {
        return listOfOrders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        if (view == null)
        {
            LayoutInflater mInflater = (LayoutInflater) ctx.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.cell_business_order, null);
        }

        // Elements
        TextView email = view.findViewById(R.id.txt_cellBizOrder_email);
        TextView service = view.findViewById(R.id.txt_cellBizOrder_service);

        Order order = listOfOrders.get(i);
        User user = userDb.getUserByEmail(order.getUserId());
        Service _service = serviceDb.getServiceById(order.getServiceId());

        email.setText(user.getFirstName() + " " + user.getLastName());
        service.setText(_service.getServiceType());

        return view;
    }
}
