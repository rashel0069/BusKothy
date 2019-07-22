package com.example.buskothy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SeeBusList extends AppCompatActivity {
    DatabaseReference databaseBusList;
    ListView listViewBus;

    List<Author> busList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_bus_list);
        databaseBusList = FirebaseDatabase.getInstance().getReference("author");

        //list view findviewbyId
        listViewBus = (ListView) findViewById(R.id.busListID);
        busList = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseBusList.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                busList.clear();
                for (DataSnapshot authorSnapshot : dataSnapshot.getChildren()){
                    Author author = authorSnapshot.getValue(Author.class);
                    busList.add(author);
                }
                BusList adapter = new BusList(SeeBusList.this, busList);
                listViewBus.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
