package com.example.program_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class businessHome extends AppCompatActivity
{
    Button btn_j_createService;
    Button btn_j_editService;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_home);

        btn_j_createService = findViewById(R.id.btn_v_createService);
        btn_j_editService = findViewById(R.id.btn_v_editService);


        createServiceButtonEvent();
        editServiceButtonEvent();
    }

    public void createServiceButtonEvent()
    {
        btn_j_createService.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("Button Pressed:", "=====Create Service Button Pressed=====");

            }
        });
    }

    public void editServiceButtonEvent()
    {
        btn_j_editService.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("Button Pressed:", "=====Edit Service Button Pressed=====");

            }
        });
    }
}