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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class ExampleBottomSheetDialog extends BottomSheetDialogFragment {
    private ImageButton searchBusList;
    private TextView pick,destination,cost;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_layout, container, false);
        searchBusList = (ImageButton) v.findViewById(R.id.searchBusID);
        pick = (TextView)v.findViewById(R.id.picLocID);
        destination = (TextView)v.findViewById(R.id.destLocId);
        cost = (TextView)v.findViewById(R.id.costID); 
        Bundle bundle = getArguments();
        final String pickloc = bundle.getString("PickLocation");
        final String destiloc = bundle.getString("DestinationLocation");
        final String selectedRoute = bundle.getString("SelectedRoute");

        //cost list
        String[] loc1 = getResources().getStringArray(R.array.m12toazp1);
        String[] loc2 = getResources().getStringArray(R.array.m12toazp2);
        String[] loc3 = getResources().getStringArray(R.array.m1toazp1);
        String[] loc4 = getResources().getStringArray(R.array.m1toazp2);
        String[] loc5 = getResources().getStringArray(R.array.mohtoabd1);
        String[] loc6 = getResources().getStringArray(R.array.mohtoabd2);
        String[] loc7 = getResources().getStringArray(R.array.m12tojat2);
        String[] loc8 = getResources().getStringArray(R.array.m12tojat3);
        String[] loc9 = getResources().getStringArray(R.array.chitokom1);
        String[] loc10 = getResources().getStringArray(R.array.chitokom2);
        //list chart
        List <String> list1 = Arrays.asList(loc1);
        List <String> list2 = Arrays.asList(loc2);
        List <String> list3 = Arrays.asList(loc3);
        List <String> list4 = Arrays.asList(loc4);
        List <String> list5 = Arrays.asList(loc5);
        List <String> list6 = Arrays.asList(loc6);
        List <String> list7 = Arrays.asList(loc7);
        List <String> list8 = Arrays.asList(loc8);
        List <String> list9 = Arrays.asList(loc9);
        List <String> list10 = Arrays.asList(loc10);

        if ((list1.contains(pickloc) && list1.contains(destiloc)) || (list2.contains(pickloc) && list2.contains(destiloc))){
            cost.setText("25");
        }else if ((list1.contains(pickloc) && list2.contains(destiloc)) || (list2.contains(pickloc) && list1.contains(destiloc))){
            cost.setText("50");
        }else if ((list3.contains(pickloc) && list3.contains(destiloc)) || (list4.contains(pickloc) && list4.contains(destiloc))){
            cost.setText("25");
        }else if ((list3.contains(pickloc) && list4.contains(destiloc)) || (list4.contains(pickloc) && list3.contains(destiloc))){
            cost.setText("50");
        }else if (list5.contains(pickloc) && list5.contains(destiloc)){
            cost.setText("40");
        }else if (list6.contains(pickloc) && list6.contains(destiloc)){
            cost.setText("50");
        }else if ((list5.contains(pickloc) && list6.contains(destiloc)) || list6.contains(pickloc) && list5.contains(destiloc)) {
            cost.setText("90");
        }else if ((list1.contains(pickloc) && list1.contains(destiloc)) ||(list7.contains(pickloc) && list7.contains(destiloc)) || (list8.contains(pickloc) && list8.contains(destiloc))){
            cost.setText("25");
        }else if ((list1.contains(pickloc) && list7.contains(destiloc)) ||(list7.contains(pickloc) && list1.contains(destiloc)) || (list7.contains(pickloc) && list8.contains(destiloc)) || (list8.contains(pickloc) && list7.contains(destiloc))){
            cost.setText("50");
        }else if ((list1.contains(pickloc) && list8.contains(destiloc)) ||(list8.contains(pickloc) && list1.contains(destiloc))){
            cost.setText("75");
        }else if ((list9.contains(pickloc) && list9.contains(destiloc)) || (list10.contains(pickloc) && list10.contains(destiloc))){
            cost.setText("35");
        }else if ((list9.contains(pickloc) && list10.contains(destiloc)) || (list10.contains(pickloc) && list9.contains(destiloc))){
            cost.setText("70");
        }
        else {
            Toast.makeText(getContext(), "Error to find Cost!", Toast.LENGTH_SHORT).show();
            cost.setText("");
        }

        pick.setText(pickloc);
        destination.setText(destiloc);

        searchBusList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fare = cost.getText().toString();
                Intent intent = new Intent(getActivity(),UserBusList.class);
                //Intent intent = new Intent(getActivity(),DatabaseBusList.class);
                intent.putExtra("PickBus",pickloc);
                intent.putExtra("DestBus",destiloc);
                intent.putExtra("Fare",fare);
                intent.putExtra("Route",selectedRoute);
                startActivity(intent);
                dismiss();
            }
        });
    return v;
    }
}
