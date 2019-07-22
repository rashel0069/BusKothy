package com.example.buskothy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserLogin extends AppCompatActivity {

    private CheckBox checkBox;
    private EditText numberPhone;
    private Button verify;
    boolean flag = false;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        mAuth = FirebaseAuth.getInstance();
        checkBox = (CheckBox) findViewById(R.id.checkboxId);
        numberPhone = (EditText) findViewById(R.id.mobilenumberID);
        verify = (Button) findViewById(R.id.verifyID);



        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobile = numberPhone.getText().toString().trim();
                Intent intent = new Intent(UserLogin.this,VerifyCode.class);
                intent.putExtra("mobile",mobile);
                startActivity(intent);
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                String mobile = numberPhone.getText().toString().trim();
                int legnth = numberPhone.length();
           if (b){
               flag = true;
               verify.setEnabled(!mobile.isEmpty() && legnth == 11 && flag == true);
           }else {

               verify.setEnabled(false);
               flag = false;

           }
           flag = false;
            }
        });



        numberPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String mobile = numberPhone.getText().toString().trim();
                int legnth = numberPhone.length();
                if (checkBox.isChecked()){
                    checkBox.setChecked(false);
                }
                verify.setEnabled(!mobile.isEmpty() && legnth == 11 && flag == true);


            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });


    }
    protected void onStart()
    {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null)
        {
            SendUserToMainActivity();
        }
    }

    private void SendUserToMainActivity(){
        Intent intent = new Intent(UserLogin.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();

    }
}
