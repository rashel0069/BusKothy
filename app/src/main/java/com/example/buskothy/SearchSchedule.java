package com.example.buskothy;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class SearchSchedule extends ArrayAdapter<BusSchedule> {
    private Activity context;
    private List<BusSchedule> busScheduleList;
    public SearchSchedule (Activity context, List<BusSchedule>busScheduleList){
        super(context, R.layout.list_schedule_bus,busScheduleList);
        this.context = context;
        this.busScheduleList = busScheduleList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listviewItems = inflater.inflate(R.layout.list_schedule_bus,null,true);

        TextView textViewBusname = listviewItems.findViewById(R.id.textViewbusnameID);
        TextView textViewBustime = listviewItems.findViewById(R.id.textViewbusScheduleId);
        TextView textViewBusroute = listviewItems.findViewById(R.id.textViewBusRouteID);
        BusSchedule busSchedule = busScheduleList.get(position);

        textViewBusname.setText(busSchedule.getTodaybusNumber());
        textViewBustime.setText(busSchedule.getTodaybusTime());
        textViewBusroute.setText(busSchedule.getTodaybusRoute());
        return listviewItems;
    }
}
