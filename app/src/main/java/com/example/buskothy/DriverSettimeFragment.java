package com.example.buskothy;


import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class DriverSettimeFragment extends Fragment {
    private ImageButton clockbutton;
    TimePickerDialog timePickerDialog;
    private Button nextbutton;
    private TextView textsetTime,textViewDate,textViewbusName;
    private Spinner textspinner;
    Calendar calendar;
    int currentHour;
    int currentMinute;
    String amPm;
    //cloud reference
   // private FirebaseFirestore db = FirebaseFirestore.getInstance();
    //private CollectionReference scheduleRef = db.collection("BusSchedule");
   DatabaseReference databaseBusSchedule;

    public DriverSettimeFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_driver_settime, container, false);

        databaseBusSchedule = FirebaseDatabase.getInstance().getReference("busSchedule");
        clockbutton = v.findViewById(R.id.clockid);
        textsetTime = v.findViewById(R.id.timepic);
        textViewDate = v.findViewById(R.id.dateID);
        textViewbusName = v.findViewById(R.id.busNameID);
        nextbutton = v.findViewById(R.id.gonext);
        nextbutton.setEnabled(true);

        Bundle b2 = getArguments();
        String busnumber = b2.getString("Busnumber");
        textViewbusName.setText(busnumber);
        //for pick date and time
        calendar = Calendar.getInstance();
        currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        currentMinute = calendar.get(Calendar.MINUTE);
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        textViewDate.setText(currentDate);

        //spinner for select rout
        textspinner = v.findViewById(R.id.spinrerroute);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),R.array.routes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        textspinner.setAdapter(adapter);

        textspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String routes = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        clockbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        if (hourOfDay >= 12){
                            amPm = "PM";
                        }else {
                            amPm = "AM";
                        }
                        textsetTime.setText(String.format("%02d:%02d", hourOfDay, minute)+" " + amPm);
                    }
                }, currentHour, currentMinute, false);
                timePickerDialog.show();
            }
        });
        //next Button
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addbusSchdule();
                /*Intent intent1 = new Intent(getContext(),DriverLogin.class);
                startActivity(intent1);*/
            }
        });

        return v;
    }
    private void addbusSchdule(){
        String busNumber = textViewbusName.getText().toString().trim();
        String todayDate = textViewDate.getText().toString().trim();
        String busroute = textspinner.getSelectedItem().toString().trim();
        String busTime = textsetTime.getText().toString().trim();

        if (!TextUtils.isEmpty(busTime)){
            String busId = databaseBusSchedule.push().getKey();
            BusSchedule busSchedule = new BusSchedule(busId, busNumber, todayDate, busroute, busTime);
            databaseBusSchedule.child(busId).setValue(busSchedule);
           /*BusSchedule setSchedule  = new BusSchedule(busNumber,todayDate,busroute,busTime);
           scheduleRef.add(setSchedule);*/

            Toast.makeText(getContext(), "Schedule set for today", Toast.LENGTH_SHORT).show();
            textsetTime.setText("");
            nextbutton.setEnabled(false);

        }else {
            Toast.makeText(getContext(), "Please select all items", Toast.LENGTH_SHORT).show();
            nextbutton.setEnabled(true);
        }

    }
}
