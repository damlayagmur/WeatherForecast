package com.damlayagmur.weatherforecast.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.damlayagmur.weatherforecast.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText enteredEmail;
    EditText enteredPassword;
    CardView loginButton;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        enteredEmail = findViewById(R.id.login_page_email);
        enteredPassword = findViewById(R.id.login_page_password);
        firebaseAuth = FirebaseAuth.getInstance();
        initViews();
    }

    private void initViews() {
        TextView registerPage = findViewById(R.id.textview_activitylogin_register);
        SpannableString registerSpannable = new SpannableString("Do not have an account? Register");
        ClickableSpan clickableSpannable = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        };
        registerSpannable.setSpan(clickableSpannable, 24, 32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        registerPage.setText(registerSpannable);
        registerPage.setMovementMethod(LinkMovementMethod.getInstance());


        loginButton = findViewById(R.id.cardview_activitylogin_loginbutton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = enteredEmail.getText().toString();
                //String name = enteredName.getText().toString();
                String password = enteredPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    enteredEmail.setError("E-mail is required");
                } else if (TextUtils.isEmpty(password)) {
                    enteredPassword.setError("Password is required");
                } else if (password.length() < 6) {
                    enteredPassword.setError("Password must be at least 6 character");
                } else {


                    firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener <AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task <AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Logged in Succesfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            } else {
                                Toast.makeText(LoginActivity.this, "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }
}
