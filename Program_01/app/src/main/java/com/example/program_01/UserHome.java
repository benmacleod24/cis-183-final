package com.example.program_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.program_01.Controllers.Session;

public class UserHome extends AppCompatActivity {

    TextView txt_j_welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        // Setup the page with elements and data.
        mountElements();
        mountData();
    }

    public void mountElements()
    {
        txt_j_welcome = findViewById(R.id.txt_userHome_welcome);
    }

    private void mountData()
    {
        //txt_j_welcome.setText("Welcome, " + Session.getUser().getFirstName() + "!");
    }
}