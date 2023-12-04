package com.example.program_01.Models;

public class Service
{
    //Access these by doing Service.SERVICE_TYPE....
    public static String SERVICE_TYPE_OIL_CHANGE = "SERVICE_OIL_CHANGE";
    public static String SERVICE_TYPE_REPAIRS = "SERVICE_REPAIRS";
    public static String SERVICE_TYPE_TUNING = "SERVICE_TUNING";
    public static String SERVICE_TYPE_DETAILING = "SERVICE_DETAILING";
    public static String SERVICE_TYPE_TOWING = "SERVICE_TOWING";
    public static String SERVICE_TYPE_ROADSIDE_ASSISTANCE = "SERVICE_ROADSIDE_ASSISTANCE";

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
