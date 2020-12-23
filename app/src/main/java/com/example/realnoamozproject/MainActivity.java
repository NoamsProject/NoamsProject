package com.example.realnoamozproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{
    Intent IUp, ILog;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void SignUp(View view)
    {
        IUp = new Intent(this, SignUpStudentActivity.class);
        startActivity(IUp);
    }

    public void SignIn(View view)
    {
        ILog = new Intent(this, SignInActivity.class);
        startActivity(ILog);
    }
}