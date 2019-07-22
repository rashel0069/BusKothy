package com.example.buskothy;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentFragment extends Fragment {
    private ImageButton bkashButton,rocketButton,nogodButton;


    public PaymentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_payment, container, false);
        bkashButton = (ImageButton)v.findViewById(R.id.bkashID);
        rocketButton = (ImageButton)v.findViewById(R.id.rocketID);
        nogodButton = (ImageButton)v.findViewById(R.id.nogodID);
        bkashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),PaymentInterface.class);
                startActivity(intent);
            }
        });
        rocketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),PaymentInterface.class);
                startActivity(intent);
            }
        });
        nogodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),PaymentInterface.class);
                startActivity(intent);
            }
        });
        return v;
    }

}
