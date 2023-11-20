package com.example.program_01.Controllers;

import com.example.program_01.Models.Business;
import com.example.program_01.Models.User;

public class Session
{
    public static String USER_TYPE = "USER";
    public static String BUSINESS_TYPE = "BUSINESS";
    private static String sessionType;
    private static Business business;
    private static User user;
    private static boolean loggedIn;

    public static void login(User u)
    {
        sessionType = USER_TYPE;
        loggedIn = true;
        user = u;
    }

    public static  void login(Business b)
    {
        sessionType = BUSINESS_TYPE;
        loggedIn = true;
        business = b;
    }

    public static String getSessionType()
    {
        return sessionType;
    }

    public static User getUser()
    {
        return user;
    }

    public static  Business getBusiness()
    {
        return business;
    }
}
