package com.example.realnoamozproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

public class StartActivity extends AppCompatActivity
{
    Intent Back, InCredits, InHelp, InSignUp, InPrivateArea;
    String St;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        InSignUp = new Intent(this, MainActivity.class);
        InCredits = new Intent(this, CreditsActivity.class);
        InHelp = new Intent(this, HelpActivity.class);
        //InPrivateArea = new Intent(this, PrivateAreaActivity.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        St = item.getTitle().toString();
        switch(St)
        {
            case "Credits" : startActivity(InCredits);
            break;
            case "Ask For Help" : startActivity(InHelp);
            break;
            case "Log Out" : startActivity(InSignUp);
            break;
        }
        return true;
    }

    public void PrivateArea(View view)
    {
        //startActivity(InPrivateArea);
    }
}