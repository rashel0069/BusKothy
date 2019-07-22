package com.example.buskothy;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class DriverMoreFragment extends Fragment {
    String arrayName[]={"Help","Notifications","Servicing Point","Emergency Contracts","Parking Location"};
    private CircleMenu circleMenu;


    public DriverMoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_driver_more, container, false);
        circleMenu = (CircleMenu)v.findViewById(R.id.circle_menu);
        circleMenu.setMainMenu(Color.parseColor("#4A6572"),R.drawable.img1,R.drawable.img2)
                .addSubMenu(Color.parseColor("#80cbc4"),R.drawable.ic3)
                .addSubMenu(Color.parseColor("#80cbc4"),R.drawable.ic4)
                .addSubMenu(Color.parseColor("#80cbc4"),R.drawable.ic7)
                .addSubMenu(Color.parseColor("#80cbc4"),R.drawable.ic6)
                .addSubMenu(Color.parseColor("#80cbc4"),R.drawable.ic5).setOnMenuSelectedListener(new OnMenuSelectedListener() {
            @Override
            public void onMenuSelected(int i) {
                Toast.makeText(getContext(), "You select "+ arrayName[i], Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }
}
