package com.example.program_01.Models;

import com.example.program_01.Controllers.Session;

public class User
{
    private String email;

    // Really don't want to store the password in the user class.
    private String password;

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public String getPassword()
    {
        return password;
    }

    private String firstName;
    private String lastName;

    public User()
    {

    }

    public User(String email, String f, String l)
    {
        this.firstName = f;
        this.lastName = l;
        this.email = email;
    }

    public User(String email, String password, String f, String l)
    {
        this.email = email;
        this.password = password;
        this.firstName = f;
        this.lastName = l;
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
            //Session.login(this);
            return true;
        }

        return false;
    }
}
