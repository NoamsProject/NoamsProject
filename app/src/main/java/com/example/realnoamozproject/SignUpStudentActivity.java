package com.example.realnoamozproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpStudentActivity extends AppCompatActivity
{
    TextView TvName, TvPass, TvGrade, TvGrade_Number;
    Intent Start;
    String Grade_Number = "", Name, Pass, Grade;
    DBHelper DB;
    SQLiteDatabase SQDB;
    RadioButton RbMale, RbFemale;
    Student user = new Student();
    EditText EtGrade, EtName, EtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_student);

        DB=new DBHelper(this);
        SQDB=DB.getWritableDatabase();
        SQDB.close();

        TvGrade_Number = findViewById(R.id.TvGrade_Number);
        TvGrade = findViewById(R.id.TvGrade);
        TvPass = findViewById(R.id.TvPass);
        TvName = findViewById(R.id.TvName);

        RbMale = findViewById(R.id.Male);
        RbFemale = findViewById(R.id.Female);
        EtPassword = findViewById(R.id.Password);
        EtGrade = findViewById(R.id.Grade);
        EtName = findViewById(R.id.Name);

        EtName.setOnClickListener(this::WrongName);
        EtPassword.setOnClickListener(this::WrongPass);
        EtGrade.setOnClickListener(this::WrongGrade);
        RbMale.setOnClickListener(this::Grade_Number);
        RbFemale.setOnClickListener(this::Grade_Number);
    }

    public void Grade_Number(View view)
    {
        if (!RbFemale.isChecked() && !RbMale.isChecked())
        {
            TvGrade_Number.setTextColor(Color.parseColor("#E60026"));
            return;
        }
        TvGrade_Number.setTextColor(Color.parseColor("#04E770"));
    }

    public void WrongName(View view)
    {
        Name = EtName.getText().toString();
        if (Name.length() < 3)
        {
            TvName.setTextColor(Color.parseColor("#E60026"));
            Toast.makeText(this, "This name is too short", Toast.LENGTH_SHORT).show();
        }
        if (Name.length() >= 3)
            TvName.setTextColor(Color.parseColor("#04E770"));

        if (Is_Found(Name))
        {
            Toast.makeText(this,"This name is already used", Toast.LENGTH_SHORT).show();
            TvName.setTextColor(Color.parseColor("#E60026"));
        }
    }

    public void WrongPass(View view)
    {
        Pass = EtPassword.getText().toString();
        if ((Pass.length() < 8) || (Pass.indexOf(" ") != -1))
        {
            TvPass.setTextColor(Color.parseColor("#E60026"));
            Toast.makeText(this, "There are not at least 8 characters with no space", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Pass.length() >= 8)
            TvPass.setTextColor(Color.parseColor("#04E770"));
    }

    public void WrongGrade(View view)
    {
        Grade = EtGrade.getText().toString();
        if ((Grade.equals("")) || (Integer.parseInt(Grade) > 99) || (Integer.parseInt(Grade) == 0))
        {
            TvGrade.setTextColor(Color.parseColor("#E60026"));
            Toast.makeText(this, "Your Grade is not between 1 and 99", Toast.LENGTH_SHORT).show();
            return;
        }
            TvGrade.setTextColor(Color.parseColor("#04E770"));
    }

    public void Create(View view)
    {
        Name = EtName.getText().toString();
        user.setName(Name);
        Pass = EtPassword.getText().toString();
        user.setPassword(Pass);
        Grade = EtGrade.getText().toString();
        user.setGrade(Grade);

        if (Is_Found(Name))
        {
            Toast.makeText(this,"This name is already used", Toast.LENGTH_SHORT).show();
            return;
        }

        WrongName(EtName);
        WrongPass(EtPassword);
        WrongGrade(EtGrade);
        Grade_Number(RbFemale);

        if (RbMale.isChecked())
        {
            user.setGrade_Number("Male");
            Grade_Number = "Male";
        }
        if (RbFemale.isChecked())
        {
            user.setGrade_Number("Female");
            Grade_Number = "Female";
        }

        if ((Name.equals("")) || (Pass.equals("")) || (Grade.equals("")) || (Grade_Number.equals("")))
        {
            Toast.makeText(this, "Please fill in all the details ", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Name.length() < 3)
        {
            Toast.makeText(this, "This name is too short", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "" + user, Toast.LENGTH_SHORT).show();


        GoReg();
        Start = new Intent(this, StartActivity.class);
        startActivity(Start);
    }


    private void GoReg()
    {
        ContentValues Cv = new ContentValues();
        Cv.put(DB.NAME, Name.toLowerCase());
        Cv.put(DB.PASS, Pass);
        Cv.put(DB.GRADE, Grade);
        Cv.put(DB.GRADE_NUMBER, Grade_Number);

        SQDB = DB.getWritableDatabase();
        SQDB.insert(DB.TABLE_NAME, null, Cv);
        SQDB.close();
    }

    private boolean Is_Found(String name)
    {
        boolean Used = false;
        name = name.toLowerCase();
        SQDB = DB.getWritableDatabase();
        Cursor C = SQDB.query(DBHelper.TABLE_NAME, null, null, null, null, null, null);
        C.moveToFirst();

        while (!C.isAfterLast())
        {
            String SName = (C.getString(C.getColumnIndex(DBHelper.NAME))).toLowerCase();
            if (name.equals(SName))
                Used = true;
            C.moveToNext();
        }
        C.close();
        SQDB.close();
        return Used;
    }

    public void Back(View view)
    {
        Intent Back = new Intent(this, MainActivity.class);
        startActivity(Back);
    }
}