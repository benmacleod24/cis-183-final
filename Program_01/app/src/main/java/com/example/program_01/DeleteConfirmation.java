package com.example.program_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.program_01.Controllers.Session;
import com.example.program_01.Database.BusinessDatabase;
import com.example.program_01.Database.ServiceDatabase;
import com.example.program_01.Models.Business;
import com.example.program_01.Models.Service;

public class DeleteConfirmation extends AppCompatActivity
{
    BusinessDatabase bizDb;
    ServiceDatabase servDb;
    Intent businessHomeIntent;
    Intent userHomeIntent;
    Intent mainActivityIntent;
    Button btn_j_dc_yes;
    Button btn_j_dc_no;
    String cameFrom;
    Service servToDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_confirmation);

        btn_j_dc_yes = findViewById(R.id.btn_v_dc_yes);
        btn_j_dc_no = findViewById(R.id.btn_v_dc_no);
        bizDb = new BusinessDatabase(this);
        servDb = new ServiceDatabase(this);
        businessHomeIntent = new Intent(DeleteConfirmation.this, businessHome.class);
        userHomeIntent = new Intent(DeleteConfirmation.this, UserHome.class);
        mainActivityIntent = new Intent(DeleteConfirmation.this, MainActivity.class);

        Intent origin = getIntent();
        cameFrom = origin.getStringExtra("cameFrom");

        if (cameFrom.equals("editServiceIntent")) //SERIVCE
        {
            servToDelete = (Service) origin.getSerializableExtra("serviceToDelete");
        }


        yesButtonClick();
        noButtonClick();
    }

    private void yesButtonClick()
    {
        btn_j_dc_yes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("Button Press", "DELETING");
                //DELETE WHATEVER IT IS

                if (cameFrom.equals("editBusinessProfile"))
                {
                    bizDb.deleteBusiness(Session.getBusiness().getEmail());
                    startActivity(mainActivityIntent);
                }
                else if (cameFrom.equals("editServiceIntent"))
                {
                    servDb.deleteService(servToDelete.getServiceId());
                    startActivity(businessHomeIntent);
                }
                //else if came from editing user

            }
        });
    }

    private void noButtonClick()
    {
        btn_j_dc_no.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("Button Press", "NOT Deleting (pressed no)");
                if (cameFrom.equals("editBusinessProfile") || cameFrom.equals("editServiceIntent"))
                {
                    startActivity(businessHomeIntent);
                }
                //Else if from user
            }
        });
    }
}