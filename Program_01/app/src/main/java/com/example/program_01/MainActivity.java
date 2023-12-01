package com.example.program_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.program_01.Controllers.Session;
import com.example.program_01.Database.BusinessDatabase;
import com.example.program_01.Database.Database;
import com.example.program_01.Database.UsersDatabase;
import com.example.program_01.Models.Business;
import com.example.program_01.Models.User;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    UsersDatabase usersDb;
    BusinessDatabase bizDb;

    //GUI Stuff
    EditText et_j_email;
    EditText et_j_password;
    Button btn_j_login;
    Button btn_j_createAccount;
    Button btn_j_createBusiness;
    TextView tv_v_fieldsError;
    TextView tv_v_loginError;

    //Database stuff
    Database database;

    //Intent stuff
    Intent createBusinessIntent;
    Intent createUserIntent;
    Intent userHomeIntent;
    Intent businessHomeIntent;

    //Array Stuff
    ArrayList<Business> listOfBusinesses;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usersDb = new UsersDatabase(this);
        bizDb = new BusinessDatabase(this);

        //Connect GUI
        et_j_email = findViewById(R.id.et_v_email);
        et_j_password = findViewById(R.id.et_v_password);
        btn_j_login = findViewById(R.id.btn_v_login);
        btn_j_createAccount = findViewById(R.id.btn_v_createAccount);
        btn_j_createBusiness = findViewById(R.id.btn_v_createBusiness);
        tv_v_fieldsError = findViewById(R.id.tv_v_fieldsError);
        tv_v_loginError = findViewById(R.id.tv_v_loginError);


        // Setup the database
        database = new Database(this);

        //Make ArrayList and fill data from database into it
        listOfBusinesses = new ArrayList<Business>();
        //listOfBusinesses = database.businesses.getAllBusinesses();
        //logBusinesses(); //For testing

        //Intent Stuff
        createBusinessIntent = new Intent(MainActivity.this, CreateBusiness.class);
        createUserIntent = new Intent(MainActivity.this, CreateAccount.class);
        userHomeIntent = new Intent(MainActivity.this, UserHome.class);
        businessHomeIntent = new Intent(MainActivity.this, businessHome.class);

        //Functions
        loginButtonEvent();
        createAccountButtonEvent();
        createBusinessButtonEvent();
    }

    public void loginButtonEvent()
    {
        btn_j_login.setOnClickListener(v ->
        {
            String email = et_j_email.getText().toString();
            String password = et_j_password.getText().toString();
            tv_v_loginError.setVisibility(View.INVISIBLE);

            if (email.isEmpty() || password.isEmpty())
            {
                // Tell the user to enter all fields.
                tv_v_fieldsError.setVisibility(View.VISIBLE);
            }
            else
            {
                tv_v_fieldsError.setVisibility(View.INVISIBLE);

                // Get the account type.
                String accountType = getEmailAccountType(email);

                if (accountType == null)
                {
                    tv_v_loginError.setVisibility(View.VISIBLE);
                }
                else if (accountType.equals(Session.USER_TYPE)) // User Account Type.
                {
                    User user = usersDb.getUserByEmail(email);

                    // Check Login validity & Log the user in.
                    if (user.isValidLogin(password))
                    {
                        Session.login(user);
                        startActivity(userHomeIntent);
                    }
                    else //Not valid password. Show error
                    {
                        tv_v_loginError.setVisibility(View.VISIBLE);
                    }

                }
                else if (accountType.equals(Session.BUSINESS_TYPE)) // Business Account Type.
                {
                    // Login Business.
                    Business business = bizDb.getBusinessByEmail(email);
                    if (business.isValidLogin(password))
                    {
                        Session.login(business);
                        startActivity(businessHomeIntent);
                    }
                    else //Not valid password. Show error
                    {
                        tv_v_loginError.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    public void createAccountButtonEvent()
    {
        btn_j_createAccount.setOnClickListener(view -> {
            startActivity(createUserIntent);
        });
    }

    public void createBusinessButtonEvent()
    {
        btn_j_createBusiness.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("Button Pressed:", "=====Create Business Button Pressed=====");
                startActivity(createBusinessIntent);
            }
        });
    }

    public void logBusinesses()
    {
        for (int i = 0; i < listOfBusinesses.size(); i++)
        {
            Log.d("Business " + i + ":", listOfBusinesses.get(i).getName());
        }
    }

    /**
     * Get the account type of the email.
     * @param email
     * @return The account types defined in session controller.
     */
    public String getEmailAccountType(String email)
    {
        if (usersDb.getUserByEmail(email) != null)
        {
            return Session.USER_TYPE;
        }
        else if (bizDb.getBusinessByEmail(email) != null)
        {
            return Session.BUSINESS_TYPE;
        }

        return null;
    }
}