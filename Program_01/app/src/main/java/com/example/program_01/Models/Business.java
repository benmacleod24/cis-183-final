package com.example.program_01.Models;

public class Business
{
    private String email;
    private String password;
    private String name;
    private String number;

    public Business()
    {

    }

    public Business(String e, String p, String n, String num)
    {
        email = e;
        password = p;
        name = n;
        number = num;
    }

    //Getters =================
    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    public String getName()
    {
        return name;
    }

    public String getNumber()
    {
        return number;
    }
    //=========================

    //Setters =================
    public void setEmail(String e)
    {
        email = e;
    }

    public void setPassword(String p)
    {
        password = p;
    }

    public void setName(String n)
    {
        name = n;
    }

    public void setNumber(String num)
    {
        number = num;
    }
    //=========================

    public boolean isValidLogin(String enteredPassword) //Check login
    {
        if (enteredPassword.equals(password))
        {
            //Session.login(this);
            return true;
        }

        return false;
    }
}
