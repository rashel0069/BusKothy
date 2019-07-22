package com.example.buskothy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

public class ChoseLogin extends AppCompatActivity {

    private ImageButton userButton,authorButton;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choselogin);
        mAuth = FirebaseAuth.getInstance();

        userButton = (ImageButton) findViewById(R.id.userchoseId);
        authorButton = (ImageButton) findViewById(R.id.authorchoseID);

        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(ChoseLogin.this,UserLogin.class);
                startActivity(intent);
            }
        });

        authorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoseLogin.this,ChoseAuthor.class);
                startActivity(intent);
            }
        });
    }
}
