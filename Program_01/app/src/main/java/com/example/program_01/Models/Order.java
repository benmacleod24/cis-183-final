package com.example.program_01.Models;

public class Order
{
    public int orderId;
    public String businessId;
    public int serviceId;

    public Order() {}

    public Order(int o, String b, int s)
    {
        orderId = o;
        businessId = b;
        serviceId = s;
    }

    //GETTERS
    public int getOrderId()
    {
        return orderId;
    }

    public String getBusinessId()
    {
        return businessId;
    }

    public int getServiceId()
    {
        return serviceId;
    }

    //SETTERS
}
