package com.example.buskothy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class NotificationBottomSheet extends BottomSheetDialogFragment {
    private TextView b1,b2,b3,b4,b5,b6,b7,b8;
    private Button backHome;
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_notification_layout, container,false);
        b1= (TextView)v.findViewById(R.id.confBusId);
        b2= (TextView)v.findViewById(R.id.confTicID);
        b3= (TextView)v.findViewById(R.id.confDateId);
        b4= (TextView)v.findViewById(R.id.confTimeID);
        b5= (TextView)v.findViewById(R.id.confSeatId);
        b6= (TextView)v.findViewById(R.id.confTakaID);
        b7= (TextView)v.findViewById(R.id.confMobileId);
        b8= (TextView)v.findViewById(R.id.confPickID);
        backHome = (Button) v.findViewById(R.id.backHomeId);
        Bundle bundle = getArguments();
        final String s1 = bundle.getString("Busnumber");
        final String s2 = bundle.getString("TicketID");
        final String s3 = bundle.getString("Date");
        final String s4 = bundle.getString("Time");
        final String s5 = bundle.getString("Seats");
        final String s6 = bundle.getString("Amount");
        final String s7 = bundle.getString("Mobile");
        final String s8 = bundle.getString("Pickplace");

        b1.setText(s1);
        b2.setText("101316"+s2);
        b3.setText(s3);
        b4.setText(s4);
        b5.setText(s5);
        b6.setText(s6);
        b7.setText(s7);
        b8.setText(s8);
        //button click
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MainActivity.class);
                startActivity(intent);
                dismiss();
            }
        });
        return v;
    }
}
