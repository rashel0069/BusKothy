package com.example.buskothy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class Admin extends AppCompatActivity {
    ImageButton reglistbutton,buslistButton,owninfobutton,editinfobutton,sendmessbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        reglistbutton = findViewById(R.id.reglistId);
        buslistButton = findViewById(R.id.buslistbuttonId);
        owninfobutton = findViewById(R.id.ownerinfobuttonId);
        editinfobutton = findViewById(R.id.editinfobuttonId);
        sendmessbutton = findViewById(R.id.sendmessbuttonId);

        reglistbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin.this,Registrationform.class);
                startActivity(intent);
            }
        });
        buslistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin.this,SeeBusList.class);
                startActivity(intent);
            }
        });
    }
}

