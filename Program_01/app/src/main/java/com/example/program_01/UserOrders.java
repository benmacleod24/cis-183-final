package com.example.program_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;

public class UserOrders extends AppCompatActivity {

    ImageView btn_j_menu_orders;
    ImageView btn_j_menu_home;
    ImageView btn_j_menu_profile;
    ImageView btn_j_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_orders);

        setupElements();
    }

    private void setupElements()
    {
        btn_j_back = findViewById(R.id.btn_v_uo_back);
        btn_j_menu_home = findViewById(R.id.btn_v_uo_home);
        btn_j_menu_orders = findViewById(R.id.btn_v_uo_order);
        btn_j_menu_profile = findViewById(R.id.btn_v_uo_profile);

        onBackClick();
        setupMenuClicks();
    }

    private void onBackClick()
    {
        btn_j_back.setOnClickListener(view -> {
            Intent userHomeIntent = new Intent(UserOrders.this, UserHome.class);
            startActivity(userHomeIntent);
        });
    }

    private void setupMenuClicks()
    {
        btn_j_menu_home.setOnClickListener(view -> {
            Intent userHomeIntent = new Intent(UserOrders.this, UserHome.class);
            startActivity(userHomeIntent);
        });

        btn_j_menu_profile.setOnClickListener(view -> {
            Intent profileIntent = new Intent(UserOrders.this, UserProfile.class);
            startActivity(profileIntent);
        });
    }
}