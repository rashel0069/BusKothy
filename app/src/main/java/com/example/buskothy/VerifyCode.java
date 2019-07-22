package com.example.buskothy;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class VerifyCode extends AppCompatActivity {
    private static final long COUNTDOWN_IN_MILLIS = 10000;
    private static final String KEY_MILLIS_LEFT = "KeyMillisLeft";
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    private ProgressBar verifyprogr;
    private TextView textViewCountDown;
    private EditText verifycode;
    private Button signIn,resend;
    String codeSend,phonenumber;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_code);
        mAuth = FirebaseAuth.getInstance();
        verifyprogr = findViewById(R.id.verifyprogressBar);
        textViewCountDown = findViewById(R.id.text_view_countdown);
        verifycode = (EditText) findViewById(R.id.verCodeID);
        signIn = (Button) findViewById(R.id.signinID);
        resend = (Button) findViewById(R.id.resendID);

        final Intent intent = getIntent();
        phonenumber = intent.getStringExtra("mobile");
        sendVerificationCode();
        timeLeftInMillis = COUNTDOWN_IN_MILLIS;
        startCountDown();

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String code = verifycode.getText().toString().trim();

                if (!code.isEmpty() && code.length() == 6){
                    verifySigninCode();
                }else {
                    Toast.makeText(VerifyCode.this, "Enter verification code", Toast.LENGTH_SHORT).show();
                }
            }
        });

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendVerificationCode();
                timeLeftInMillis = COUNTDOWN_IN_MILLIS;
                startCountDown();
                resend.setVisibility(View.GONE);
            }
        });
    }
    private void startCountDown(){
        countDownTimer = new CountDownTimer(timeLeftInMillis,1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMillis = l;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                resend.setVisibility(View.VISIBLE);

            }
        }.start();
    }
    private void updateCountDownText(){

        int minutes = (int)(timeLeftInMillis / 1000) / 60 ;
        int seconds = (int)(timeLeftInMillis / 1000) % 60 ;

        String timeFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
        textViewCountDown.setText(timeFormatted);
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
        Intent intent = new Intent(VerifyCode.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();

    }
    private void sendVerificationCode(){

        PhoneAuthProvider.getInstance().verifyPhoneNumber("+88"+
                phonenumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks

    }
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {

        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            codeSend = s;

        }
    };

    private void verifySigninCode(){
        verifyprogr.setVisibility(View.VISIBLE);
        String code = verifycode.getText().toString();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSend, code);
        signInWithPhoneAuthCredential(credential);

    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            verifyprogr.setVisibility(View.GONE);
                            SendUserToMainActivity();
                            finish();

                        } else {
                            verifyprogr.setVisibility(View.GONE);
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(VerifyCode.this, "Invalid Verification code", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
}
