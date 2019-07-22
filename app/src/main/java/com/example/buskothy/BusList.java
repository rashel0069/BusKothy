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

public class BusList extends ArrayAdapter<Author> {
    private Activity context;
    private List<Author> busList;

    public BusList(Activity context, List<Author> busList){
        super(context,R.layout.list_layout, busList);
        this.context = context;
        this.busList = busList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View lisViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView textViewBus = (TextView) lisViewItem.findViewById(R.id.tvBusID);
        TextView textViewpass = (TextView) lisViewItem.findViewById(R.id.tvBuspassID);

        Author author = busList.get(position);

        textViewBus.setText(author.getAuthorbusnumber());
        textViewpass.setText(author.getAuthorpassword());
        return lisViewItem;
    }
}
