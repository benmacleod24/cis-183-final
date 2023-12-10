package com.example.program_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.program_01.Controllers.Session;
import com.example.program_01.Database.OrderDatabase;
import com.example.program_01.Database.UsersDatabase;
import com.example.program_01.Models.User;

public class UserProfile extends AppCompatActivity {

    UsersDatabase userdb;
    ImageView btn_go_back;
    Button btn_delete;
    Button btn_update;
    User user;
    EditText inp_firstName;
    EditText inp_lastName;
    OrderDatabase orderDb;
    Intent deleteConfirmationIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        userdb = new UsersDatabase(this);
        orderDb = new OrderDatabase(this);
        user = userdb.getUserByEmail(Session.getUser().getEmail());

        deleteConfirmationIntent = new Intent(UserProfile.this, DeleteConfirmation.class);

        setupElements();
    }

    private void setupElements()
    {
        btn_go_back = findViewById(R.id.btn_v_up_back);
        btn_delete = findViewById(R.id.btn_up_delete);
        btn_update = findViewById(R.id.btn_up_update);
        inp_firstName = findViewById(R.id.inp_v_up_firstName);
        inp_lastName = findViewById(R.id.inp_v_up_lastName);

        setupData();

        onDelete();
        onGoBack();
        onUpdate();
    }

    private void setupData()
    {
        inp_firstName.setText(user.getFirstName());
        inp_lastName.setText(user.getLastName());
    }

    private void onGoBack()
    {
        btn_go_back.setOnClickListener(view -> {
            Intent userHome = new Intent(UserProfile.this, UserHome.class);
            startActivity(userHome);
        });
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

            User newUser = new User(Session.getUser().getEmail(), firstName, lastName);
            userdb.updateUser(newUser);
        });
    }
}