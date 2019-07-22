package com.example.buskothy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class ChoseAuthor extends AppCompatActivity {
    ImageButton driver,owner,admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_author);
        driver = findViewById(R.id.driverId);
        owner = findViewById(R.id.ownerID);
        admin = findViewById(R.id.adminID);


        driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoseAuthor.this,DriverInterface.class);
                startActivity(intent);
            }
        });
        owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoseAuthor.this,BusOwner.class);
                startActivity(intent);
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoseAuthor.this,AdminInterface.class);
                startActivity(intent);
            }
        });
    }
}
