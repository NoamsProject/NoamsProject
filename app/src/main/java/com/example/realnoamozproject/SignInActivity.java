package com.example.realnoamozproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity
{
    Button BtSignUp;
    TextView TvName, TvPass;
    Intent GoLogIn, GoSignUp;
    DBHelper DB;
    SQLiteDatabase SQDB;
    EditText EtName, EtPass;
    String Name, Pass;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        DB=new DBHelper(this);
        SQDB=DB.getWritableDatabase();
        SQDB.close();

        TvPass = findViewById(R.id.TvPass);
        TvName = findViewById(R.id.TvName);
        EtName = findViewById(R.id.EtName);
        EtPass = findViewById(R.id.EtPass);

        BtSignUp = findViewById(R.id.BtSignUp);

        EtName.setOnClickListener(this::WrongName);
        BtSignUp.setOnLongClickListener(this::Sod);
    }

    private boolean Sod(View view)
    {
        EtName.setText("Noamoz");
        EtPass.setText("123123123");
        return true;
    }

    public void WrongPass(View view)
    {
        Name = EtName.getText().toString();
        Name = Name.toLowerCase();
        if(FoundName(Name))
        {
            TvName.setTextColor(Color.parseColor("#04E770")); //Green
            TvPass.setTextColor(Color.parseColor("#E60026")); //Red
            Toast.makeText(this, "Your Password is incorrect", Toast.LENGTH_SHORT).show();
        }
        else
        {
            TvName.setTextColor(Color.parseColor("#E60026")); //Red
            TvPass.setTextColor(Color.parseColor("#757373")); //Gray
            Toast.makeText(this, "Your Name is incorrect", Toast.LENGTH_SHORT).show();
        }
    }

    public void WrongName(View view)
    {
        Name = EtName.getText().toString();
        Name = Name.toLowerCase();
        if(FoundName(Name))
        {
            TvName.setTextColor(Color.parseColor("#04E770")); //Green
            //Toast.makeText(this, "Your Password is incorrect", Toast.LENGTH_SHORT).show();
        }
        else
            {
            TvName.setTextColor(Color.parseColor("#E60026")); //Red
            //Toast.makeText(this, "Your Name is incorrect", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean FoundName(String name)
    {
        name = name.toLowerCase();
        SQDB = DB.getWritableDatabase();
        String whereFind = DBHelper.NAME + "=?";
        whereFind.toLowerCase();
        String[] whatFind = {name};
        Cursor c = SQDB.query(DBHelper.TABLE_NAME, null, whereFind.toLowerCase(), whatFind, null,null,null);
        boolean Search = c.moveToFirst();
        c.close();
        SQDB.close();
        return Search;
    }

    public void LogIn(View view)
    {
        Name = EtName.getText().toString();
        Pass = EtPass.getText().toString();

        if ((Name.equals("")) || (Pass.equals("")))
        {
            Toast.makeText(this, "Please fill in all the details ", Toast.LENGTH_SHORT).show();
            TvPass.setTextColor(Color.parseColor("#E60026")); //Red
            TvName.setTextColor(Color.parseColor("#E60026")); //Red

            return;
        }

        if (Found(Name, Pass))
        {
            Toast.makeText(this, "Welcome Back!", Toast.LENGTH_SHORT).show();
            GoLogIn = new Intent(this, StartActivity.class);
            startActivity(GoLogIn);
            return;
        }

        WrongName(EtName);
        WrongPass(EtPass);
    }

    private boolean Found(String name, String pass)
    {
        name = name.toLowerCase();
        SQDB = DB.getWritableDatabase();
        String whereFind = DBHelper.NAME + "=? AND " + DBHelper.PASS+"=?";
        String[] whatFind = {name,pass};
        Cursor c = SQDB.query(DBHelper.TABLE_NAME, null, whereFind, whatFind, null,null,null);
        boolean Search = c.moveToFirst();
        c.close();
        SQDB.close();
        return Search;
    }

    private boolean FoundPass(String pass)
    {
        SQDB = DB.getWritableDatabase();
        String whereFind = DBHelper.PASS + "=?";
        String[] whatFind = {pass};
        Cursor c = SQDB.query(DBHelper.TABLE_NAME, null, whereFind, whatFind, null,null,null);
        boolean Search = c.moveToFirst();
        c.close();
        SQDB.close();
        return Search;
    }

    public void SignUp(View view)
    {
        GoSignUp = new Intent(this, SignUpStudentActivity.class);
        startActivity(GoSignUp);
    }
}