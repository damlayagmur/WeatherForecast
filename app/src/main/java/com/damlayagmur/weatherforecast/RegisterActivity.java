package com.damlayagmur.weatherforecast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    CardView registerbutton;
    EditText enteredName;
    EditText  enteredEmail;
    EditText enteredPassword;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerbutton = findViewById(R.id.cardview_activityregister_registerbutton);
        enteredName =  findViewById(R.id.register_name);
        enteredEmail = findViewById(R.id.register_email);
        enteredPassword = findViewById(R.id.register_password);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = enteredEmail.getText().toString().trim();
                //String name = enteredName.getText().toString();
                String password= enteredPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    enteredEmail.setError("E-mail is required");
                }
                if(TextUtils.isEmpty(password)){
                    enteredPassword.setError("Password is required");
                }
                if(password.length()<6){
                    enteredPassword.setError("Password must be at least 6 character");
                }

                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener <AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task <AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this,"User Created",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else{
                            Toast.makeText(RegisterActivity.this,"Error "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
    }
}
