package com.example.buskothy;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import Modules.DirectionFinder;
import Modules.DirectionFinderListener;
import Modules.Route;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements OnMapReadyCallback, DirectionFinderListener {
    private GoogleMap mMap;
    Button openBottomSheet;
    AutoCompleteTextView pickBusStop,destinationBusStop;
    Spinner selectroute;
    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarker = new ArrayList<>();
    private List<Polyline> polyLinePaths = new ArrayList<>();
    private ProgressDialog progressDialog;
    private static final int Request_call = 1;
    private FloatingActionButton floatingActionButton;



    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_home, container, false);
        //SupportMapFragment mapFragment = (SupportMapFragment)getFragmentManager().findFragmentById(R.id.usermap);
        //mapFragment.getMapAsync(this);
        openBottomSheet = (Button)v.findViewById(R.id.openBottomId);
        pickBusStop = (AutoCompleteTextView)v.findViewById(R.id.pickBusID);
        destinationBusStop = (AutoCompleteTextView)v.findViewById(R.id.destinationBusId);
        selectroute = (Spinner)v.findViewById(R.id.routeSpinerID);
        floatingActionButton = (FloatingActionButton)v.findViewById(R.id.emergencycallId);


        //make call
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               emerCall();
            }
        });
        //select route
        selectroute.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             int itemSelect = selectroute.getSelectedItemPosition();
                if (itemSelect != 0){
                    pickBusStop.setEnabled(true);
                    destinationBusStop.setEnabled(true);
                }else {
                    pickBusStop.setEnabled(false);
                    destinationBusStop.setEnabled(false);
                }

                if (itemSelect == 1 || itemSelect == 2){
                    //Get bus rout by using list adapter
                    String [] route = getResources().getStringArray(R.array.mirpur12_to_azimpur);
                    ArrayAdapter adapter = new ArrayAdapter(getContext(),android.R.layout.simple_expandable_list_item_1,route);
                    pickBusStop.setAdapter(adapter);
                    destinationBusStop.setAdapter(adapter);
                }
                if (itemSelect == 3 || itemSelect == 4){
                    String [] route = getResources().getStringArray(R.array.mirpur1_to_azimpur);
                    ArrayAdapter adapter = new ArrayAdapter(getContext(),android.R.layout.simple_expandable_list_item_1,route);
                    pickBusStop.setAdapter(adapter);
                    destinationBusStop.setAdapter(adapter);

                }
                if (itemSelect == 5 || itemSelect == 6){
                    String [] route = getResources().getStringArray(R.array.mohammadpur_to_abdullahpur);
                    ArrayAdapter adapter = new ArrayAdapter(getContext(),android.R.layout.simple_expandable_list_item_1,route);
                    pickBusStop.setAdapter(adapter);
                    destinationBusStop.setAdapter(adapter);
                }
                if (itemSelect == 7 || itemSelect == 8){
                    String [] route = getResources().getStringArray(R.array.mirpur12_to_jatrabari);
                    ArrayAdapter adapter = new ArrayAdapter(getContext(),android.R.layout.simple_expandable_list_item_1,route);
                    pickBusStop.setAdapter(adapter);
                    destinationBusStop.setAdapter(adapter);

                }
                if (itemSelect == 9 || itemSelect == 10){
                    String [] route = getResources().getStringArray(R.array.chiriyakhana_to_komlapuri);
                    ArrayAdapter adapter = new ArrayAdapter(getContext(),android.R.layout.simple_expandable_list_item_1,route);
                    pickBusStop.setAdapter(adapter);
                    destinationBusStop.setAdapter(adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        openBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = pickBusStop.getText().toString().trim();
                String s2 = destinationBusStop.getText().toString().trim();
                String s3 = selectroute.getSelectedItem().toString();
                Bundle bundle = new Bundle();
                bundle.putString("PickLocation",s1);
                bundle.putString("DestinationLocation",s2);
                bundle.putString("SelectedRoute",s3);

                if (!s1.isEmpty() && !s2.isEmpty()){
                    //sed request for draw path
                        sendReqest();
                }else {
                    Toast.makeText(getContext(), "Please select Pick & Destination Bus Stop", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return v;

    }

    private void emerCall() {
        String number = "999";
        if (ContextCompat.checkSelfPermission(getContext(),Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE},Request_call);
        }else {
            String dial = "tel:" + number;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == Request_call){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                emerCall();
            }else {
                Toast.makeText(getContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void sendReqest(){
        if (pickBusStop.getText().toString().equals(destinationBusStop.getText().toString())){
            destinationBusStop.setError("Change Pick or Destination place");
            return;
        }else {
            String origin = pickBusStop.getText().toString();
            String destination = destinationBusStop.getText().toString();
            if (origin.isEmpty()) {
                Toast.makeText(getContext(), "Please enter origin!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (destination.isEmpty()){
                Toast.makeText(getContext(), "Please enter destination!", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                new DirectionFinder(this, origin, destination).execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.usermap);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        int reqCode = 1;
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, reqCode);
            return;
        }
        LatLng GUB = new LatLng(23.786920, 90.377537);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(GUB,15));
        mMap.setMyLocationEnabled(true);
        // AIzaSyAMNipkbZIQxrCST6MabE_uNvlA2IC1Tfc
    }

    @Override
    public void onDirectionFinderStart() {
        progressDialog = ProgressDialog.show(getContext(), "Please wait", "Finding direction", true);
        if (originMarkers != null) {
            for (Marker marker : originMarkers) {
                marker.remove();
            }
        }
        if (destinationMarker != null) {
            for (Marker marker : destinationMarker) {
                marker.remove();
            }
        }
        if (polyLinePaths != null) {
            for (Polyline polylinePath : polyLinePaths) {
                polylinePath.remove();
            }
        }
    }

    @Override
    public void onDirectionFinderSuccess(List<Route> routes) {
        progressDialog.dismiss();
        polyLinePaths = new ArrayList<>();
        originMarkers = new ArrayList<>();
        destinationMarker = new ArrayList<>();

        for (Route route : routes) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route.startLocation, 16));
            //((TextView).findViewById(R.id.textViewDistance)).setText(route.distance.text);
            //((TextView)v.findViewById(R.id.textViewTime)).setText(route.duration.text);

            originMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.start_blue3))
                    .title(route.startAddress)
                    .position(route.startLocation)));

            destinationMarker.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.end_red3))
                    .title(route.endAddress)
                    .position(route.endLocation)));

            PolylineOptions polylineOptions = new PolylineOptions()
                    .geodesic(true)
                    .color(Color.BLUE)
                    .width(10);

            for (int i = 0; i < route.points.size(); i++) {
                polylineOptions.add(route.points.get(i));
            }

            polyLinePaths.add(mMap.addPolyline(polylineOptions));
        }
        String s1 = pickBusStop.getText().toString().trim();
        String s2 = destinationBusStop.getText().toString().trim();
        String s3 = selectroute.getSelectedItem().toString();
        Bundle bundle = new Bundle();
        bundle.putString("PickLocation",s1);
        bundle.putString("DestinationLocation",s2);
        bundle.putString("SelectedRoute",s3);
        ExampleBottomSheetDialog bottomSheet = new ExampleBottomSheetDialog();
        bottomSheet.setArguments(bundle);
        bottomSheet.show(getFragmentManager(),"exampleBottomSheet");
    }
}
