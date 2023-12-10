package com.example.program_01.Adaptors;

import android.app.Activity;
import android.content.Context;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.program_01.Database.BusinessDatabase;
import com.example.program_01.Database.OrderDatabase;
import com.example.program_01.Database.ServiceDatabase;
import com.example.program_01.Models.Business;
import com.example.program_01.Models.Order;
import com.example.program_01.Models.Service;
import com.example.program_01.R;

import java.util.ArrayList;

public class UserOrderAdaptor extends BaseAdapter
{
    Context ctx;
    ArrayList<Order> listOfOrders;
    BusinessDatabase bizDb;
    ServiceDatabase serviceDb;
    OrderDatabase orderDb;

    public UserOrderAdaptor(Context ctx, ArrayList<Order> lofO)
    {
        this.ctx = ctx;
        listOfOrders = lofO;
        bizDb = new BusinessDatabase(ctx);
        serviceDb = new ServiceDatabase(ctx);
        orderDb = new OrderDatabase(ctx);
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
            view = mInflater.inflate(R.layout.cell_user_order, null);
        }

        // Elements
        TextView businessName = view.findViewById(R.id.txt_cellUserOrder_bizName);
        TextView service = view.findViewById(R.id.txt_cellUserOrder_service);
        Button btn_cancel = view.findViewById(R.id.btn_v_cuo_cancel);

        Order order = listOfOrders.get(i);
        Business biz = bizDb.getBusinessByEmail(order.getBusinessId());
        Service _service = serviceDb.getServiceById(order.getServiceId());

        businessName.setText(biz.getName());
        service.setText(_service.getServiceType());

        btn_cancel.setOnClickListener(_view -> {
            orderDb.deleteOrder(order.getOrderId());
            listOfOrders.remove(i);
            this.notifyDataSetChanged();
        });

        return view;
    }
}
