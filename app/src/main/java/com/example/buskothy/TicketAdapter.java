package com.example.buskothy;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class TicketAdapter extends FirestoreRecyclerAdapter <Request, TicketAdapter.TicketHolder> {

    public TicketAdapter(@NonNull FirestoreRecyclerOptions<Request> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull TicketHolder holder, int position, @NonNull Request model) {
        holder.textViewbusnumber.setText(model.getRequestBusNumber());
        holder.textViewtime.setText(model.getRequestTime());
        holder.textViewmobile.setText(model.getRequestMobile());
        holder.textViewseats.setText(String.valueOf(model.getRequestSeat()));
        holder.textViewpickpoin.setText(model.getRequestPick());

    }

    @NonNull
    @Override
    public TicketHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,parent,false);
        return new TicketHolder(v);
    }

    class TicketHolder extends RecyclerView.ViewHolder{
        TextView textViewbusnumber;
        TextView textViewtime;
        TextView textViewmobile;
        TextView textViewseats;
        TextView textViewpickpoin;

        public TicketHolder(@NonNull View itemView) {
            super(itemView);
            textViewbusnumber = itemView.findViewById(R.id.busnumberId);
            textViewtime = itemView.findViewById(R.id.datetimeId);
            textViewmobile = itemView.findViewById(R.id.mobilenumberId);
            textViewseats = itemView.findViewById(R.id.seatsnumberId);
            textViewpickpoin = itemView.findViewById(R.id.buspointId);

        }
    }
}

