package com.example.buskothy;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;


/**
 * A simple {@link Fragment} subclass.
 */
public class DriverRequestFragment extends Fragment {
    private TextView requestDisplay;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference requestRef = db.collection("ComfirmRequest");

    public DriverRequestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_driver_request, container, false);
        requestDisplay = (TextView)v.findViewById(R.id.displayreqID);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        requestRef.orderBy("requestDate", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null){
                    return;
                }
                String data = "";
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    Request request = documentSnapshot.toObject(Request.class);
                    String getnumber = request.getRequestBusNumber();
                    String getdate = request.getRequestDate();
                    String gettime = request.getRequestTime();
                    String getroute = request.getRequestRoute();
                    String getmobile = request.getRequestMobile();
                    String gettpick = request.getRequestPick();
                    int getsit = request.getRequestSeat();

                    //all information
                   /* data += "Bus Number : "+ getnumber + "\nDate : " + getdate + "\nTime : "+ gettime + "\nRoute : "+ getroute + "\nMobile : "+ getmobile +"\nPick Point : "+ gettpick +"\nBooking Sits : "+ getsit+ "\n\n";*/

                    //only mobile,pickpoin,seats
                    data += "Date : " + getdate + "\nTime : "+ gettime + "\nMobile : "+ getmobile +"\nPick Point : "+ gettpick +"\nBooking Seats : "+ getsit+ "\n\n";
                }
                requestDisplay.setText(data);
            }
        });
    }
}
