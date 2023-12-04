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

import java.util.ArrayList;

public class CreateBusiness extends AppCompatActivity
{
    //GUI Stuff
    EditText et_j_cb_email;
    EditText et_j_cb_password;
    EditText et_j_cb_name;
    EditText et_j_cb_number;
    TextView tv_j_cb_fieldsError; //All fields aren't filled out error
    TextView tv_j_cb_emailError; //Email already in use error
    Button btn_j_cb_createBusiness;
    Button btn_j_cb_back;

    //Intent Stuff
    Intent mainActivityIntent;
    Intent bussinessHomeIntent;

    //Database Stuff
    BusinessDatabase businessDb;
    UsersDatabase usersDb;

    //ArrayList Stuff
    ArrayList<Business> listOfBusinesses;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_business);
        //Connect GUI
        et_j_cb_email = findViewById(R.id.et_v_cb_email);
        et_j_cb_password = findViewById(R.id.et_v_cb_password);
        et_j_cb_name = findViewById(R.id.et_v_cb_name);
        et_j_cb_number = findViewById(R.id.et_v_cb_number);
        tv_j_cb_fieldsError = findViewById(R.id.tv_v_cb_fieldsError);
        tv_j_cb_emailError = findViewById(R.id.tv_v_cb_emailError);
        btn_j_cb_createBusiness = findViewById(R.id.btn_v_cb_createBusiness);
        btn_j_cb_back = findViewById(R.id.btn_v_cb_back);

        //Database Stuff
        businessDb = new BusinessDatabase(this);
        usersDb = new UsersDatabase(this);

        //Make ArrayList and fill data from database into it to check for unique email
        listOfBusinesses = new ArrayList<Business>();
        listOfBusinesses = businessDb.getAllBusinesses();
        //TEST
        for (int i = 0; i < listOfBusinesses.size(); i++)
        {
            Log.d("Name of Business " + i + ":", listOfBusinesses.get(i).getName() + ", " + listOfBusinesses.get(i).getEmail());
        }

        //Intent Stuff
        mainActivityIntent = new Intent(CreateBusiness.this, MainActivity.class);
        bussinessHomeIntent = new Intent(CreateBusiness.this, businessHome.class);


        //Functions
        createBusinessButtonEvent();
        backButtonEvent();
    }

    public void createBusinessButtonEvent()
    {
        btn_j_cb_createBusiness.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("Button Pressed:", "=====Create Business Button Pressed (From CreateBusinessIntent)=====");
                //Store text box info since we'll need it twice anyway
                String email = et_j_cb_email.getText().toString();
                String password = et_j_cb_password.getText().toString();
                String name = et_j_cb_name.getText().toString();
                String number = et_j_cb_number.getText().toString();

                if (email.equals("") || password.equals("") || name.equals("") || number.equals("")) //If any field isn't filled out
                {
                    tv_j_cb_fieldsError.setVisibility(View.VISIBLE); //Show error to fill out fields
                }
                else
                {
                    tv_j_cb_fieldsError.setVisibility(View.INVISIBLE); //Hide error to fill out fields

                    boolean isUnique = true;
                    for (int i = 0; i < listOfBusinesses.size(); i++) //Iterate through all businesses
                    {
                        if (email.equals(listOfBusinesses.get(i).getEmail())) //If the email in the text box equals any email from the list, then
                        {
                            isUnique = false; //It is no longer unique
                        }
                    }
                    if (!usersDb.doesEmailExist(email)) //Also check users
                    { //Seems like logic is flipped for .doesEmailExits() but it's ok
                        isUnique = false;
                    }
                    if (isUnique) //If it ended up being unique
                    {
                        tv_j_cb_emailError.setVisibility(View.INVISIBLE); //Hide error (is unique)
                        //Make new Business
                        Business b = new Business(email, password, name, number);
                        businessDb.createBusiness(b);

                        //LOG IN AND GO TO BUSINESS HOME PAGE
                        Session.login(b);
                        startActivity(bussinessHomeIntent);
                    }
                    else
                    {
                        tv_j_cb_emailError.setVisibility(View.VISIBLE); //Show error (not unique)
                    }
                }
            }
        });
    }

    public void backButtonEvent()
    {
        btn_j_cb_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("Button Pressed:", "=====Back Button Pressed (From CreateBusinessIntent)=====");
                startActivity(mainActivityIntent);
            }
        });
    }
}