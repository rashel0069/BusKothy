package com.example.buskothy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DriverInterface extends AppCompatActivity {
    private Button driverlog;
    ProgressBar progressBar;
    private EditText logDriver,driverPass;
    public boolean flag=false;
    DatabaseReference busDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_interface);
        logDriver = findViewById(R.id.driverLogin);
        driverPass = findViewById(R.id.driverPassword);
        driverlog = findViewById(R.id.driverLogId);
        progressBar = findViewById(R.id.driverprogressbarId);

        driverlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = false;
                progressBar.setVisibility(View.VISIBLE);
                String driverlog = driverPass.getText().toString().trim();
                String driverlogpass = driverPass.getText().toString().trim();
                if (!driverlog.isEmpty() && !driverlogpass.isEmpty()){
                    busDB = FirebaseDatabase.getInstance().getReference("author");
                    final Query query = FirebaseDatabase.getInstance().getReference("author").orderByChild("authorbusnumber")
                            .equalTo(logDriver.getText().toString().trim());
                    query.addListenerForSingleValueEvent(valueEventListener);
                }else {
                    progressBar.setVisibility(View.GONE);
                    logDriver.setError("Enter Valid ID");
                    driverPass.setError("Enter Valid Password");
                    logDriver.setText("");
                    driverPass.setText("");

                }
            }
        });

    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()){
                progressBar.setVisibility(View.GONE);
                if (flag!=false){
                    String busName = logDriver.getText().toString().trim();
                    Intent intent = new Intent(DriverInterface.this,DriverLogin.class);//booking
                    intent.putExtra("busName",busName);
                    startActivity(intent);
                    flag = false;
                    finish();

                }else {
                    NewQuery();
                }


            }else {
                progressBar.setVisibility(View.GONE);
                logDriver.setError("Enter Valid ID");
                driverPass.setError("Enter Valid Password");
                logDriver.setText("");
                driverPass.setText("");

            }

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {


        }
    };
    protected void NewQuery(){
        final Query query1 = FirebaseDatabase.getInstance().getReference("author").orderByChild("authorpassword")
                .equalTo(driverPass.getText().toString().trim());
        flag = true;
        query1.addListenerForSingleValueEvent(valueEventListener);
    }
}
