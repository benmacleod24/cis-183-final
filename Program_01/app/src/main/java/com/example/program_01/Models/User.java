package com.example.program_01.Models;

public class User
{
    private String email;

    // Really don't want to store the password in the user class.
    private String password;

    public User()
    {

    }

    public User(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public User(String email, String password)
    {
        this.email = email;
        this.password = password;
    }

    /**
     * Determine if a login attempt is valid.
     * @param enteredPassword Password entered in by the user.
     * @return True or False depending on the validity of the request.
     */
    public boolean isValidLogin(String enteredPassword)
    {
        if (enteredPassword.equals(password))
        {
            return true;
        }

        return false;
    }
}
