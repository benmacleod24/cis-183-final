package com.example.program_01.Models;

import java.io.Serializable;

public class Service implements Serializable
{
    //Access these by doing Service.SERVICE_TYPE....
    public static String SERVICE_TYPE_OIL_CHANGE = "OIL CHANGE";
    public static String SERVICE_TYPE_REPAIRS = "REPAIRS";
    public static String SERVICE_TYPE_TUNING = "TUNING";
    public static String SERVICE_TYPE_DETAILING = "DETAILING";
    public static String SERVICE_TYPE_TOWING = "TOWING";
    public static String SERVICE_TYPE_ROADSIDE_ASSISTANCE = "ROADSIDE ASSISTANCE";

    private int serviceId; //Auto Incrementing
    private String businessId; //Business Email (foreign key)
    private String serviceType; //Any of the predefined service types
    private String serviceDesc; //Desc

    public Service() {}

    public Service(int id, String e, String sType, String sDesc)
    {
        //ORDER: serviceId, businessId, serviceType, serviceName, serviceDesc
        serviceId = id;
        businessId = e;
        serviceType = sType;
        serviceDesc = sDesc;
    }

    //GETTERS
    public int getServiceId()
    {
        return serviceId;
    }

    public String getBusinessId()
    {
        return businessId;
    }

    public String getServiceType()
    {
        return serviceType;
    }

    public String getServiceDescription()
    {
        return serviceDesc;
    }

    //SETTERS
}
