package com.example.program_01.Controllers;

import com.example.program_01.Models.User;

public class Session
{
    private static boolean _isLoggedIn;
    private static User user;

    /**
     * Get is the user is logged in or not.
     * @return Boolean
     */
    public static boolean isLoggedIn()
    {
        return _isLoggedIn;
    }

    /**
     * Get the logged in user.
     * @return User
     */
    public static User getUser()
    {
        return user;
    }

    /**
     * Log the user in.
     */
    public static void login(User u)
    {
        _isLoggedIn = true;
        user = u;
    }
}
