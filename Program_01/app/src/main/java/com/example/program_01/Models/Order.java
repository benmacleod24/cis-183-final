package com.example.program_01.Models;

public class Order
{
    public int orderId;
    public String businessId;
    public int serviceId;
    public String userId;

    public Order() {}

    public Order(int o, String b, int s, String u)
    {
        orderId = o;
        businessId = b;
        serviceId = s;
        userId = u;
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

    public String getUserId() {
        return userId;
    }


    //SETTERS
}
