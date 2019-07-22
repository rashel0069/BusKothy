package com.example.buskothy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminInterface extends AppCompatActivity {

    ProgressBar progressBar;
    private EditText editTextAdminemail,editTextadminpass;
    private Button adminLogin;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_interface);
        mAuth = FirebaseAuth.getInstance();

        editTextAdminemail = findViewById(R.id.adminLoginId);
        editTextadminpass = findViewById(R.id.adminPasswordId);
        progressBar = findViewById(R.id.adminprogressbarId);
        adminLogin = findViewById(R.id.adminLogId);

        adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String email = editTextAdminemail.getText().toString().trim();
                String password = editTextadminpass.getText().toString().trim();
                if (email.isEmpty() && password.isEmpty()){
                    editTextAdminemail.setError("Enter email");
                    editTextadminpass.setError("Enter Password");
                    Toast.makeText(AdminInterface.this, "Email and Password are required", Toast.LENGTH_SHORT).show();
                return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                  editTextAdminemail.setError("Invalid email address");
                  return;
                }
                progressBar.setVisibility(view.VISIBLE);

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(view.GONE);
                        if (task.isSuccessful()){
                            Intent intent = new Intent(AdminInterface.this,Admin.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                        finish();

                    }
                });


            }
        });

    }
}
