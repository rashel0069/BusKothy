package com.example.buskothy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class DriverLogin extends AppCompatActivity {
    private TextView currentBusName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login);
        currentBusName = findViewById(R.id.CBusID);
        final Intent intent = getIntent();
        currentBusName.setText(intent.getStringExtra("busName"));

        BottomNavigationView drivernavView = findViewById(R.id.driverBottomNav);
        Bundle b1 = new Bundle();
        b1.putString("Busnumber",currentBusName.getText().toString());

       final DriverHomeFragment driverHomeFragment = new DriverHomeFragment();
       final DriverRequestFragment driverRequestFragment = new DriverRequestFragment();
       final DriverSettimeFragment driverSettimeFragment = new DriverSettimeFragment();
       driverSettimeFragment.setArguments(b1);
       final DriverMoreFragment driverMoreFragment = new DriverMoreFragment();

        drivernavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.itemdriverHome){
                    setFragment(driverHomeFragment);
                    return true;
                }else if (id == R.id.itemdriveruserreq){
                    setFragment(driverRequestFragment);
                    return true;
                }else if (id == R.id.itemdrivertimeset){
                    setFragment(driverSettimeFragment);
                    return true;
                }else if (id == R.id.itemdrivermore){
                    setFragment(driverMoreFragment);
                    return true;
                }
                return false;
            }
        });
        drivernavView.setSelectedItemId(R.id.itemdriverHome);
    }

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.driverframe,fragment);
        fragmentTransaction.commit();
    }
}
