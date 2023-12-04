package com.example.program_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.program_01.Database.ServiceDatabase;
import com.example.program_01.Models.Service;

public class editService extends AppCompatActivity
{
    //GUI
    Spinner sp_j_es_type;
    EditText et_j_es_desc;
    TextView tv_j_es_descError;
    Button btn_j_es_editService;
    Button btn_j_es_deleteService;
    Button btn_j_es_back;

    //OTHER
    Service service;
    Intent bussinessHomeIntent;
    ServiceDatabase serviceDb;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_service);
        //GUI
        sp_j_es_type = findViewById(R.id.sp_v_es_type);
        et_j_es_desc = findViewById(R.id.et_v_es_desc);
        tv_j_es_descError = findViewById(R.id.tv_v_es_descError);
        btn_j_es_editService = findViewById(R.id.btn_v_es_editService);
        btn_j_es_deleteService = findViewById(R.id.btn_v_es_deleteService);
        btn_j_es_back = findViewById(R.id.btn_v_es_back);

        //OTHER
        bussinessHomeIntent = new Intent(editService.this, businessHome.class);

        Intent cameFrom = getIntent();
        service = (Service) cameFrom.getSerializableExtra("myService");
        et_j_es_desc.setText(service.getServiceDescription()); //Set Description field to the service's current description

        serviceDb = new ServiceDatabase(this);

        //FUNCTIONS
        fillSpinner();
        spinnerItemSelectEvent();
        editServiceButtonEvent();
        deleteServiceButtonEvent();
        backButtonEvent();
    }

    public void fillSpinner()
    {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.serviceTypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_j_es_type.setAdapter(adapter);

        sp_j_es_type.setSelection(adapter.getPosition(service.getServiceType()));
        Log.d("================", service.getServiceType());
        Log.d("==============", String.valueOf(adapter.getPosition(service.getServiceType())));
    }

    public void spinnerItemSelectEvent()
    {
        sp_j_es_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String text = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
    }

    public void editServiceButtonEvent()
    {
        btn_j_es_editService.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("Button Press: ", "===Edit Service Button (From Edit Service Intent)===");

                if (et_j_es_desc.getText().toString().equals(""))
                {
                    tv_j_es_descError.setVisibility(View.VISIBLE);
                }
                else
                {
                    tv_j_es_descError.setVisibility(View.INVISIBLE);

                    serviceDb.updateService(sp_j_es_type.getSelectedItem().toString(), et_j_es_desc.getText().toString(), service.getServiceId());
                    startActivity(bussinessHomeIntent);
                }
            }
        });
    }

    public void deleteServiceButtonEvent()
    {
        btn_j_es_deleteService.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("Button Press: ", "===Delete Service Button (From Edit Service Intent)===");

                //CONFIRM (ARE YOU SURE YOU WANT TO DELETE?)
                serviceDb.deleteService(service.getServiceId());

                startActivity(bussinessHomeIntent);
            }
        });
    }

    public void backButtonEvent()
    {
        btn_j_es_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("Button Press: ", "===Back Button (From Edit Service Intent)===");
                startActivity(bussinessHomeIntent);
            }
        });
    }
}