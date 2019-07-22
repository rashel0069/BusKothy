package com.example.buskothy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registrationform extends AppCompatActivity {

    EditText editTextname,editTextemail,editTextmobile,editTextbusnumber,editTextpassword,editTextNid;
    Spinner  spinnergender,spinnerreligion;
    Button buttonregist;

    private FirebaseAuth mAuth;
    DatabaseReference databaseAuthor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationform);
        databaseAuthor = FirebaseDatabase.getInstance().getReference("author");

        mAuth = FirebaseAuth.getInstance();
        editTextNid = findViewById(R.id.nationalId);
        editTextname = findViewById(R.id.nameId);
        editTextemail = findViewById(R.id.emailId);
        editTextmobile = findViewById(R.id.mobileId);
        editTextbusnumber = findViewById(R.id.busnumberId);
        editTextpassword = findViewById(R.id.passwordId);
        spinnergender = findViewById(R.id.genderId);
        spinnerreligion = findViewById(R.id.religionsId);

        buttonregist = findViewById(R.id.regID);

        buttonregist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addauthor();
            }
        });
    }

    private void addauthor(){
        String name = editTextname.getText().toString().trim();
        String email = editTextemail.getText().toString().trim();
        String gender = spinnergender.getSelectedItem().toString();
        String religion = spinnerreligion.getSelectedItem().toString();
        String mobile = editTextmobile.getText().toString().trim();
        String nid = editTextNid.getText().toString().trim();
        String busnumber = editTextbusnumber.getText().toString().trim();
        String password = editTextpassword.getText().toString().trim();

        if (!TextUtils.isEmpty(name)){
            String id = databaseAuthor.push().getKey();

            Author author = new Author(id, name, email, gender, religion, mobile,nid, busnumber, password);

            databaseAuthor.child(id).setValue(author);

            Toast.makeText(this, "Author added", Toast.LENGTH_SHORT).show();
            editTextname.setText("");
            editTextemail.setText("");
            editTextmobile.setText("");
            editTextNid.setText("");
            editTextbusnumber.setText("");
            editTextpassword.setText("");
        }else {
            Toast.makeText(this, "Enter name and other field", Toast.LENGTH_SHORT).show();
        }
    }
}