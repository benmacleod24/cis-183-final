package com.example.program_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.program_01.Database.Database;
import com.example.program_01.Database.UsersDatabase;
import com.example.program_01.Models.Business;
import com.example.program_01.Models.User;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    UsersDatabase usersDb;

    //GUI Stuff
    EditText et_j_email;
    EditText et_j_password;
    Button btn_j_login;
    Button btn_j_createAccount;
    Button btn_j_createBusiness;
    TextView tv_j_loginError;
    TextView tv_j_emailError;
    TextView tv_j_passwordError;

    //Database stuff
    Database database;

    //Intent stuff
    Intent createBusinessIntent;
    Intent createUserIntent;

    //Array Stuff
    ArrayList<Business> listOfBusinesses;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usersDb = new UsersDatabase(this);

        //Connect GUI
        et_j_email = findViewById(R.id.et_v_email);
        et_j_password = findViewById(R.id.et_v_password);
        btn_j_login = findViewById(R.id.btn_v_login);
        btn_j_createAccount = findViewById(R.id.btn_v_createAccount);
        btn_j_createBusiness = findViewById(R.id.btn_v_createBusiness);
        tv_j_emailError = findViewById(R.id.tv_v_emailError);
        tv_j_passwordError = findViewById(R.id.tv_v_passwordError);
        tv_j_loginError = findViewById(R.id.tv_v_loginError);

        // Setup the database
        database = new Database(this);

        //Make ArrayList and fill data from database into it
        listOfBusinesses = new ArrayList<Business>();
        //listOfBusinesses = database.businesses.getAllBusinesses();
        //logBusinesses(); //For testing

        //Intent Stuff
        createBusinessIntent = new Intent(MainActivity.this, CreateBusiness.class);

        //Functions
        loginButtonEvent();
        createAccountButtonEvent();
        createBusinessButtonEvent();
    }

    public void loginButtonEvent()
    {
        btn_j_login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("Button Pressed:", "=====Login Button Pressed=====");
                if(!et_j_email.getText().toString().equals("") && !et_j_password.getText().toString().equals("")) //If both fields are filled
                {
                    //No need to set error visibilities to invisible because we'll be loading a new intent anyway (home page)

                    //Log in, then jump to home page intent

                }
                else
                {
                    if (et_j_email.getText().toString().equals("")) //If email field is empty
                    {
                        tv_j_emailError.setVisibility(View.VISIBLE); //Set email error visible
                    }
                    else {tv_j_emailError.setVisibility(View.INVISIBLE);} //If email field is filled, set email error invisible

                    if (et_j_password.getText().toString().equals("")) //If password field is empty
                    {
                        tv_j_passwordError.setVisibility(View.VISIBLE); //If password field is filled, set password error visible
                    }
                    else {tv_j_passwordError.setVisibility(View.INVISIBLE);} //Set password error invisible
                }
            }
        });
    }

    public void createAccountButtonEvent()
    {
        btn_j_createAccount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("Button Pressed:", "=====Create Account Button Pressed=====");
            }
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
}