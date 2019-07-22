package com.example.buskothy;

import android.content.Intent;
import android.graphics.Path;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserBusList extends AppCompatActivity {
    DatabaseReference databaseBusSchedule;
    private ListView listViewbuslist;
    List<BusSchedule> busSchedulelists;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_bus_list);
        listViewbuslist = findViewById(R.id.fraglistViewID);
        progressBar = findViewById(R.id.progressBarId);
        databaseBusSchedule = FirebaseDatabase.getInstance().getReference("busSchedule");
        busSchedulelists = new ArrayList<>();
        progressBar.setVisibility(View.VISIBLE);

        final String checkRoute = getIntent().getExtras().getString("Route");
      databaseBusSchedule.orderByChild("todaybusRoute")
              .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                busSchedulelists.clear();
                for (DataSnapshot busSearchSnapshort : dataSnapshot.getChildren()){
                    BusSchedule busSchedule = busSearchSnapshort.getValue(BusSchedule.class);
                    busSchedulelists.add(busSchedule);
                }
                SearchSchedule adapter = new SearchSchedule(UserBusList.this,busSchedulelists);
                listViewbuslist.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressBar.setVisibility(View.GONE);
            }
        });
        listViewbuslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String pickBustop = getIntent().getExtras().getString("PickBus");
                String destinationBusstop = getIntent().getExtras().getString("DestBus");
                String fare = getIntent().getExtras().getString("Fare");
                String nameBus = busSchedulelists.get(position).todaybusNumber;
                String dateBus = busSchedulelists.get(position).todaybusDate;
                String timeBus = busSchedulelists.get(position).todaybusTime;
                String routeBus = busSchedulelists.get(position).todaybusRoute;

                Intent intent = new Intent(getApplicationContext(),UserRequestforBus.class);
                intent.putExtra("busname",nameBus);
                intent.putExtra("date",dateBus);
                intent.putExtra("time",timeBus);
                intent.putExtra("route",routeBus);
                intent.putExtra("pickStop",pickBustop);
                intent.putExtra("destStop",destinationBusstop);
                intent.putExtra("farevalue",fare);
                intent.putExtra("checkIT",checkRoute);
                startActivity(intent);
            }
        });
    }
}
