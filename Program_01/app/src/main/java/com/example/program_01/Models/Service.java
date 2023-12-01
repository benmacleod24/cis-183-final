package com.example.program_01.Models;

public class Service
{
    //Access these by doing Service.SERVICE_TYPE_...
    //Basically, if a business creates a service and says it is an emergency service,
    //then pass Service.SERVICE_TYPE_... when creating the service (3rd in the constructor)
    public static String SERVICE_TYPE_EMERGENCY = "SERVICE_EMERGENCY";
    public static String SERVICE_TYPE_BASIC = "SERVICE_BASIC";

    private int serviceId; //Auto Incrementing
    private String businessId; //Business Email (foreign key)
    private String serviceType; //Emergency vs Basic
    private String serviceName; //Name of service
    private String serviceDesc; //Desc

    public Service() {}

    public Service(int id, String e, String sType, String sName, String sDesc)
    {
        //ORDER: serviceId, businessId, serviceType, serviceName, serviceDesc
        serviceId = id;
        businessId = e;
        serviceType = sType;
        serviceName = sName;
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

    public String getServiceName()
    {
        return serviceName;
    }

    public String getServiceDescription()
    {
        return serviceDesc;
    }

    //SETTERS
}
