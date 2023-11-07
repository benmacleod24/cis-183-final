package com.example.program_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.program_01.Database.Database;

public class MainActivity extends AppCompatActivity {
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup the database
        database = new Database(this);

        // Example: How to get a user by id.
        database.users.getUserById(1);
    }
}