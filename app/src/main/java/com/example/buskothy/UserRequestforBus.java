package com.example.buskothy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import javax.annotation.Nullable;

public class UserRequestforBus extends AppCompatActivity {
    private TextView availableText,textUnitfare,textViewBusnumber,textViewDate,textViewTime,textViewRoute,textViewSelectsits,textViewPick,textViewDesti;
    private Button buttonSubs,buttonAdd,buttonRequestBooking;
    private EditText editTextMobile;
    public int data = 0;
    private ProgressDialog progressDialog;
   // DatabaseReference databaseRequest;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference availableseats = db.collection("ComfirmRequest");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_requestfor_bus);
        //databaseRequest = FirebaseDatabase.getInstance().getReference("request");
        textViewBusnumber = findViewById(R.id.ticBusNumId);
        textViewDate = findViewById(R.id.ticDateId);
        textViewTime = findViewById(R.id.ticTimeId);
        textViewRoute = findViewById(R.id.ticRouteId);
        editTextMobile = findViewById(R.id.ticMobileId);
        textViewSelectsits = findViewById(R.id.setnumberId);
        textUnitfare = findViewById(R.id.farevaluId);
        textViewPick = findViewById(R.id.stopPickId);
        textViewDesti  =findViewById(R.id.stopDestId);
        buttonSubs = findViewById(R.id.ticSubID);
        buttonAdd = findViewById(R.id.ticAddId);
        availableText = findViewById(R.id.availableSetsID);
        buttonRequestBooking = findViewById(R.id.requestforbookingId);

        //get extra intent
        textViewBusnumber.setText(getIntent().getExtras().getString("busname"));
        textViewDate.setText(getIntent().getExtras().getString("date"));
        textViewTime.setText(getIntent().getExtras().getString("time"));
        textViewRoute.setText(getIntent().getExtras().getString("route"));
        textViewPick.setText(getIntent().getExtras().getString("pickStop"));
        textViewDesti.setText(getIntent().getExtras().getString("destStop"));
        textUnitfare.setText(getIntent().getExtras().getString("farevalue"));

        selectSeat();
        buttonRequestBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // request
                //check route
                String vRouteCheck = getIntent().getExtras().getString("checkIT");
                String cRoute = textViewRoute.getText().toString();
                if (cRoute.equals(vRouteCheck)){
                    buttonRequestBooking.setEnabled(true);
                    if (!TextUtils.isEmpty(editTextMobile.getText().toString())){
                        sendRequest();
                    }else {
                        editTextMobile.setError("Enter Mobile Number");
                    }
                }else {
                    buttonRequestBooking.setEnabled(false);
                    Toast.makeText(UserRequestforBus.this, "You are select wrong Bus", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void selectSeat(){
        final Integer sets =Integer.valueOf( textViewSelectsits.getText().toString());
        //button enable and disable
        if (sets == 0){
            buttonSubs.setClickable(false);
        }
        if (sets == 5){
            buttonAdd.setClickable(false);
        }
        if (sets >= 0 && sets < 5){
            buttonAdd.setClickable(true);
            buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int setnumber = sets + 1;
                    textViewSelectsits.setText(String.valueOf(setnumber));
                    selectSeat();
                }
            });
        }
        if (sets >= 1 && sets !=0){
            buttonSubs.setClickable(true);
            buttonSubs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int setnumber = sets - 1;
                    textViewSelectsits.setText(String.valueOf(setnumber));
                    selectSeat();
                }
            });
        }

    }
    private void sendRequest(){
       String requestnumber = textViewBusnumber.getText().toString();
       String requestdate = textViewDate.getText().toString();
       String requesttime = textViewTime.getText().toString();
       String requestroute = textViewRoute.getText().toString();
       String requestmobile = editTextMobile.getText().toString();
       String requestpick = textViewPick.getText().toString();
       String requestdest = textViewDesti.getText().toString();
       String requestsit = textViewSelectsits.getText().toString();
       String requestfare = textUnitfare.getText().toString();

        String vnum =String.valueOf((requestmobile.charAt(0)));
        vnum = vnum + String.valueOf((requestmobile.charAt(1)))+ String.valueOf((requestmobile.charAt(2)));

      if (requestmobile.length() == 11){
          if (vnum.matches("013") || vnum.matches("014") || vnum.matches("015") || vnum.matches("016")
                  || vnum.matches("017")|| vnum.matches("018")|| vnum.matches("019") && Integer.valueOf(requestsit) != 0 ){
              Intent intent = new Intent(getApplicationContext(),ConfirmPayment.class);
              intent.putExtra("Busnumber",requestnumber);
              intent.putExtra("Date",requestdate);
              intent.putExtra("Time",requesttime);
              intent.putExtra("Route",requestroute);
              intent.putExtra("Mobile",requestmobile);
              intent.putExtra("Pick",requestpick);
              intent.putExtra("Dest",requestdest);
              intent.putExtra("Seats",requestsit);
              intent.putExtra("Totalfare",requestfare);
              startActivity(intent);
          /* Request user_req = new Request(requestnumber,requestdate,requesttime,requestroute,requestmobile,requestpick,requestsit);
           booking.add(user_req);*/

           /*String id = databaseRequest.push().getKey();
           Request request = new Request(id,requestnumber,requestdate,requesttime,requestroute,requestmobile,requestpick,requestsit);
           databaseRequest.child(id).setValue(request);*/
              Toast.makeText(this, "Request Send", Toast.LENGTH_SHORT).show();
              editTextMobile.setText("");
              textViewSelectsits.setText("0");

          }else {
              editTextMobile.setError("Enter Valid Phone Number");
          }
      }else {
          editTextMobile.setError("Must be contains 11 digits");
      }

    }

    @Override
    protected void onStart() {
        super.onStart();
        availableseats.whereEqualTo("requestBusNumber",textViewBusnumber.getText().toString())
                .whereEqualTo("requestDate",textViewDate.getText().toString())
                .whereEqualTo("requestTime",textViewTime.getText().toString())
                .whereEqualTo("requestRoute",String.valueOf(textViewRoute.getText()))
                .addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null){
                    return;
                }
                int data = 0;
                for (QueryDocumentSnapshot documentSnapshots : queryDocumentSnapshots){
                    Request request = documentSnapshots.toObject(Request.class);

                    int seat = request.getRequestSeat();

                    data = data + seat;
                }
                if (data==0){
                    data = 25;
                    availableText.setText(String.valueOf(data));
                    buttonRequestBooking.setEnabled(true);
                }else {
                    data = 25-data;
                    if (data < 25 && data > 0){
                        availableText.setText(String.valueOf(data));
                        buttonRequestBooking.setEnabled(true);
                    }else {
                        availableText.setText("0");
                        buttonRequestBooking.setEnabled(false);
                    }
                }

            }
        });
    }
}
