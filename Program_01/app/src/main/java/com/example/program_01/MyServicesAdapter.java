package com.example.program_01;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.program_01.Database.ServiceDatabase;
import com.example.program_01.Models.Service;

import java.util.ArrayList;

public class MyServicesAdapter extends BaseAdapter
{
    Context context;
    ArrayList<Service> listOfMyServices;

    public MyServicesAdapter(Context c, ArrayList<Service> ls)
    {
        context = c;
        listOfMyServices = ls;
    }

    @Override
    public int getCount()
    {
        return listOfMyServices.size();
    }

    @Override
    public Object getItem(int i)
    {
        return listOfMyServices.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if (view == null)
        {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.myservices_custom_cell, null);
        }

        //GUI
        TextView tv_j_myServicesCC_Type = view.findViewById(R.id.tv_v_myServicesCC_Type);
        TextView tv_j_myServicesCC_Desc = view.findViewById(R.id.tv_v_myServicesCC_Desc);

        Service service = listOfMyServices.get(i);
        tv_j_myServicesCC_Type.setText(service.getServiceType());
        tv_j_myServicesCC_Desc.setText(service.getServiceDescription());

        return view;
    }
}
