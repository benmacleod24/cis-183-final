package com.example.program_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.program_01.Controllers.Session;
import com.example.program_01.Database.UsersDatabase;
import com.example.program_01.Models.User;

public class CreateAccount extends AppCompatActivity {
    UsersDatabase userDb;
    Boolean emailIsUnique;

    // Inputs
    EditText inp_j_firstName;
    EditText inp_j_lastName;
    EditText inp_j_email;
    EditText inp_j_password;
    EditText inp_j_passwordConfirm;

    // Text
    TextView txt_j_valid_email;
    TextView txt_j_error;

    // Buttons
    Button btn_j_create;
    Button btn_j_cancel;

    // Intents
    Intent userHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        // Establish the new database.
        userDb = new UsersDatabase(this);

        userHome = new Intent(CreateAccount.this, UserHome.class);

        // Setup UI elements.
        mountElements();
    }

    private void mountElements()
    {
        inp_j_firstName = findViewById(R.id.inp_createAcct_firstName);
        inp_j_lastName = findViewById(R.id.inp_createAcct_lastName);
        inp_j_email = findViewById(R.id.inp_createAcct_email);
        inp_j_password = findViewById(R.id.inp_createAcct_password);
        inp_j_passwordConfirm = findViewById(R.id.inp_createAcct_confirmPassword);

        btn_j_create = findViewById(R.id.btn_createAcct_create);
        btn_j_cancel = findViewById(R.id.btn_createAcct_create2);

        txt_j_valid_email = findViewById(R.id.txt_createAcct_valid_email);
        txt_j_error = findViewById(R.id.txt_createAcct_error);

        // Mount Listeners
        handleCreateSubmit();
        onEmailChange();
    }

    private void onEmailChange()
    {
        inp_j_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean isValidEmail = userDb.doesEmailExist(s.toString());

                if (s.length() == 0)
                {
                    txt_j_valid_email.setVisibility(View.INVISIBLE);
                }
                else
                {
                    txt_j_valid_email.setVisibility(View.VISIBLE);
                }

                if (isValidEmail)
                {
                    txt_j_valid_email.setText("Valid Email");
                    txt_j_valid_email.setTextColor(Color.GREEN);
                    emailIsUnique = true;
                }
                else
                {
                    txt_j_valid_email.setText("invalid Email");
                    txt_j_valid_email.setTextColor(Color.RED);
                    emailIsUnique = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void handleCreateSubmit()
    {
        btn_j_create.setOnClickListener(view -> {
            String firstName = inp_j_firstName.getText().toString();
            String lastName = inp_j_lastName.getText().toString();
            String email = inp_j_email.getText().toString();
            String password = inp_j_password.getText().toString();
            String confirmPassword = inp_j_passwordConfirm.getText().toString();

            Log.d("P", password + "::" + confirmPassword);

            if (!emailIsUnique)
            {
                txt_j_error.setText("Email is Taken, try another one.");
                txt_j_error.setVisibility(View.VISIBLE);
                return;
            }

            if (firstName.isEmpty() || lastName.isEmpty() || password.isEmpty() || confirmPassword.isEmpty())
            {
                txt_j_error.setText("Please fill in all fields to create an account.");
                txt_j_error.setVisibility(View.VISIBLE);
                return;
            }

            if (password.equals(confirmPassword) && emailIsUnique)
            {
                Log.d("BTN", "Creating User");

                User user = new User(email, firstName, lastName);
                userDb.create(email, password, firstName, password);

                //Session.login(user);
                startActivity(userHome);
            }
            else
            {
                // No Match Password
                txt_j_error.setText("Passwords Do Not Match.");
                txt_j_error.setVisibility(View.VISIBLE);
            }
        });
    }
}