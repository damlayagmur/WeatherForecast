package com.damlayagmur.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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

        CardView loginButton = findViewById(R.id.cardview_activitylogin_loginbutton);
       // loginButton.setOnClickListener(this);

    }
}
