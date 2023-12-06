package com.example.program_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.program_01.Controllers.Session;
import com.example.program_01.Database.BusinessDatabase;
import com.example.program_01.Models.Business;

public class editBusinessProfile extends AppCompatActivity
{
    //GUI
    EditText et_j_ebp_name;
    EditText et_j_ebp_number;
    EditText et_j_ebp_password;
    Button btn_j_ebp_saveChanges;
    ImageView btn_j_ebp_seeOrders;
    ImageView btn_j_ebp_businessHome;
    TextView tv_j_ebp_error;
    //Not doing edit profile intent since I'm already here

    //INTENTS
    Intent businessHomeIntent;
    Intent seeOrdersIntent;

    //DATABASE
    BusinessDatabase businessDb;
    Business b;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_business_profile);

        //GUI
        et_j_ebp_name = findViewById(R.id.et_v_ebp_name);
        et_j_ebp_number = findViewById(R.id.et_v_ebp_number);
        et_j_ebp_password = findViewById(R.id.et_v_ebp_password);
        btn_j_ebp_saveChanges = findViewById(R.id.btn_v_ebp_saveChanges);
        btn_j_ebp_seeOrders = findViewById(R.id.btn_v_ebp_seeOrders);
        btn_j_ebp_businessHome = findViewById(R.id.btn_v_ebp_businessHome);
        tv_j_ebp_error = findViewById(R.id.tv_v_ebp_error);

        //INTENTS
        businessHomeIntent = new Intent(editBusinessProfile.this, businessHome.class);
        //seeOrdersIntent = new Intent(editBusinessProfile.this, SEE ORDERS INTENT.CLASS);

        //DATABASE
        businessDb = new BusinessDatabase(this);

        //FUNCTIONS
        fillTextBoxes();
        saveChangesButtonClick();
        businessHomeButtonClick();
        seeOrdersButtonClick();
    }

    public void fillTextBoxes()
    {
        b = businessDb.getBusinessByEmail(Session.getBusiness().getEmail());

        et_j_ebp_name.setText(b.getName().toString());
        et_j_ebp_number.setText(b.getNumber().toString());
        et_j_ebp_password.setText(b.getPassword().toString());
    }

    public void saveChangesButtonClick()
    {
        btn_j_ebp_saveChanges.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("Button Pressed:", "=====Save Changes (EBP) Button Pressed=====");

                if (et_j_ebp_name.getText().toString().equals("") || et_j_ebp_number.getText().toString().equals("") || et_j_ebp_password.getText().toString().equals(""))
                {
                    tv_j_ebp_error.setVisibility(View.VISIBLE);
                }
                else
                {
                    tv_j_ebp_error.setVisibility(View.INVISIBLE);

                    String name = et_j_ebp_name.getText().toString();
                    String number = et_j_ebp_number.getText().toString();
                    String password = et_j_ebp_password.getText().toString();

                    businessDb.updateBusiness(b.getEmail(), name, number, password);

                    startActivity(businessHomeIntent);
                }
            }
        });
    }

    public void businessHomeButtonClick()
    {
        btn_j_ebp_businessHome.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("Button Pressed:", "=====Business Home (EBP) Button Pressed=====");
                startActivity(businessHomeIntent);
            }
        });
    }

    public void seeOrdersButtonClick()
    {
        btn_j_ebp_seeOrders.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("Button Pressed:", "=====See Orders (EBP) Button Pressed=====");
            }
        });
    }
}