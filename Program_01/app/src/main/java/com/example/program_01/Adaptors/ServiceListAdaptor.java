package com.example.program_01.Adaptors;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.program_01.Controllers.Session;
import com.example.program_01.Database.BusinessDatabase;
import com.example.program_01.Database.OrderDatabase;
import com.example.program_01.Models.Business;
import com.example.program_01.Models.Order;
import com.example.program_01.Models.Service;
import com.example.program_01.R;

import java.util.ArrayList;

public class ServiceListAdaptor extends BaseAdapter
{
    Context ctx;
    ArrayList<Service> listOfServices;
    BusinessDatabase bizDb;
    OrderDatabase orderDb;

    public ServiceListAdaptor(Context c, ArrayList<Service> ls)
    {
        listOfServices = ls;
        ctx = c;
        bizDb = new BusinessDatabase(c);
        orderDb = new OrderDatabase(c);
    }

    @Override
    public int getCount()
    {
        return listOfServices.size();
    }

    @Override
    public Object getItem(int position)
    {
        return listOfServices.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {

        if (view == null)
        {
            LayoutInflater mInflater = (LayoutInflater) ctx.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.cell_business_service_list, null);
        }

        TextView txt_j_bizName = view.findViewById(R.id.txt_bizServLs_name);
        TextView txt_j_desc = view.findViewById(R.id.txt_bizServLs_description);
        Button btn_j_order = view.findViewById(R.id.btn_bizServLs_order);

        Service service = listOfServices.get(i);
        Business business = bizDb.getBusinessByEmail(service.getBusinessId());

        btn_j_order.setOnClickListener(_view -> {
            if (Session.getUser() == null) return;
            Order _order = new Order(service.getBusinessId(), service.getServiceId(), Session.getUser().getEmail());
            orderDb.createOrder(_order);
        });

        txt_j_bizName.setText(business.getName());
        txt_j_desc.setText(service.getServiceDescription());

        return view;
    }
}
