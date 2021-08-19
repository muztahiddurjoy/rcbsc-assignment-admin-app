package com.epizy.muztahiddurjoy.assignmentadminapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen  extends AppCompatActivity {
    private FirebaseAuth auth;
    @Override
    protected void onPostCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser()!=null){
            startActivity(new Intent(this,MainActivity.class));
        }
        else {
            startActivity(new Intent(this, AuthenticationActivity.class));
        }
    }
}
