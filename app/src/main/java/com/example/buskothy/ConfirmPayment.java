package com.example.buskothy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Random;

public class ConfirmPayment extends AppCompatActivity {
    private TextView textMobile,textTotalfare,textViewBusnumber,textViewDate,textViewTime,textViewRoute,textViewSelectsits,textViewPick,textViewDesti;
    private Button buttonConfirmBooking;
    private EditText editTextPayPass;
    // DatabaseReference databaseRequest;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference booking = db.collection("ComfirmRequest");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_payment);
        //databaseRequest = FirebaseDatabase.getInstance().getReference("request");
        textViewBusnumber = findViewById(R.id.cticBusNumId);
        textViewDate = findViewById(R.id.cticDateId);
        textViewTime = findViewById(R.id.cticTimeId);
        textViewRoute = findViewById(R.id.cticRouteId);
        textMobile = findViewById(R.id.cticMobileId);
        textViewSelectsits = findViewById(R.id.cseatsId);
        textTotalfare = findViewById(R.id.cfarevaluId);
        textViewPick = findViewById(R.id.cstopPickId);
        textViewDesti  =findViewById(R.id.cstopDestId);
        editTextPayPass = findViewById(R.id.payPassId);
        buttonConfirmBooking = findViewById(R.id.confirmbookingId);
        //get intent
        textViewBusnumber.setText(getIntent().getExtras().getString("Busnumber"));
        textViewDate.setText(getIntent().getExtras().getString("Date"));
        textViewTime.setText(getIntent().getExtras().getString("Time"));
        textViewRoute.setText(getIntent().getExtras().getString("Route"));
        textMobile.setText(getIntent().getExtras().getString("Mobile"));
        textViewPick.setText(getIntent().getExtras().getString("Pick"));
        textViewDesti.setText(getIntent().getExtras().getString("Dest"));
        textViewSelectsits.setText(getIntent().getExtras().getString("Seats"));
        int totalseats = (int) Integer.valueOf(textViewSelectsits.getText().toString());
        int totaltk = Integer.valueOf(getIntent().getExtras().getString("Totalfare"));
        int valuetk = totalseats * totaltk;
        textTotalfare.setText(String.valueOf(valuetk));
        //
        buttonConfirmBooking.setEnabled(true);
        buttonConfirmBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passwor = editTextPayPass.getText().toString();
                String requestnumber = textViewBusnumber.getText().toString();
                String requestdate = textViewDate.getText().toString();
                String requesttime = textViewTime.getText().toString();
                String requestroute = textViewRoute.getText().toString();
                String requestmobile = textMobile.getText().toString();
                String requestpick = textViewPick.getText().toString();
                String requestdest = textViewDesti.getText().toString();
                int requestsit = Integer.parseInt(textViewSelectsits.getText().toString());
                int requestfare =Integer.parseInt(textTotalfare.getText().toString());

                //Bundle
                int min = 1;
                int max = 99;
                Random r = new Random();
                int textID = r.nextInt((max - min ) + 1)+ min;
                Bundle bundle = new Bundle();
                bundle.putString("Busnumber",requestnumber);
                bundle.putString("TicketID",String.valueOf(textID));
                bundle.putString("Date",requestdate);
                bundle.putString("Time",requesttime);
                bundle.putString("Seats",textViewSelectsits.getText().toString());
                bundle.putString("Amount",textTotalfare.getText().toString());
                bundle.putString("Mobile",requestmobile);
                bundle.putString("Pickplace",requestpick);

                if (!TextUtils.isEmpty(passwor)){
                    Request user_req = new Request(requestnumber,requestdate,requesttime,requestroute,requestmobile,requestpick,requestsit,requestfare);
                    booking.add(user_req);
                    NotificationBottomSheet notificationBottomSheet = new NotificationBottomSheet();
                    notificationBottomSheet.setArguments(bundle);
                    notificationBottomSheet.show(getSupportFragmentManager(),"Notification");
                    editTextPayPass.setText("");
                    buttonConfirmBooking.setEnabled(false);

                }else {
                    Toast.makeText(getApplicationContext(), "Password Invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
