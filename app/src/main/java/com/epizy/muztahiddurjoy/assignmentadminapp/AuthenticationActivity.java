package com.epizy.muztahiddurjoy.assignmentadminapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class AuthenticationActivity extends AppCompatActivity {
    TextInputEditText email, password;
    FirebaseAuth auth;
    Button button_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        email = findViewById(R.id.email_auth);
        password = findViewById(R.id.pass_auth);
        button_login = findViewById(R.id.login_btn_auth);
        auth = FirebaseAuth.getInstance();
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailT = email.getText().toString();
                String passT = password.getText().toString();
                auth.signInWithEmailAndPassword(emailT,passT).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Snackbar.make(v,"Logged in Successfully!", BaseTransientBottomBar.LENGTH_LONG).show();
                        startActivity(new Intent(AuthenticationActivity.this, MainActivity.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Snackbar.make(v,e.getMessage(), BaseTransientBottomBar.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}