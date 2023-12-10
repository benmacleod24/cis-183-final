package com.example.program_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.program_01.Controllers.Session;
import com.example.program_01.Database.OrderDatabase;
import com.example.program_01.Database.UsersDatabase;
import com.example.program_01.Models.User;

public class UserProfile extends AppCompatActivity
{

    UsersDatabase userdb;
    Button btn_delete;
    Button btn_update;
    ImageView btn_j_up_order;
    ImageView btn_j_up_home;
    User user;
    EditText inp_firstName;
    EditText inp_lastName;
    EditText inp_password;
    OrderDatabase orderDb;
    Intent deleteConfirmationIntent;
    Intent seeOrdersIntent;
    Intent userHomeIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        userdb = new UsersDatabase(this);
        orderDb = new OrderDatabase(this);
        user = userdb.getUserByEmail(Session.getUser().getEmail());

        deleteConfirmationIntent = new Intent(UserProfile.this, DeleteConfirmation.class);
        seeOrdersIntent = new Intent(UserProfile.this, UserOrders.class);
        userHomeIntent = new Intent(UserProfile.this, UserHome.class);

        setupElements();
    }

    private void setupElements()
    {
        btn_delete = findViewById(R.id.btn_up_delete);
        btn_update = findViewById(R.id.btn_up_update);
        inp_firstName = findViewById(R.id.inp_v_up_firstName);
        inp_lastName = findViewById(R.id.inp_v_up_lastName);
        inp_password = findViewById(R.id.inp_v_up_password);
        btn_j_up_order = findViewById(R.id.btn_v_up_order);
        btn_j_up_home = findViewById(R.id.btn_v_up_home);

        setupData();
        onDelete();
        onUpdate();
        onSeeOrders();
        onUserHome();
    }

    private void setupData()
    {
        inp_firstName.setText(user.getFirstName());
        inp_lastName.setText(user.getLastName());
        inp_password.setText(user.getPassword());
    }

    private void onDelete()
    {
        btn_delete.setOnClickListener(view -> {
            if (Session.getUser() == null) return;

            deleteConfirmationIntent.putExtra("cameFrom", "userProfileIntent");
            startActivity(deleteConfirmationIntent);
        });
    }

    private void onUpdate()
    {
        btn_update.setOnClickListener(view -> {
            String firstName = inp_firstName.getText().toString();
            String lastName = inp_lastName.getText().toString();
            String password = inp_password.getText().toString();

            User newUser = new User(Session.getUser().getEmail(), password, firstName, lastName);
            userdb.updateUser(newUser);

            startActivity(userHomeIntent);
        });
    }

    private void onSeeOrders()
    {
        btn_j_up_order.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("Button Press", "Moving to User Orders");
                startActivity(seeOrdersIntent);
            }
        });
    }

    private void onUserHome()
    {
        btn_j_up_home.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("Button Press", "Moving to User Home");
                startActivity(userHomeIntent);
            }
        });
    }
}