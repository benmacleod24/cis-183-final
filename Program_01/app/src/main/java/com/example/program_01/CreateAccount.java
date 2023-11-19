package com.example.program_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.program_01.Controllers.Session;
import com.example.program_01.Database.UsersDatabase;
import com.example.program_01.Models.User;

public class CreateAccount extends AppCompatActivity {
    UsersDatabase userDb;

    // Inputs
    EditText inp_j_firstName;
    EditText inp_j_lastName;
    EditText inp_j_email;
    EditText inp_j_password;
    EditText inp_j_passwordConfirm;

    // Buttons
    Button btn_j_create;
    Button btn_j_cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        // Establish the new database.
        userDb = new UsersDatabase(this);

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

        // Mount Listeners
        handleCreateSubmit();
    }

    public void handleCreateSubmit()
    {
        btn_j_create.setOnClickListener(view -> {
            String firstName = inp_j_firstName.toString();
            String lastName = inp_j_lastName.toString();
            String email = inp_j_email.toString();
            String password = inp_j_password.toString();
            String confirmPassword = inp_j_passwordConfirm.toString();

            if (password.equals(confirmPassword))
            {
                User user = new User(email, firstName, lastName);
                userDb.create(email, password, firstName, password);

                Session.login(user);
            }
            else
            {
                // No Match Password
            }
        });
    }
}