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

public class RequestList extends ArrayAdapter<Request> {
    private Activity context;
    private List<Request> requests;

    public RequestList(Activity context, List<Request> requests){
        super(context,R.layout.seat_request_list, requests);
        this.context = context;
        this.requests = requests;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View lisViewItem = inflater.inflate(R.layout.seat_request_list, null, true);

        TextView textViewBus = (TextView) lisViewItem.findViewById(R.id.requestBusnumberId);
        TextView textViewtime = (TextView) lisViewItem.findViewById(R.id.requestTimeId);
        TextView textViewpick = (TextView) lisViewItem.findViewById(R.id.requestPickId);
        TextView textViewseat = (TextView) lisViewItem.findViewById(R.id.requestseatNumberId);

        Request request = requests.get(position);

        textViewBus.setText(request.getRequestBusNumber());
        textViewtime.setText(request.getRequestTime());
        textViewpick.setText(request.getRequestPick());
        textViewseat.setText(request.getRequestSeat());
        return lisViewItem;
    }
}
