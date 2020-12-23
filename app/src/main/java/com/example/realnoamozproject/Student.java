package com.example.realnoamozproject;

public class Student
{
    private String Name;
    private String Password;
    private String Grade; //Age
    private String Grade_Number; //Gender

    public Student()
    {

    }
    public Student(String name, String grade_number, String grade, String password)
    {
        this.Name = name;
        this.Grade = grade;
        this.Grade_Number = grade_number;
        this.Password = password;
    }

    public void setPassword(String password)
    {
        Password = password;
    }
    public void setName(String name)
    {
        Name = name;
    }
    public void setGrade(String grade)
    {
        Grade = grade;
    }
    public void setGrade_Number(String grade_number)
    {
        Grade_Number = grade_number;
    }
    @Override
    public String toString()
    {
        return ("Name: " + Name + ", Password: " + Password + ", Grade_Number: " + Grade_Number + ", Grade: " + Grade);
    }
}
