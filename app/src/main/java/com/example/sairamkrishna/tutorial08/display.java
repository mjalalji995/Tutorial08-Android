package com.example.sairamkrishna.tutorial08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Display;
import android.widget.TextView;
import android.widget.Toast;

public class display extends AppCompatActivity {

    TextView display;
    MyDatabaseHelper myDB;
    String userdata = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        display = findViewById(R.id.wel_display);
        myDB = new MyDatabaseHelper(this);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        Toast.makeText(display.this, username, Toast.LENGTH_SHORT).show();
        Cursor cursor = myDB.getPartUserData(username);
        cursor.moveToFirst();
        userdata += "First Name: "+cursor.getString(1);
        userdata += "\nLast Name: " + cursor.getString(2);
        userdata += "\nEmail: " + cursor.getString(3);
        userdata += "\nPassword: " + cursor.getString(4);
        userdata += "\nBranch :" + cursor.getString(5);
        userdata += "\nGender: " + cursor.getString(6);
        userdata += "\nCity: " + cursor.getString(7);
        display.setText(userdata);

    }
}